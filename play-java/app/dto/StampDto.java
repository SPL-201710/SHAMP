package dto;

import java.util.Date;

public class StampDto {

	  private Boolean active;
	  private Date creationDate;
	  private String name;
	  private long id;
	  private String stampLargeImagePath;
	  private String stampLongDescription;
	  private String stampName;
	  private String stampPrice;
	  private String stampShortDescription;
	  private String stampSmallImagePath;
	  
	  private CategoryDto category;
	  private UserDto user;
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStampLargeImagePath() {
		return stampLargeImagePath;
	}
	public void setStampLargeImagePath(String stampLargeImagePath) {
		this.stampLargeImagePath = stampLargeImagePath;
	}
	public String getStampLongDescription() {
		return stampLongDescription;
	}
	public void setStampLongDescription(String stampLongDescription) {
		this.stampLongDescription = stampLongDescription;
	}
	public String getStampName() {
		return stampName;
	}
	public void setStampName(String stampName) {
		this.stampName = stampName;
	}
	public String getStampPrice() {
		return stampPrice;
	}
	public void setStampPrice(String stampPrice) {
		this.stampPrice = stampPrice;
	}
	public String getStampShortDescription() {
		return stampShortDescription;
	}
	public void setStampShortDescription(String stampShortDescription) {
		this.stampShortDescription = stampShortDescription;
	}
	public String getStampSmallImagePath() {
		return stampSmallImagePath;
	}
	public void setStampSmallImagePath(String stampSmallImagePath) {
		this.stampSmallImagePath = stampSmallImagePath;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}

	 
	  
}
