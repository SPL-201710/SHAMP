package dto;

import java.util.List;

public class OrderDto {

	  private Integer id;

	  private DetailOrderDto order;

	  private List<OrderShirtDto> products;

	  private Integer user_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DetailOrderDto getOrder() {
		return order;
	}

	public void setOrder(DetailOrderDto order) {
		this.order = order;
	}

	public List<OrderShirtDto> getProducts() {
		return products;
	}

	public void setProducts(List<OrderShirtDto> products) {
		this.products = products;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	  
	  
}
