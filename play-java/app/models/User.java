package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Table(name="users")
@Entity
public class User extends Model {
    
    @Id
    public long user_id;
    
    @Constraints.Required(message="validation.required")
    @Constraints.Email(message="validation.email")
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(unique=true, nullable=false, length=100)
    public String user_mail;
    
    @Constraints.MinLength(value=8, message="validation.minLength")
    @Constraints.MaxLength(value=40, message="validation.maxLength")
    @Column(nullable=false, length=128)
    public String user_password;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String user_first_name;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String user_last_name;
    
    public int user_type;
    
    public int user_status;
    
    public boolean active;
    
    public Date creation_date;
    
    public String name;
    
    public String username;
    
    public void updatePassword(String newPassword)
    {
        user_password = newPassword;
    }
    
    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}

	public String getUser_mail() {
		return user_mail;
	}

	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_first_name() {
		return user_first_name;
	}

	public void setUser_first_name(String user_first_name) {
		this.user_first_name = user_first_name;
	}

	public String getUser_last_name() {
		return user_last_name;
	}

	public void setUser_last_name(String user_last_name) {
		this.user_last_name = user_last_name;
	}

	public int getUser_type() {
		return user_type;
	}

	public void setUser_type(int user_type) {
		this.user_type = user_type;
	}

	public int getUser_status() {
		return user_status;
	}

	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
    
    
    
}