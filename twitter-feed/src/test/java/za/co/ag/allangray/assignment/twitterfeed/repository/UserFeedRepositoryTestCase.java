package za.co.ag.allangray.assignment.twitterfeed.repository;

import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.UserHelper;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by waveshan on 2015/09/16.
 */
public class UserFeedRepositoryTestCase {

    public static final String TWEET_1 = "I don't want to go to heaven with a headache, I'd be all cross and wouldn't enjoy it.";
    public static final String TWEET_2 = "Then he realized there was a contradiction involved here and merely hoped that there wasn't an afterlife.";
    public static final String TWEET_3 = "We're in a small galley cabin in one of the spaceships of the Vogon Constructor Fleet.";

    @Before
    public void setupData() {
        UserFeedRepository.getInstance().clearData();
    }

    @Test
    public void testAddToUserFeed() {

        UserFeedRepository userFeedRepository = UserFeedRepository.getInstance();
        userFeedRepository.addTweetToUserFeed(UserHelper.ARTHUR, new Tweet(UserHelper.ARTHUR, TWEET_1));
        userFeedRepository.addTweetToUserFeed(UserHelper.ARTHUR, new Tweet(UserHelper.FORD, TWEET_3));
        userFeedRepository.addTweetToUserFeed(UserHelper.ARTHUR, new Tweet(UserHelper.ARTHUR, TWEET_2));

        List<Tweet> arthursFeed = userFeedRepository.getUserFeed(UserHelper.ARTHUR);
        assertEquals(3, arthursFeed.size());
        assertEquals(TWEET_1, arthursFeed.get(0).getMessage());
        assertEquals(TWEET_3, arthursFeed.get(1).getMessage());
        assertEquals(TWEET_2, arthursFeed.get(2).getMessage());
    }

}
