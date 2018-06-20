package com.spd.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	private PurchaseRepository purchaseRepository;

	@Autowired
	public void setPurchaseRepository(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public Optional<PurchaseModel> getPurchase(Long purchaseId) {
		return purchaseRepository.findById(purchaseId)
				.map(PurchaseModel::new);
	}
}
