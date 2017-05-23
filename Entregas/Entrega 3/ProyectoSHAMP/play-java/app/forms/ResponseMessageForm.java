package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class ResponseMessageForm {
	
	@Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String message_from;
    
	@Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String message_to;
    
	@Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String message_subject;
	@Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=255,message="La longitud maxima es 255 caracteres")
    public String message_content;
    
    

}
