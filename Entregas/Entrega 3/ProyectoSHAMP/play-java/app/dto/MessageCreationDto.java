package dto;

import java.util.Date;

public class MessageCreationDto 
{
	
	 private String message_to;
	 private String message_subject;
	 private String message_content;
	 private long parent_message;
	 
	 public MessageCreationDto()
	 {
		 parent_message = -1;
	 }

	public String getMessage_to() {
		return message_to;
	}

	public void setMessage_to(String message_to) {
		this.message_to = message_to;
	}

	public String getMessage_subject() {
		return message_subject;
	}

	public void setMessage_subject(String message_subject) {
		this.message_subject = message_subject;
	}

	public String getMessage_content() {
		return message_content;
	}

	public void setMessage_content(String message_content) {
		this.message_content = message_content;
	}

	public long getParent_message() {
		return parent_message;
	}

	public void setParent_message(long parent_message) {
		this.parent_message = parent_message;
	}
}
