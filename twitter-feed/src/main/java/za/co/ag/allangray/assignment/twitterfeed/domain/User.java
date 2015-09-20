package za.co.ag.allangray.assignment.twitterfeed.domain;

import java.util.List;

public class User {

    private String username;
    private List<String> followees;

    public User(String username, List<String> followees) {
        this.username = username;
        this.followees = followees;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getFollowees() {
        return followees;
    }

}