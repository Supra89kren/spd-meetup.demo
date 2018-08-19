package com.spd.purchase;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	private final PurchaseRepository purchaseRepository;

	public PurchaseServiceImpl(PurchaseRepository purchaseRepository) {
		this.purchaseRepository = purchaseRepository;
	}

	@Override
	public List<PurchaseModel> findPurchasesByCustomerId(Integer customerId) {
		return purchaseRepository.findPurchasesByCustomerId(customerId).stream()
				.map(PurchaseModel::new)
				.collect(Collectors.toList());
	}
}
