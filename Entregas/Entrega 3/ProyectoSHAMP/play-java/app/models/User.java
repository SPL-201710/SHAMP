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
    
    public boolean base_datos;
    
    public void updatePassword(String newPassword)
    {
        user_password = newPassword;
    }
    
    public static Finder<Long,User> find = new Finder<Long,User>(Long.class, User.class);
    
    
    
}