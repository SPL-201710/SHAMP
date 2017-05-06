package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Table(name="stamps")
public class Stamp extends Model 
{
    @Id
    public long stamp_id;
    
    @ManyToOne
    @Column(name="user_id")
    public User user_id;
    
    @ManyToOne
    public Categories category_id;
    
    
    public int stamp_status;

    @Constraints.MaxLength(value=20,message="validation.maxLength")
    @Column(nullable=false, length=20)
    public String stamp_name;
    
    @Constraints.MaxLength(value=50,message="validation.maxLength")
    @Column(nullable=false, length=50)
    public String stamp_short_description;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(nullable=false, length=255)
    public String stamp_long_description;
    
    @Column(nullable=false)
    public double stamp_price;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_small_image_path;
    
    @Constraints.MaxLength(value=255,message="validation.maxLength")
    @Column(length=255)
    public String stamp_large_image_path;
    
    public String name;
    
    public boolean active;
    
    public Date creation_date;
    
    
    public static Finder<Long,Stamp> find = new Finder<Long,Stamp>(Long.class, Stamp.class);
    
    
}