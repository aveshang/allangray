package za.co.ag.allangray.assignment.twitterfeed.printer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;

import java.util.List;

public class UserFeedPrinter {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserFeedPrinter.class);

    public void printUserFeed() {
        List<String> users = UserRepository.getInstance().getAllUsers();

        StringBuilder feed = new StringBuilder();
        feed.append("\n");

        for (String user : users) {
            List<Tweet> userFeed = UserFeedRepository.getInstance().getUserFeed(user);

            feed.append(user);
            feed.append("\n");

            if ((userFeed != null) && (!userFeed.isEmpty())) {
                for (Tweet tweet : userFeed) {
                    feed.append("@");
                    feed.append(tweet.getCreator());
                    feed.append(": ");
                    feed.append(tweet.getMessage());
                    feed.append("\n");
                }
            }
        }

        LOGGER.info(feed.toString());
    }

}
