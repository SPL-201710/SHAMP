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

@Table(name="stamp_shirt")
@Entity
public class StampShirt extends Model {
    
    @Id
    public long stamp_shirt_id;
    
    @ManyToOne
    @Column(nullable=false)
    public Shirt shirt_id;
    
    @ManyToOne
    @Column(nullable=false)
    public Stamp stamp_id;
    
    public String stamp_size;
    
    public String stamp_location;
    
    public String name;
    
    public Date creation_date;
    
    public boolean active;
    
    
    public static Finder<Long,StampShirt> find = new Finder<Long,StampShirt>(Long.class, StampShirt.class);

    public static List<String> getStampSize ()
    {
        List<String> response = new ArrayList<String>();
        
        response.add("Pequena");
        response.add("Mediana");
        response.add("Grande");
        
        return response;
    }
    
    public static List<String> getStampLocation ()
    {
        List<String> response = new ArrayList<String>();
        
        response.add("Pecho");
        response.add("Espalda");
        response.add("Brazo Izquierdo");
        response.add("Brazo Derecho");
        
        return response;
    }
    
}