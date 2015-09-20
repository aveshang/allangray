package za.co.ag.allangray.assignment.twitterfeed.repository;

import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;

import java.util.*;

public class UserFeedRepository {
    private static UserFeedRepository instance;

    private Map<String, List<Tweet>> userFeed = new TreeMap<String, List<Tweet>>();

    private UserFeedRepository() {
    }

    public static UserFeedRepository getInstance() {
        if (instance == null) {
            instance = new UserFeedRepository();
        }
        return instance;
    }

    public void addTweetToUserFeed(String user, Tweet tweet) {
        if (!userFeed.containsKey(user)) {
            List<Tweet> tweetVector = new ArrayList<Tweet>();
            tweetVector.add(tweet);

            userFeed.put(user, tweetVector);

        } else {
            userFeed.get(user).add(tweet);
        }
    }

    public List<Tweet> getUserFeed(String user) {
        return userFeed.get(user);
    }

    public void clearData() {
        userFeed.clear();
    }

}
