package io.integral.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import io.integral.model.Message;
import io.integral.model.Timeline;
import io.integral.model.User;

public class TimelineService {

	public String getTimeline(User user) {
		return buildTimeline(user);
	}

	private String buildTimeline(User user) {
		Set<User> followedUsers = user.getFollowedUsers();
		StringBuffer sb = new StringBuffer();
		appendMessages(sb, user);
		followedUsers.forEach(followedUser -> {
			appendMessages(sb, followedUser);
		});
		return sb.toString();
	}

	private void appendMessages(StringBuffer sb, User user) {
		Timeline userTimeline = user.getTimeline();
		Set<Message> messages = userTimeline.getMessages();
		String firstName = user.getFirstName();
		
		messages.forEach(message -> {
			sb.append(buildMessage(firstName, message));
			sb.append(System.getProperty("line.separator"));
		});
	}

	private String buildMessage(String firstName, Message message) {
		StringBuffer sb = new StringBuffer();
		sb.append(firstName);
		sb.append(" - ");
		sb.append(message.getMessage());
		sb.append(getTimeSinceMessage(message));
		return sb.toString();
	}

	private String getTimeSinceMessage(Message message) {
		LocalDateTime messagePublishedTime = message.getTimePublished();
		LocalDateTime now = LocalDateTime.now();

		long seconds = ChronoUnit.SECONDS.between(messagePublishedTime, now);
		long minutes = ChronoUnit.MINUTES.between(messagePublishedTime, now);
		long hours = ChronoUnit.HOURS.between(messagePublishedTime, now);
		if (seconds == 0 && minutes == 0) {
			return "(" + hours + " seconds ago)";
		} else if (seconds == 0 && hours == 0) {
			return "(" + minutes + " seconds ago)";
		} else {
			return "(" + seconds + " seconds ago)";
		}
	}
}
