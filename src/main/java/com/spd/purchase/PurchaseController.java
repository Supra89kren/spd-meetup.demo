package com.spd.purchase;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class PurchaseController {
	private PurchaseService purchaseService;
	private MapperFacade mapperFacade;

	@Autowired
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}

	@Autowired
	public void setMapperFacade(MapperFacade mapperFacade) {
		this.mapperFacade = mapperFacade;
	}

	@GetMapping("/api/purchases/customers/{customerId}")
	private ResponseEntity<List<PurchaseDto>> findPurchasesByCustomerId(@PathVariable Long customerId) {
		List<PurchaseModel> purchases = purchaseService.findPurchasesByCustomerId(customerId);
		if (CollectionUtils.isEmpty(purchases)) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(mapperFacade.mapAsList(purchases, PurchaseDto.class));
	}
}
