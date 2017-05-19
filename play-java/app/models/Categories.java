package models;


import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;
import java.util.List;


@Table(name="stamp_categories")
@Entity
public class Categories extends Model {
    
    @Id
    public long category_id;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String category_name;
    
    @Constraints.MaxLength(value=100,message="validation.maxLength")
    @Column(nullable=false, length=100)
    public String category_description_url;
    
    public boolean active;
    
    public Date creation_date;
    
    public String name;
    
    public static Finder<Long,Categories> find = new Finder<Long,Categories>(Long.class, Categories.class);
    
    public static List<Categories> getCategories()
    {
        return find.where().gt("category_id",0).findList();
    }

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
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
    
    
    
}