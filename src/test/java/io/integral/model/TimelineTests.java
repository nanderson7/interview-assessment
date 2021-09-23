package io.integral.model;

import java.time.LocalDateTime;
import java.util.HashSet;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class TimelineTests {

	private Timeline timeline;
	@Mock
	private Message mockMessage;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		this.timeline = new Timeline(1, new HashSet<>());
	}
	
	@Test
	void testGetMessages_returnsUnmodifiableSet() {
		Assertions.assertThrows(UnsupportedOperationException.class, 
				() -> timeline.getMessages().add(new Message(1, "Howdy Matt", LocalDateTime.now())));
	}

	@Test
	void testAddMessage_returnsTrue_whenMessageIsSuccessfullyAdded_andIncreasesSizeOfMessagesByOne() {
		int initialNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(initialNumOfMessages == 0);
		
		boolean result = timeline.addMessage(mockMessage);
		Assertions.assertTrue(result);
		
		int updatedNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(updatedNumOfMessages == 1);
	}
	
	@Test
	void testAddMessage_returnsFalse_whenSameMessageIsAddedASecondTime_andSizeOfMessagesDoesNotChange() {
		timeline.addMessage(mockMessage);
		int initialNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(initialNumOfMessages == 1);
		
		boolean result = timeline.addMessage(mockMessage);
		Assertions.assertFalse(result);
		
		int updatedNumOfMessages = timeline.getMessages().size();
		Assertions.assertEquals(initialNumOfMessages, updatedNumOfMessages);
	}
	
	@Test
	void testRemoveMessage_returnsTrue_whenMessageIsSuccessfullyRemoved_andDecreasesSizeOfMessagesByOne() {
		timeline.addMessage(mockMessage);
		int initialNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(initialNumOfMessages == 1);
		
		boolean result = timeline.removeMessage(mockMessage);
		Assertions.assertTrue(result);
		
		int updatedNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(updatedNumOfMessages == 0);
	}
	
	@Test
	void testRemoveMessage_returnsFalse_whenSameMessageIsRemovedASecondTime_andSizeOfMessagesDoesNotChange() {
		timeline.addMessage(mockMessage);
		timeline.removeMessage(mockMessage);
		int initialNumOfMessages = timeline.getMessages().size();
		Assertions.assertTrue(initialNumOfMessages == 0);
		
		boolean result = timeline.removeMessage(mockMessage);
		Assertions.assertFalse(result);
		
		int updatedNumOfMessages = timeline.getMessages().size();
		Assertions.assertEquals(initialNumOfMessages, updatedNumOfMessages);
	}
}
