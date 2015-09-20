package za.co.ag.allangray.assignment.twitterfeed;

import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;

import static org.junit.Assert.assertEquals;

public class TwitterFeedApplicationTestCase {

    @Before
    public void setupData() {
        UserRepository.getInstance().clearData();
        UserFeedRepository.getInstance().clearData();
    }

    @Test
    public void testFeedWithGivenExamples() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.run(TestFileConstants.EXAMPLE_USER_FILE, TestFileConstants.EXAMPLE_TWEET_FILE);

        assertEquals(3, UserRepository.getInstance().getAllUsers().size());
        assertEquals(2, UserFeedRepository.getInstance().getUserFeed("Alan").size());
    }

    @Test
    public void testFeedWithSamples1() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.run(TestFileConstants.GALAXY_USER_FILE, TestFileConstants.GALAXY_TWEET_FILE);

        assertEquals(3, UserRepository.getInstance().getAllUsers().size());
        assertEquals(2, UserFeedRepository.getInstance().getUserFeed(UserHelper.ARTHUR).size());
    }

    @Test
    public void testMainMethodNoArgs() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.main(null);

        assertEquals(0, UserRepository.getInstance().getAllUsers().size());
    }

    @Test
    public void testMainMethod1Arg() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.main(new String[] {""});

        assertEquals(0, UserRepository.getInstance().getAllUsers().size());
    }

    @Test
    public void testMainMethod2Arg() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.main(new String[] {TestFileConstants.GALAXY_USER_FILE, ""});

        assertEquals(0, UserRepository.getInstance().getAllUsers().size());
    }

    @Test
    public void testMainMethodFullArgs() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.main(new String[] {TestFileConstants.GALAXY_USER_FILE, TestFileConstants.GALAXY_TWEET_FILE});

        assertEquals(3, UserRepository.getInstance().getAllUsers().size());
        assertEquals(2, UserFeedRepository.getInstance().getUserFeed(UserHelper.ARTHUR).size());
    }

    @Test
    public void testMainMethodLargeFile() {
        TwitterFeedApplication twitterFeedApplication = new TwitterFeedApplication();
        twitterFeedApplication.main(new String[] {TestFileConstants.GALAXY_USER_FILE, TestFileConstants.GALAXY_TWEET_FILE_LARGE});

        assertEquals(3, UserRepository.getInstance().getAllUsers().size());
        assertEquals(700, UserFeedRepository.getInstance().getUserFeed(UserHelper.ARTHUR).size());
    }


}
