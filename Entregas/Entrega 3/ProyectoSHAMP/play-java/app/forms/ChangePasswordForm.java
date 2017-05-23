package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;

public class ChangePasswordForm{
    
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String oldPassword;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String newPassword;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String password_confirmation;
    
    public List<ValidationError> validate()
    {
        List<ValidationError> errors = new ArrayList<ValidationError>();
        
        if(!newPassword.equals(password_confirmation))
            errors.add(new ValidationError("password confirmacion", "El password ingresado no coincide con la confirmacion"));
        
        return errors.isEmpty() ? null : errors;
        
    }
    
}