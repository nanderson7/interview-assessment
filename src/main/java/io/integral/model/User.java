package io.integral.model;

import java.util.Collections;
import java.util.Set;

public class User {

	private final long id;
	private final Timeline timeline;
	private final Set<User> followedUsers;
	private String firstName;
	private String lastName;

	public User(long id, Timeline timeline, Set<User> followedUsers, String firstName, String lastName) {
		super();
		this.id = id;
		this.timeline = timeline;
		this.followedUsers = followedUsers;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public long getId() {
		return id;
	}

	public Timeline getTimeline() {
		return timeline;
	}

	public Set<User> getFollowedUsers() {
		return Collections.unmodifiableSet(followedUsers);
	}
	
	public boolean followUser(User user) {
		return followedUsers.add(user);
	}
	
	public boolean unfollowUser(User user) {
		return followedUsers.remove(user);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((followedUsers == null) ? 0 : followedUsers.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (id != other.id)
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (followedUsers == null) {
			if (other.followedUsers != null)
				return false;
		} else if (!followedUsers.equals(other.followedUsers))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [usersFollowed=" + followedUsers + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

}
