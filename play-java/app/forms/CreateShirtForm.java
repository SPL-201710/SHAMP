package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;
import com.fasterxml.jackson.annotation.*;
import play.data.validation.*;

public class CreateShirtForm{
    
    @Constraints.Required(message="Campo Requerido")
    public String shirt_name;
    
    @Constraints.Required(message="Campo Requerido")
    public String shirt_color;
    
    @Constraints.Required(message="Campo Requerido")
    public String shirt_sex;
    
    @Constraints.Required(message="Campo Requerido")
    public double shirt_price;
    
}