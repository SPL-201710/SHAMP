package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Table(name="user_billing")
public class UserBilling extends Model 
{
    @Id
    public long billing_id;
    
    @ManyToOne
    public User user_id;
    
    public int billing_status;

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
    @Column(nullable = false,length=100)
    public String user_credit_card;
    
    @Column(nullable=false, length=128)
    public String name_card;
    
    @Column(nullable=false)
    public Date expiration_date;
    
    @Column(nullable=false)
    public String cvv;
    
    public static Finder<Long,UserBilling> find = new Finder<Long,UserBilling>(Long.class, UserBilling.class);
    
    public void updateBilling(String country, 
    String city,
    String phone, 
    String address, 
    String card,
    String name,
    Date date, 
    String p_cvv)
    {
        user_country = country;
        user_city = city;
        phone_number = phone;
        user_address = address;
        user_credit_card = card;
        name_card = name;
        expiration_date = date;
        cvv = p_cvv;
    }
    
}