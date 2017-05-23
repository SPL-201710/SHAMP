package dto;

public class OrderShirtDto 
{
	private long stamp_id;
	private long shirt_id;
	private Double quantity;
	private String shirt_size;
    private String shirt_sex;
    private String shirt_location;
    private String text_font;
    private String text_size;
    private String text_color;
    private String text;
    private String stamp_url;
    
	public long getStamp_id() {
		return stamp_id;
	}
	public void setStamp_id(long stamp_id) {
		this.stamp_id = stamp_id;
	}
	public long getShirt_id() {
		return shirt_id;
	}
	public void setShirt_id(long shirt_id) {
		this.shirt_id = shirt_id;
	}
	public Double getQuantity() {
		return quantity;
	}
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	public String getShirt_size() {
		return shirt_size;
	}
	public void setShirt_size(String shirt_size) {
		this.shirt_size = shirt_size;
	}
	public String getShirt_sex() {
		return shirt_sex;
	}
	public void setShirt_sex(String shirt_sex) {
		this.shirt_sex = shirt_sex;
	}
	public String getShirt_location() {
		return shirt_location;
	}
	public void setShirt_location(String shirt_location) {
		this.shirt_location = shirt_location;
	}
	public String getText_font() {
		return text_font;
	}
	public void setText_font(String text_font) {
		this.text_font = text_font;
	}
	public String getText_size() {
		return text_size;
	}
	public void setText_size(String text_size) {
		this.text_size = text_size;
	}
	public String getText_color() {
		return text_color;
	}
	public void setText_color(String text_color) {
		this.text_color = text_color;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getStamp_url() {
		return stamp_url;
	}
	public void setStamp_url(String stamp_url) {
		this.stamp_url = stamp_url;
	}
	
	
}
