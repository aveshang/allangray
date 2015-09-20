package za.co.ag.allangray.assignment.twitterfeed.domain;

public class Tweet {

    private String creator;
    private String message;

    public Tweet(String creator, String message) {
        this.creator = creator;
        this.message = message;
    }

    public String getCreator() {
        return creator;
    }

    public String getMessage() {
        return message;
    }

}
