package com.spd.purchase;

public class PurchaseDto {
	private Long id;
	private Long customerId;
	private String productName;

	public Long getId() {
		return id;
	}

	public PurchaseDto setId(Long id) {
		this.id = id;
		return this;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public PurchaseDto setCustomerId(Long customerId) {
		this.customerId = customerId;
		return this;
	}

	public String getProductName() {
		return productName;
	}

	public PurchaseDto setProductName(String productName) {
		this.productName = productName;
		return this;
	}
}
