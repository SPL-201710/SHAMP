package dto;

import java.util.Date;

public class ReportDto 
{
	private String userMail;
	private long orderId;
	private Date orderDate;
	private long stampId;
	private String artistMail;
	private int quantity;
	private double value;
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public long getStampId() {
		return stampId;
	}
	public void setStampId(long stampId) {
		this.stampId = stampId;
	}
	public String getArtistMail() {
		return artistMail;
	}
	public void setArtistMail(String artistMail) {
		this.artistMail = artistMail;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
}
