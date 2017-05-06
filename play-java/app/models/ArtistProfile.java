package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Table(name="user_profile")
@Entity
public class ArtistProfile extends Model 
{
    @Id
    public long profile_id;
    
    @ManyToOne
    public User user_id;

    public int profile_status;
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(nullable=false, length=255)
    public String user_country;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String user_city;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String phone_number;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(nullable=false, length=255)
    public String user_address;
    
    @Constraints.MaxLength(value=16,message="validation.maxLength")
    @Column(nullable = false,length=255)
    public String user_profile;
    
    @Column(nullable=false, length=255)
    public String user_image_path;
    
    public boolean active;
    
    public Date creation_date;
    
    public String name;
    
    
    public static Finder<Long,ArtistProfile> find = new Finder<Long,ArtistProfile>(Long.class, ArtistProfile.class);
    
}