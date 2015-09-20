package za.co.ag.allangray.assignment.twitterfeed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ag.allangray.assignment.twitterfeed.domain.User;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;
import za.co.ag.allangray.assignment.twitterfeed.parser.UserParser;
import za.co.ag.allangray.assignment.twitterfeed.parser.TweetParser;
import za.co.ag.allangray.assignment.twitterfeed.printer.UserFeedPrinter;
import za.co.ag.allangray.assignment.twitterfeed.processor.UserProcessor;
import za.co.ag.allangray.assignment.twitterfeed.processor.UserFeedProcessor;

import java.io.File;
import java.util.List;

public class TwitterFeedApplication {
    private static final Logger LOGGER = LoggerFactory.getLogger(TwitterFeedApplication.class);

    public static void main(String[] args) {

        StringBuilder usageInstructions = new StringBuilder();
        usageInstructions.append("\n");
        usageInstructions.append("TwitterFeedApplication Usage:\n");
        usageInstructions.append("java -jar <jar file> <user file> <tweet file>\n");
        usageInstructions.append("eg. java -jar twitter-feed-1.0.0-jar-with-dependencies.jar user.txt tweet.txt");

        if (args == null || args.length == 0) {
            LOGGER.error("No user and tweet file specified.");
            LOGGER.info(usageInstructions.toString());
            return;
        }

        if (args[0] == null || args[0].isEmpty()) {
            LOGGER.error("No user file specified. Please specify user file as argument 0.");
            LOGGER.info(usageInstructions.toString());
            return;
        }

        if (args[1] == null || args[1].isEmpty()) {
            LOGGER.error("No tweet file specified. Please specify tweet file as argument 1.");
            LOGGER.info(usageInstructions.toString());
            return;
        }

        TwitterFeedApplication application = new TwitterFeedApplication();
        application.run(args[0], args[1]);
    }

    public void run(String userFile, String tweetFile) {
        // Parse the user file.
        UserParser userParser = new UserParser();
        List<User> users = userParser.parse(new File(userFile));

        // Add the users to the singleton repo.
        UserProcessor userProcessor = new UserProcessor();
        userProcessor.addUserDataToUserRepository(users);

        // Parse the tweets.
        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File(tweetFile));

        // Add the tweets to the a singleton feed repo for each user.
        // Instead of storing the tweets per user and then querying across this store for deciphering each user's feed,
        // build up a list of each user's feed as each tweet is processed, by adding to the tweet creator's followers feed.
        UserFeedProcessor userFeedProcessor = new UserFeedProcessor();
        userFeedProcessor.addTweetsToUserFeed(tweets);

        // Print the contents of the singleton feed repo.
        UserFeedPrinter userFeedPrinter = new UserFeedPrinter();
        userFeedPrinter.printUserFeed();
    }

}
