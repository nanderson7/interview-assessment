package io.integral.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.integral.model.Message;
import io.integral.model.Timeline;
import io.integral.model.User;

class TimelineServiceTests {

	private TimelineService timelineService;
	@Mock
	private User mockUser;
	@Mock 
	private User mockUser2;
	@Mock
	private Timeline mockTimeline;
	@Mock
	private Message mockMessage;
	
	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		this.timelineService = new TimelineService();
		when(mockUser.getTimeline()).thenReturn(mockTimeline);
		
	}
	
	@Test
	void testGetTimeline_callsUserGetFollowedUsers() {
		timelineService.getTimeline(mockUser);
		
		verify(mockUser, times(1)).getFollowedUsers();
	}
	
	@Test
	void testGetTimeline_callsGetTimeline() {
		timelineService.getTimeline(mockUser);
		
		verify(mockUser, times(1)).getTimeline();
	}
	
	@Test
	void testGetTimeline_callsTimelineGetMessages() {
		timelineService.getTimeline(mockUser);
		
		verify(mockTimeline, times(1)).getMessages();
	}
	
	@Test
	void testGetTimeline_callsMessageGetMessageAndGetTimePublished_forEachMessage() {
		Set<Message> messages = new HashSet<>();
		messages.add(mockMessage);
		when(mockTimeline.getMessages()).thenReturn(messages);
		when(mockMessage.getTimePublished()).thenReturn(LocalDateTime.now());
		
		timelineService.getTimeline(mockUser);
		
		verify(mockMessage, times(1)).getMessage();
		verify(mockMessage, times(1)).getTimePublished();
	}
	
	@Test
	void testGetTimeline_callsGetTimeline_forEachUserFollowed() {
		Set<User> users = new HashSet<>();
		users.add(mockUser2);
		when(mockUser.getFollowedUsers()).thenReturn(users);
		when(mockUser2.getTimeline()).thenReturn(mockTimeline);
		
		timelineService.getTimeline(mockUser);
		
		verify(mockUser2, times(1)).getTimeline();
	}
	
	@Test
	void testGetTimeline_callsTimelineGetMessages_forEachUserFollowed_andForThemself() {
		Set<User> users = new HashSet<>();
		users.add(mockUser2);
		when(mockUser.getFollowedUsers()).thenReturn(users);
		when(mockUser2.getTimeline()).thenReturn(mockTimeline);
		
		timelineService.getTimeline(mockUser);
		
		verify(mockTimeline, times(2)).getMessages();
	}

	@Test
	void testGetTimeline_returnsCorrectString() {
		Set<User> users = new HashSet<>();
		Set<Message> messages = new HashSet<>();
		messages.add(new Message(1, "Hello World", LocalDateTime.now()));
		User user = new User(1, mockTimeline, new HashSet<>(), "Noah", "Anderson");
		users.add(user);
		when(mockTimeline.getMessages()).thenReturn(messages);
		
		String timelineMessages = timelineService.getTimeline(user);
		
		/*
		 * I realized just before pushing this to git that I should've used an 
		 * Iterator to loop through the Set of messages so I could use the 
		 * hasNext method of the iterator to determine if a line separator was needed.
		 */
		Assertions.assertEquals("Noah - Hello World(0 seconds ago)" + 
									System.getProperty("line.separator"), timelineMessages);
					
	}
}
