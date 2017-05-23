package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class UpdateProfileForm{
    
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
    
}