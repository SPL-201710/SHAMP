package dto;

public class ShirtDto 
{
	
	private long shirt_id;
	private String name;
    private String shirt_color;
    private String shirt_sex;
    private double shirt_price;
    private String shirt_small_image_path;
    private String shirt_large_image_path;
	
	public String getShirt_small_image_path() {
		return shirt_small_image_path;
	}

	public void setShirt_small_image_path(String shirt_small_image_path) {
		this.shirt_small_image_path = shirt_small_image_path;
	}

	public String getShirt_large_image_path() {
		return shirt_large_image_path;
	}

	public void setShirt_large_image_path(String shirt_large_image_path) {
		this.shirt_large_image_path = shirt_large_image_path;
	}

	public long getShirt_id() {
		return shirt_id;
	}

	public void setShirt_id(long shirt_id) {
		this.shirt_id = shirt_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getShirt_color() {
		return shirt_color;
	}

	public void setShirt_color(String shirt_color) {
		this.shirt_color = shirt_color;
	}

	public String getShirt_sex() {
		return shirt_sex;
	}

	public void setShirt_sex(String shirt_sex) {
		this.shirt_sex = shirt_sex;
	}

	public double getShirt_price() {
		return shirt_price;
	}

	public void setShirt_price(double shirt_price) {
		this.shirt_price = shirt_price;
	}

	
}
