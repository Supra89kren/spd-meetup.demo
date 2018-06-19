package com.spd.purchase;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController("/api/purchases")
public class PurchaseController {
	@GetMapping
	private ResponseEntity<List<PurchaseDto>> getAll() {
		PurchaseDto dto = new PurchaseDto();
		dto.setId(12L);
		dto.setCustomerId(13L);
		dto.setProductName("best product");
		return ResponseEntity.ok(Collections.singletonList(dto));
	}
}
