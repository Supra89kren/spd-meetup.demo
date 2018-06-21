package com.spd.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Long>{

	List<Purchase> findPurchasesByCustomerId(Long customerId);
}
