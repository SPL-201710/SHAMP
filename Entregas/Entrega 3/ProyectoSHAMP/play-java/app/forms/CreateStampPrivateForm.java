package forms;

import play.data.validation.Constraints;

public class CreateStampPrivateForm {
	
	@Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=20,message="La longitud maxima es 20 caracteres")
    public String stamp_name;
    
    @Constraints.Required(message="Campo Requerido")
    public String stamp_category;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=50,message="La longitud maxima es 50 caracteres")
    public String stamp_short_description;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=255,message="La longitud maxima es 255 caracteres")
    public String stamp_long_description;
    
    @Constraints.Required(message="Campo Requerido")
    public double stamp_price;
    
    public String stamp_buyer;

}
