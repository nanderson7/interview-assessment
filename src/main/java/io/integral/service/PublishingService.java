package io.integral.service;

import io.integral.exception.MessageException;
import io.integral.model.Message;
import io.integral.model.Timeline;
import io.integral.model.User;

public class PublishingService {

	private static final String MESSAGE_ALREADY_EXISTS_MESSAGE = "Message could not be added because it already exists.";
	
	public void publishMessage(User user, Message message) {
		Timeline userTimeline = user.getTimeline();
		boolean messageAdded = userTimeline.addMessage(message);
		if (!messageAdded) {
			throw new MessageException(MESSAGE_ALREADY_EXISTS_MESSAGE);
		}
		// TODO: add logging for successful addition of message
	}
}
