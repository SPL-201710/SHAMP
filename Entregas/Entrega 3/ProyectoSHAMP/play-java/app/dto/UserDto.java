package dto;

public class UserDto {
	
	private String email;
	  private long id;
	  private String name;
	  private String surname;
	  private String username;
	  private Boolean base_datos;

	  public String getEmail() {
	    return this.email;
	  }

	  public long getId() {
	    return this.id;
	  }

	  public String getName() {
	    return this.name;
	  }

	  public String getSurname() {
	    return this.surname;
	  }

	  public String getUsername() {
	    return this.username;
	  }

	  public void setEmail(final String email) {
	    this.email = email;
	  }

	  public void setId(long id) {
	    this.id = id;
	  }

	  public void setName(final String name) {
	    this.name = name;
	  }

	  public void setSurname(final String surname) {
	    this.surname = surname;
	  }

	  public void setUsername(final String username) {
	    this.username = username;
	  }

		public Boolean getBase_datos() {
			return base_datos;
		}
	
		public void setBase_datos(Boolean base_datos) {
			this.base_datos = base_datos;
		}
	
}
