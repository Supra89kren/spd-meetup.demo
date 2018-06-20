package com.spd.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
interface PurchaseRepository extends JpaRepository<Purchase, Long>{

}
