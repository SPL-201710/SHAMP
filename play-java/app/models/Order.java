package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Table(name="user_orders")
@Entity
public class Order extends Model {
    
    
    @Id
    public long order_id;
    
    @ManyToOne
    @Column(nullable=false)
    public User user_id;
    
    @Column(nullable=false)
    public String order_status;
    
    public Date order_date;
    
    public String name;
    
    public boolean active;
    
    public Date creation_date;
    
    
    
    public static Finder<Long,Order> find = new Finder<Long,Order>(Long.class, Order.class);

    
    
}