package za.co.ag.allangray.assignment.twitterfeed.parser;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.UserHelper;
import za.co.ag.allangray.assignment.twitterfeed.domain.Tweet;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;

import java.io.File;
import java.util.List;

public class TweetParserTestCase {

    @Before
    public void setupData() {
        UserRepository.getInstance().clearData();
    }

    @Test
    public void testParseData() {

        UserRepository.getInstance().addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.FORD);

        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("src/test/resources/samples/tweetsFromTheGalaxy_1.txt"));

        Assert.assertEquals(3, tweets.size()); //TODO: better assertion
    }

    @Test
    public void testParseDataNoUser() {

        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("src/test/resources/samples/tweetsFromTheGalaxy_1.txt"));

        Assert.assertEquals(0, tweets.size());
    }

    @Test
    public void testParseDataWithErrorAndMessageLimitExceeded() {

        UserRepository.getInstance().addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.FORD);

        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("src/test/resources/samples/tweetsFromTheGalaxy_1_dodgy.txt"));

        Assert.assertEquals(0, tweets.size());
    }

    @Test(expected = RuntimeException.class)
    public void testParseNoFileFound() {
        TweetParser tweetParser = new TweetParser();
        List<Tweet> tweets = tweetParser.parse(new File("unknownlocation"));
    }

}
