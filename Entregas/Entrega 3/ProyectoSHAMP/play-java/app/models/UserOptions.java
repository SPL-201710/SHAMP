package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;

@Table(name="user_options")
@Entity
public class UserOptions extends Model {
    
    @Id
    public long user_option_id;
    
    @Constraints.MinLength(value=1, message="validation.minLength")
    @Constraints.MaxLength(value=1, message="validation.maxLength")
    @Column(nullable=false, length=1)
    public int user_type_option;
    
    @Constraints.MinLength(value=1, message="validation.minLength")
    @Constraints.MaxLength(value=1, message="validation.maxLength")
    @Column(nullable=false, length=1)
    public int user_object_option;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String user_option_name;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String user_option_url;
    
    public static Finder<Long,UserOptions> find = new Finder<Long,UserOptions>(Long.class, UserOptions.class);
    
    public List<UserOptions> findOptionsByType(int userType)
    {
        return find.where().eq("user_type_option",userType).findList();
    }
    
    
}