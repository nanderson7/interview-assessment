package io.integral.model;

import java.util.Collections;
import java.util.Set;

public class Timeline {

	private final long id;
	private final Set<Message> messages;

	public Timeline(long id, Set<Message> messages) {
		super();
		this.id = id;
		this.messages = messages;
	}

	public long getId() {
		return id;
	}

	public Set<Message> getMessages() {
		return Collections.unmodifiableSet(messages);
	}

	public boolean addMessage(Message message) {
		return messages.add(message);
	}

	public boolean removeMessage(Message message) {
		return messages.remove(message);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
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
		Timeline other = (Timeline) obj;
		if (id != other.id)
			return false;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Timeline [messages=" + messages + "]";
	}

}
