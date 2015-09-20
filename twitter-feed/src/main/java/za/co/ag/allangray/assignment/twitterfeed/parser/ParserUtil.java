package za.co.ag.allangray.assignment.twitterfeed.parser;

import java.util.ArrayList;
import java.util.List;

public class ParserUtil {

    private ParserUtil(){
    }

    public static List<String> trimData(String[] items) {
        List<String> result = new ArrayList<>();

        for (String item : items) {
            result.add(item.trim());
        }

        return result;
    }

}
