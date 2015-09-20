package za.co.ag.allangray.assignment.twitterfeed.processor;

import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;
import za.co.ag.allangray.assignment.twitterfeed.domain.User;

import java.util.List;

public class UserProcessor {

    public void addUserDataToUserRepository(List<User> users) {
        for (User user : users) {
            for (String followee : user.getFollowees()) {
                UserRepository.getInstance().addFolloweeAndUser(followee, user.getUsername());
            }
        }
    }

}
