package za.co.ag.allangray.assignment.twitterfeed.parser;

import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.TestFileConstants;
import za.co.ag.allangray.assignment.twitterfeed.domain.User;
import za.co.ag.allangray.assignment.twitterfeed.parser.UserParser;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserFeedRepository;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserParserTestCase {

    @Before
    public void setupData() {
        UserRepository.getInstance().clearData();
    }

    @Test
    public void testParseFollowerUsers() {

        UserParser userParser = new UserParser();
        List<User> users = userParser.parse(new File(TestFileConstants.GALAXY_USER_FILE));

        assertEquals(4, users.size());

        // Ford follows Arthur
        assertEquals("Ford", users.get(0).getUsername());
        assertEquals("Arthur", users.get(0).getFollowees().get(0));

        // Arthur follows Zaphod
        assertEquals("Arthur", users.get(1).getUsername());
        assertEquals("Zaphod", users.get(1).getFollowees().get(0));

        // Ford follows Zaphod, Arthur
        assertEquals("Ford", users.get(2).getUsername());
        assertEquals("Zaphod", users.get(2).getFollowees().get(0));
        assertEquals("Arthur", users.get(2).getFollowees().get(1));

        // Zaphod follows Ford, Ford, Zaphod
        assertEquals("Zaphod", users.get(3).getUsername());
        assertEquals("Ford", users.get(3).getFollowees().get(0));
        assertEquals("Ford", users.get(3).getFollowees().get(1));
        assertEquals("Zaphod", users.get(3).getFollowees().get(2));
    }

    @Test
    public void testParseShortLine() {
        UserParser userParser = new UserParser();
        List<User> users = userParser.parse(new File(TestFileConstants.GALAXY_USER_FILE_SHORT));

        assertEquals(0, users.size());
    }

    @Test
    public void testParseLongLine() {
        UserParser userParser = new UserParser();
        List<User> users = userParser.parse(new File(TestFileConstants.GALAXY_USER_FILE_LONG));

        assertEquals(0, users.size());
    }

    @Test(expected = RuntimeException.class)
    public void testParseNoFileFound() {
        UserParser userParser = new UserParser();
        List<User> users = userParser.parse(new File("unknownlocation"));
    }


}
