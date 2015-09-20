package za.co.ag.allangray.assignment.twitterfeed.repository;

import org.junit.Before;
import org.junit.Test;
import za.co.ag.allangray.assignment.twitterfeed.UserHelper;
import za.co.ag.allangray.assignment.twitterfeed.repository.UserRepository;

import static org.junit.Assert.*;

public class UserRepositoryTestCase {

    @Before
    public void setupData() {
        UserRepository.getInstance().clearData();
    }

    @Test
    public void testAddFollower() {

        UserRepository followerRepository = UserRepository.getInstance();
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.ZAPHOD);

        assertTrue(followerRepository.getFollowersOfUser(UserHelper.ARTHUR).contains(UserHelper.ZAPHOD));
    }

    @Test
    public void testAddMultipleFollowers() {

        UserRepository followerRepository = UserRepository.getInstance();
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.ZAPHOD);
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.FORD);

        assertEquals(2, followerRepository.getFollowersOfUser(UserHelper.ARTHUR).size());
    }

    @Test
    public void testAddDuplicateFollowers() {

        UserRepository followerRepository = UserRepository.getInstance();
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.ZAPHOD);
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.FORD);
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.ZAPHOD);

        assertEquals(2, followerRepository.getFollowersOfUser(UserHelper.ARTHUR).size());
        assertTrue(followerRepository.getFollowersOfUser(UserHelper.ARTHUR).contains(UserHelper.ZAPHOD));
    }

    @Test
    public void testFollowerIsNotBeingFollowed() {

        UserRepository followerRepository = UserRepository.getInstance();
        followerRepository.addFolloweeAndUser(UserHelper.ARTHUR, UserHelper.ZAPHOD);

        assertEquals(0, followerRepository.getFollowersOfUser(UserHelper.ZAPHOD).size());
    }
}
