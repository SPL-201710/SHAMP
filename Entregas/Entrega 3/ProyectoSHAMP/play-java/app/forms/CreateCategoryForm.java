package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class CreateCategoryForm{
    
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=20,message="La longitud maxima es 20 caracteres")
    public String category_name;
    
    @Constraints.Required(message="Campo Requerido")
    public String category_description_url;
    
     public List<ValidationError> validate(){
        
        List<ValidationError> errors = new ArrayList<ValidationError>();
        
        Categories tempCategory = Categories.find.where().eq("category_name",category_name).findUnique();
        
        if(tempCategory!= null)
            errors.add(new ValidationError("Nombre","El nombre utilizado ya existe"));
        
        return errors.isEmpty() ? null : errors;
        
    }
    
    
}