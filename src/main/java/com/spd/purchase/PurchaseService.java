package com.spd.purchase;

import java.util.Optional;

public interface PurchaseService {
	Optional<PurchaseModel> getPurchase(Long purchaseId);
}
