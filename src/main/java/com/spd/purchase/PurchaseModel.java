package com.spd.purchase;

public class PurchaseModel {
	private Integer purchaseId;
	private Integer customerId;
	private String productName;

	public PurchaseModel() {
	}

	public PurchaseModel(Purchase purchase) {
		purchaseId = purchase.getId();
		customerId = purchase.getCustomerId();
		productName = purchase.getProductName();
	}

	public Integer getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
}
