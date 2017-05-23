package forms;

import java.util.*;
import play.data.format.*;
import play.data.validation.*;
import models.*;

public class SignInForm{
    
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String mail;
    
    @Constraints.Required(message="Campo Requerido")
    @Constraints.MaxLength(value=100,message="La longitud maxima es 100 caracteres")
    public String password;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
    
}