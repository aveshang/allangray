package za.co.ag.allangray.assignment.twitterfeed.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ag.allangray.assignment.twitterfeed.domain.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserParser implements Parser<List<User>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TweetParser.class);
    private static final Pattern USER_FOLLOW_PATTERN = Pattern.compile(" follows ");
    private static final Pattern USER_LIST_PATTERN = Pattern.compile(",");
    public static final int EXPECTED_TOKEN_COUNT = 2;

    @Override
    public List<User> parse(File file) {
        List<User> users = new ArrayList<User>();

        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));

            String userLine;
            while ((userLine = bufferedReader.readLine()) != null) {
                String[] userData = USER_FOLLOW_PATTERN.split(userLine);

                // Assuming the flow of not halting processing due to errors,
                // chose to favour "continue" statements over if/else for readability.

                if (userData.length < EXPECTED_TOKEN_COUNT) {
                    LOGGER.error("Follower/User line \"" + userLine + "\" contains incomplete data.");
                    continue;
                }

                if (userData.length > EXPECTED_TOKEN_COUNT) {
                    LOGGER.error("Follower/User line \"" + userLine + "\" contains misformatted data. Does the word \'follow\' occur multiple times?");
                    continue;

                }

                String user = userData[0];
                List<String> trimmedFollowees = ParserUtil.trimData(USER_LIST_PATTERN.split(userData[1]));

                users.add(new User(user, trimmedFollowees));
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

        return users;
    }

}