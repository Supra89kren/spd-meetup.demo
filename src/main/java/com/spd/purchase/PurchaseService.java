package com.spd.purchase;

import java.util.List;

public interface PurchaseService {
	List<PurchaseModel> findPurchasesByCustomerId(Integer customerId);
}
