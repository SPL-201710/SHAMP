package dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;

public class MessageDto {
	
	
	 private long message_id;
	 private String message_from;
	 private String message_to;
	 private String message_subject;
	 private String message_content;
	 private Date creation_date;
	 private long parent_message;
	 

	 public long getMessage_id() {
		return message_id;
	}

	public void setMessage_id(long message_id) {
		this.message_id = message_id;
	}

	public String getMessage_from() {
		return message_from;
	}

	public void setMessage_from(String message_from) {
		this.message_from = message_from;
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

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public long getParent_message() {
		return parent_message;
	}

	public void setParent_message(long parent_message) {
		this.parent_message = parent_message;
	}

	

}
