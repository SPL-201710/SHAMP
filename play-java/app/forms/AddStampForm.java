package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;

public class AddStampForm{
    
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String stamp_size;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String stamp_location;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String stamp_name;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String stamp_id;
    
}