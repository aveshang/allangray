package za.co.ag.allangray.assignment.twitterfeed.processor;

import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.UserHelper;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;
import za.co.ag.allangray.assignment.twitterfeed.parser.TweetParser;
import za.co.ag.allangray.assignment.twitterfeed.processor.UserFeedProcessor;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by waveshan on 2015/09/16.
 */
public class UserFeedProcessorTestCase {

    @Before
    public void setupData() {
        UserRepository.getInstance().clearData();
        UserFeedRepository.getInstance().clearData();
    }

    @Test
    public void testProcessUserFeed1() {
        UserRepository.getInstance().addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.FORD);

        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("src/test/resources/samples/tweetsFromTheGalaxy_1.txt"));

        UserFeedProcessor userFeedProcessor = new UserFeedProcessor();
        userFeedProcessor.addTweetsToUserFeed(tweets);

        List<Tweet> arthursFeed = UserFeedRepository.getInstance().getUserFeed(UserHelper.ARTHUR);
        assertEquals(2, arthursFeed.size());

        List<Tweet> fordsFeed = UserFeedRepository.getInstance().getUserFeed(UserHelper.FORD);
        assertEquals(3, fordsFeed.size());
    }

    @Test
    public void testProcessUserFeed2() {
        UserRepository.getInstance().addFolloweeAndUser(UserHelper.FORD, UserHelper.ARTHUR);

        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("src/test/resources/samples/tweetsFromTheGalaxy_1.txt"));

        UserFeedProcessor userFeedProcessor = new UserFeedProcessor();
        userFeedProcessor.addTweetsToUserFeed(tweets);

        List<Tweet> arthursFeed = UserFeedRepository.getInstance().getUserFeed(UserHelper.ARTHUR);
        assertEquals(3, arthursFeed.size());

        List<Tweet> fordsFeed = UserFeedRepository.getInstance().getUserFeed(UserHelper.FORD);
        assertEquals(1, fordsFeed.size());
    }

}
