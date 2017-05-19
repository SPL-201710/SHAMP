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
    public User user_id;
    
    public String shirt_size;
    
    public String shirt_color;
    
    public String shirt_sex;
    
    public double shirt_price;
    
    public boolean active;
    
    public String name;
    
    public Date creation_date;
    
    public String shirt_small_image_path;
    
    public String shirt_large_image_path;
    
    
    
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
        response.add("Unisex");
        
        return response;
    }

	public long getShirt_id() {
		return shirt_id;
	}

	public void setShirt_id(long shirt_id) {
		this.shirt_id = shirt_id;
	}

	public String getShirt_size() {
		return shirt_size;
	}

	public void setShirt_size(String shirt_size) {
		this.shirt_size = shirt_size;
	}

	public String getShirt_color() {
		return shirt_color;
	}

	public void setShirt_color(String shirt_color) {
		this.shirt_color = shirt_color;
	}

	public String getShirt_sex() {
		return shirt_sex;
	}

	public void setShirt_sex(String shirt_sex) {
		this.shirt_sex = shirt_sex;
	}

	public double getShirt_price() {
		return shirt_price;
	}

	public void setShirt_price(double shirt_price) {
		this.shirt_price = shirt_price;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreation_date() {
		return creation_date;
	}

	public void setCreation_date(Date creation_date) {
		this.creation_date = creation_date;
	}

	public String getShirt_small_image_path() {
		return shirt_small_image_path;
	}

	public void setShirt_small_image_path(String shirt_small_image_path) {
		this.shirt_small_image_path = shirt_small_image_path;
	}

	public String getShirt_large_image_path() {
		return shirt_large_image_path;
	}

	public void setShirt_large_image_path(String shirt_large_image_path) {
		this.shirt_large_image_path = shirt_large_image_path;
	}
    
    
    
    
}