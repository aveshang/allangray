package za.co.ag.allangray.assignment.twitterfeed.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class TweetParser implements Parser<List<Tweet>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(TweetParser.class);

    private static final Pattern TWEET_PATTERN = Pattern.compile("> ");
    public static final int EXPECTED_TOKEN_COUNT = 2;
    public static final int MESSAGE_LENGTH_LIMIT = 140;

    @Override
    public List<Tweet> parse(File file) {
        List<Tweet> tweets = new ArrayList<Tweet>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String tweetLine;
            while ((tweetLine = bufferedReader.readLine()) != null) {
                String[] tweetData = TWEET_PATTERN.split(tweetLine);

                // Assuming the flow of not halting processing due to errors,
                // chose to favour "continue" statements over if/else for readability.

                if (tweetData.length > EXPECTED_TOKEN_COUNT) {
                    LOGGER.error("Tweet line \"" + tweetLine + "\" contains misformatted data. Does the tweet contain \'> \'?");
                    continue;
                }

                if (tweetData.length < EXPECTED_TOKEN_COUNT) {
                    LOGGER.error("Tweet line \"" + tweetLine + "\" contains missing message content. Tweet ignored.");
                    continue;
                }

                String user = tweetData[0];
                String message = tweetData[1];

                if (!UserRepository.getInstance().doesUserExist(user)) {
                    LOGGER.error("User " + user + " not found in user list. Cannot process tweet: " + message);
                    continue;
                }

                if (message.length() > MESSAGE_LENGTH_LIMIT) {
                    LOGGER.error("Message \"" + message + "\" exceeds 140 character limit. Tweet ignored.");
                    continue;
                }

                tweets.add(new Tweet(user, message));
            }

        } catch (Exception e) {
            String errorMessage = "Unable to find/read file " + file.getAbsolutePath();

            LOGGER.error(errorMessage, e);
            throw new RuntimeException(errorMessage, e);

        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    String errorMessage = "Unable to close file reader for file " + file.getAbsolutePath();
                    LOGGER.error(errorMessage, e);
                }
            }
        }

        return tweets;
    }

}
