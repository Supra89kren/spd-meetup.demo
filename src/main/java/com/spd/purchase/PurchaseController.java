package com.spd.purchase;

import ma.glasnost.orika.MapperFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseController {
	private final MapperFacade mapperFacade;
	private final PurchaseService purchaseService;

	public PurchaseController(MapperFacade mapperFacade, PurchaseService purchaseService) {
		this.mapperFacade = mapperFacade;
		this.purchaseService = purchaseService;
	}

	@GetMapping("/api/purchases/customers/{customerId}")
	public ResponseEntity<List<PurchaseDto>> findPurchasesByCustomerId(@PathVariable Integer customerId) {
		List<PurchaseModel> purchasesByCustomerId = purchaseService.findPurchasesByCustomerId(customerId);
		return ResponseEntity.ok(mapperFacade.mapAsList(purchasesByCustomerId, PurchaseDto.class));
	}

}
