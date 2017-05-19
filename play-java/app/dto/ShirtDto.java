package dto;

import java.util.Date;

public class ShirtDto {

	  private Boolean active;
	  private Date creationDate;
	  private String name;
	  private long id;
	  private String shirtColor;
	  private String shirtLargeImagePath;
	  private String shirtSex;
	  private String shirtSize;
	  private String shirtSmallImagePath;
	  private String shirtPrice;
	  
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
	public String getShirtColor() {
		return shirtColor;
	}
	public void setShirtColor(String shirtColor) {
		this.shirtColor = shirtColor;
	}
	public String getShirtLargeImagePath() {
		return shirtLargeImagePath;
	}
	public void setShirtLargeImagePath(String shirtLargeImagePath) {
		this.shirtLargeImagePath = shirtLargeImagePath;
	}
	public String getShirtSex() {
		return shirtSex;
	}
	public void setShirtSex(String shirtSex) {
		this.shirtSex = shirtSex;
	}
	public String getShirtSize() {
		return shirtSize;
	}
	public void setShirtSize(String shirtSize) {
		this.shirtSize = shirtSize;
	}
	public String getShirtSmallImagePath() {
		return shirtSmallImagePath;
	}
	public void setShirtSmallImagePath(String shirtSmallImagePath) {
		this.shirtSmallImagePath = shirtSmallImagePath;
	}
	public String getShirtPrice() {
		return shirtPrice;
	}
	public void setShirtPrice(String shirtPrice) {
		this.shirtPrice = shirtPrice;
	}

	  
	  
}
