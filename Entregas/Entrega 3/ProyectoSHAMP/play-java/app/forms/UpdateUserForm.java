package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class UpdateUserForm{
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String user_country;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String user_city;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String user_address;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String phone_number;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MinLength(value=16,message="La longitud minima es 16 caracteres")
    @Constraints.MaxLength(value=16,message="La longitud maxima es 16 caracteres")
    public String credit_card;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String name_card;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.Pattern(value="[0-3][0-9]/[01][0-9]/\\d{4}")
    public String expiration_date;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MinLength(value=3,message="La longitud minima es 3 caracteres")
    @Constraints.MaxLength(value=3,message="La longitud maxima es 3 caracteres")
    public String cvv;
    
    public List<ValidationError> validate(){
        
        List<ValidationError> errors = new ArrayList<ValidationError>();
        return errors.isEmpty() ? null : errors;
        
    }
}