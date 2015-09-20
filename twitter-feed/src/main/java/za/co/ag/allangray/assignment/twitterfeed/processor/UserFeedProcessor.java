package za.co.ag.allangray.assignment.twitterfeed.processor;

import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;

import java.util.List;
import java.util.Set;

public class UserFeedProcessor {

    public void addTweetsToUserFeed(List<Tweet> tweets) {
        for (Tweet tweet : tweets) {

            // Add a user's tweet to his/her own timeline
            UserFeedRepository.getInstance().addTweetToUserFeed(tweet.getCreator(), tweet);

            Set<String> followersOfUser = UserRepository.getInstance().getFollowersOfUser(tweet.getCreator());
            for (String user : followersOfUser) {
                // Add a user's tweet to his/her followers timeline
                UserFeedRepository.getInstance().addTweetToUserFeed(user, tweet);
            }
        }
    }
}
