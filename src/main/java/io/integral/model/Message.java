package io.integral.model;

import java.time.LocalDateTime;

public class Message {

	private final long id;
	private final String message;
	private final LocalDateTime timePublished;

	public Message(long id, String message, LocalDateTime timePublished) {
		super();
		this.id = id;
		this.message = message;
		this.timePublished = timePublished;
	}

	public long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public LocalDateTime getTimePublished() {
		return timePublished;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((timePublished == null) ? 0 : timePublished.hashCode());
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
		Message other = (Message) obj;
		if (id != other.id)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (timePublished == null) {
			if (other.timePublished != null)
				return false;
		} else if (!timePublished.equals(other.timePublished))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", timePublished=" + timePublished + "]";
	}

}
