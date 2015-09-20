package za.co.ag.allangray.assignment.twitterfeed.parser;

import java.io.File;

public interface Parser<T> {

    public T parse(File file);

}
