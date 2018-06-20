package com.spd.purchase;

public class PurchaseModel {
	private Long id;
	private Long customerId;
	private String productName;

	public PurchaseModel() {
	}

	public PurchaseModel(Purchase purchase) {
		id = purchase.getId();
		customerId = purchase.getCustomerId();
		productName = purchase.getProductName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
