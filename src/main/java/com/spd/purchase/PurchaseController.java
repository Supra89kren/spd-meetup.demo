package com.spd.purchase;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


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

	@GetMapping("/api/purchases/{purchaseId}")
	private ResponseEntity<PurchaseDto> getPurchaseInfo(@PathVariable Long purchaseId) {
		return purchaseService.getPurchase(purchaseId)
				.map(model -> mapperFacade.map(model, PurchaseDto.class))
				.map(ResponseEntity::ok)
				.orElseGet(ResponseEntity.notFound()::build);
	}
}
