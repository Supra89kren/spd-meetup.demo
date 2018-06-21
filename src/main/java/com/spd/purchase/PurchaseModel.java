package com.spd.purchase;

public class PurchaseModel {
	private Long purchaseId;
	private Long customerId;
	private String productName;

	public PurchaseModel() {
	}

	public PurchaseModel(Purchase purchase) {
		purchaseId = purchase.getId();
		customerId = purchase.getCustomerId();
		productName = purchase.getProductName();
	}

	public Long getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Long purchaseId) {
		this.purchaseId = purchaseId;
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
