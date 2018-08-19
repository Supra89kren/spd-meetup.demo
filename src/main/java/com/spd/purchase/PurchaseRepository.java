package com.spd.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
	List<Purchase> findPurchasesByCustomerId(Integer customerId);
}
