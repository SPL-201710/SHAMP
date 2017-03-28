package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;

@Table(name="order_shirt")
@Entity
public class OrderShirt extends Model {
    
    @Id
    public long order_shirt_id;
    
    @ManyToOne
    public Order order_id;
    
    @ManyToOne
    public Shirt shirt_id;
    
    public int shirt_quantity;
    
    
    public static Finder<Long,OrderShirt> find = new Finder<Long,OrderShirt>(Long.class, OrderShirt.class);

    
    
}