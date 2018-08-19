package com.spd.purchase;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Override
	public List<PurchaseModel> findPurchasesByCustomerId(Integer customerId) {
		return null;
	}
}
