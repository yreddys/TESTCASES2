package com.orders.dto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Orders {
	@Id
	@GeneratedValue

	private Long orderId;
	private String skuCode;
	private BigDecimal price;
	private Integer quantity;
	private String customerEmail;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getSkuCode() {
		return skuCode;
	}

	public void setSkuCode(String skuCode) {
		this.skuCode = skuCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public Orders(Long orderId, String skuCode, BigDecimal price, Integer quantity, String customerEmail) {

		this.orderId = orderId;
		this.skuCode = skuCode;
		this.price = price;
		this.quantity = quantity;
		this.customerEmail = customerEmail;
	}

	public Orders() {
	}

	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", skuCode=" + skuCode + ", price=" + price + ", quantity=" + quantity
				+ ", customerEmail=" + customerEmail + "]";
	}

}
