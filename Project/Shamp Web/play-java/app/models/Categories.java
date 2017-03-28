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
    
    public static Finder<Long,Categories> find = new Finder<Long,Categories>(Long.class, Categories.class);
    
    public static List<Categories> getCategories()
    {
        return find.where().gt("category_id",0).findList();
    }
    
}