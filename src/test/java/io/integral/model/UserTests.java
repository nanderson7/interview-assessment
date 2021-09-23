package io.integral.model;

import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserTests {

	private User user;
	@Mock
	private User mockUser;
	@Mock
	private Timeline mockTimeline;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		this.user = new User(1, mockTimeline, new HashSet<>(), "Noah", "Anderson");
	}
	
	@Test
	void testGetUsersFollowed_returnsUnmodifiableSet() {
		Assertions.assertThrows(UnsupportedOperationException.class, () -> user.getFollowedUsers().add(null));
	}
	
	@Test
	void testFollowUser_returnsTrue_whenUserSuccessfullyAdded_andIncreasesSizeOfFollowedUsersByOne() {
		int initialNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(initialNumOfUsersFollowed == 0);
		
		boolean result = user.followUser(mockUser);
		Assertions.assertTrue(result);
		
		int updatedNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(updatedNumOfUsersFollowed == 1);
	}

	@Test
	void testFollowUser_returnsFalse_whenSameUserisAddedASecondTime_andSizeOfFollowedUsersDoesNotChange() {
		user.followUser(mockUser);
		int initialNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(initialNumOfUsersFollowed == 1);
		
		boolean result = user.followUser(mockUser);
		Assertions.assertFalse(result);
		
		int updatedNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertEquals(initialNumOfUsersFollowed, updatedNumOfUsersFollowed);
	}

	@Test
	void testUnfollowUser_returnsTrue_whenUserSuccessfullyRemoved_andDecreasesSizeOfFollowedUsersByOne() {
		user.followUser(mockUser);
		int initialNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(initialNumOfUsersFollowed == 1);
		
		boolean result = user.unfollowUser(mockUser);
		Assertions.assertTrue(result);
		
		int updatedNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(updatedNumOfUsersFollowed == 0);
	}
	
	@Test
	void testUnfollowUser_returnsFalse_whenSameUserisRemovedASecondTime_andSizeOfFollowedUsersDoesNotChange() {
		user.followUser(mockUser);
		user.unfollowUser(mockUser);
		int initialNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertTrue(initialNumOfUsersFollowed == 0);
		
		boolean result = user.unfollowUser(mockUser);
		Assertions.assertFalse(result);
		
		int updatedNumOfUsersFollowed = user.getFollowedUsers().size();
		Assertions.assertEquals(initialNumOfUsersFollowed, updatedNumOfUsersFollowed);
	}
}
