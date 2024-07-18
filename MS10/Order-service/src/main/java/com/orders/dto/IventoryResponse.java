package com.orders.dto;

public class IventoryResponse {
	
	private String skuCode;
	private boolean isInStock;
	
	// Private constructor to enforce the use of the builder
	private IventoryResponse(Builder builder) {
		this.skuCode = builder.skuCode;
		this.isInStock = builder.isInStock;
	}
	
	// Getters
	public String getSkuCode() {
		return skuCode;
	}
	
	public boolean isInStock() {
		return isInStock;
	}

	// Static inner Builder class
	public static class Builder {
		private String skuCode;
		private boolean isInStock;

		public Builder skuCode(String skuCode) {
			this.skuCode = skuCode;
			return this;
		}

		public Builder isInStock(boolean isInStock) {
			this.isInStock = isInStock;
			return this;
		}

		public IventoryResponse build() {
			return new IventoryResponse(this);
		}
	}

	// Static factory method for creating a new Builder
	public static Builder builder() {
		return new Builder();
	}
	
	// Optionally include a no-args constructor for frameworks that need it
	public IventoryResponse() {}
}
