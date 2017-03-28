package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class SignUpArtistForm{
    
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String first_name;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String last_name;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.Email(message="Se debe introducir una direccion de correo valida")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String mail;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.Email(message="Se debe introducir una direccion de correo valida")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String mail_confirmation;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MinLength(value=8, message="La longitud minima es 8 caracteres")
    @Constraints.MaxLength(value=40,message="La longitud maxima es 40 caracteres")
    public String password;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=40,message="La longitud maxima es 40 caracteres")
    public String password_confirmation;
    
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
    @Constraints.MaxLength(value=255,message="La longitud maxima es 100 caracteres")
    public String user_profile;
    
    public List<ValidationError> validate(){
        
        List<ValidationError> errors = new ArrayList<ValidationError>();
        
        if(!mail.equals(mail_confirmation))
            errors.add(new ValidationError("mail confirmacion", "El mail ingresado no coincide con la confirmacion"));
        
        
        User tempUser = User.find.where().eq("user_mail",mail).findUnique();
        
        if(tempUser!= null)
            errors.add(new ValidationError("mail","El mail utilizado ya existe"));
        
        
        if(!password.equals(password_confirmation))
            errors.add(new ValidationError("password confirmacion", "El password ingresado no coincide con la confirmacion"));
        
        
        if(first_name == null || first_name.equals(""))
            errors.add(new ValidationError("first_name","El nombre ingresado no es valido"));
            
         if(last_name == null || last_name.equals(""))
            errors.add(new ValidationError("last_name","El apellido ingresado no es valido"));    
        
        
        return errors.isEmpty() ? null : errors;
        
    }
}