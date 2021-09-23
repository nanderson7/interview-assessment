package io.integral.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.integral.exception.MessageException;
import io.integral.model.Message;
import io.integral.model.Timeline;
import io.integral.model.User;

class PublishingServiceTests {

	private PublishingService publishingService;

	@Mock
	private User mockUser;
	@Mock
	private Message mockMessage;
	private Timeline timeline;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		this.publishingService = new PublishingService();
		this.timeline = new Timeline(1, new HashSet<>());
		when(mockMessage.getTimePublished()).thenReturn(LocalDateTime.now());
	}

	@Test
	void testPublishMessage_callsUser_getTimeline() {
		when(mockUser.getTimeline()).thenReturn(timeline);
		publishingService.publishMessage(mockUser, mockMessage);

		verify(mockUser, times(1)).getTimeline();
	}

	@Test
	void testPublishMessage_throwsMessageNotAddedException_whenMessageHasAlreadyBeenAdded() {
		when(mockUser.getTimeline()).thenReturn(timeline);
		publishingService.publishMessage(mockUser, mockMessage);
		
		Assertions.assertThrows(MessageException.class, 
				() -> publishingService.publishMessage(mockUser, mockMessage),
				"Message could not be added because it already exists.");
	}
}
