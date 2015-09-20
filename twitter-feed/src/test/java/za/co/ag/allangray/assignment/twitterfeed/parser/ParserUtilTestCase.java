package za.co.ag.allangray.assignment.twitterfeed.parser;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ParserUtilTestCase {

    @Test
    public void testTrimData() {
        List<String> strings = ParserUtil.trimData(new String[]{" bob "});
        assertEquals(1, strings.size());
        assertEquals("bob", strings.get(0));
    }

}
