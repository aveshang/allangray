package za.co.ag.allangray.assignment.twitterfeed.repository;

import java.util.*;

public class UserRepository {
    private static UserRepository instance;

    private Map<String, Set<String>> userMap = new TreeMap<String, Set<String>>();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void addFolloweeAndUser(String followee, String user) {
        if (!userMap.containsKey(followee)) {
            Set<String> followerSet = createEmptySet();
            followerSet.add(user);

            userMap.put(followee, followerSet);

        } else {
            userMap.get(followee).add(user);
        }

        if (!userMap.containsKey(user)) {
            userMap.put(user, createEmptySet());
        }
    }

    private Set<String> createEmptySet() {
        return new HashSet<String>();
    }

    public Set<String> getFollowersOfUser(String user) {
        return userMap.get(user);
    }

    public boolean doesUserExist(String name) {
        return userMap.containsKey(name);
    }

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();
        users.addAll(userMap.keySet());

        return users;
    }

    public void clearData() {
        userMap.clear();
    }

}
