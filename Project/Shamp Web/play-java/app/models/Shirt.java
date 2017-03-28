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

@Table(name="shirt")
@Entity
public class Shirt extends Model {
    
    
    public Shirt()
    {
        shirt_size = Shirt.getShirtSize().get(0);
        shirt_color = Shirt.getShirtColor().get(0);
        shirt_sex = Shirt.getShirtSex().get(0);
        shirt_price = 10;
    }
    @Id
    public long shirt_id;
    
    @ManyToOne
    @Column(nullable=false)
    public User user_id;
    
    @ManyToOne
    @Column(nullable=false)
    public Order order_id;
    
    public String shirt_size;
    
    public String shirt_color;
    
    public String shirt_sex;
    
    public double shirt_price;
    
    public void addStampPrice(Stamp temp)
    {
        shirt_price += temp.stamp_price;
    }
    
    public void removeStampPrice(Stamp temp)
    {
        shirt_price -= temp.stamp_price;
    }
    
    public static Finder<Long,Shirt> find = new Finder<Long,Shirt>(Long.class, Shirt.class);
    
    
    public static List<String> getShirtSize ()
    {
        List<String> response = new ArrayList<String>();
        
        response.add("XS");
        response.add("S");
        response.add("M");
        response.add("L");
        response.add("XL");
        
        return response;
    }
    
    public static List<String> getShirtColor ()
    {
        List<String> response = new ArrayList<String>();
        
        response.add("Blanco");
        response.add("Negro");
        response.add("Rojo");
        response.add("Azul");
        response.add("Amarillo");
        response.add("Verde");
        
        return response;
    }
    
    public static List<String> getShirtSex ()
    {
        List<String> response = new ArrayList<String>();
        
        response.add("Mujer");
        response.add("Hombre");
        
        return response;
    }
    
    
}