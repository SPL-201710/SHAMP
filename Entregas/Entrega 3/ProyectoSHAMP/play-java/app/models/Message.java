package models;

import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import com.avaje.ebean.Model.Finder;

import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Table(name="messages")
@Entity
public class Message extends Model {
	
	 @Id
	 public long message_id;
	 
	 @Column(nullable=false)
	 public int message_status;
	 
	 
	 @Column(name="message_from")
	 public long message_from;
	 
	 
	 @Column(name="message_to")
	 public long message_to;
	 
	 @Column(name="message_subject")
	 public String message_subject;
	 
	 @Column(name="message_content")
	 public String message_content;
	 
	 
	 @Column(nullable=false)
	 public Date creation_date;
	 
	 public long parent_message;
	 
	 public static Finder<Long,Message> find = new Finder<Long,Message>(Long.class, Message.class);
	 
	 public User getUserTo()
	 {
		 return User.find.where().eq("user_id", message_to).findUnique();
	 }
	 
	 public User getUserFrom()
	 {
		 return User.find.where().eq("user_id", message_from).findUnique();
	 }
	 
	 

}
