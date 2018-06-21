package com.spd.purchase;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {
	@Mock
	private PurchaseRepository repository;
	@InjectMocks
	private PurchaseServiceImpl purchaseService;

	@Test
	public void assertResultOfMappingEntityToModel() {
		Purchase firstPurchase = new Purchase();
		firstPurchase.setId(1002L);
		firstPurchase.setCustomerId(13L);
		firstPurchase.setProductName("best product");
		Purchase secondPurchase = new Purchase();
		secondPurchase.setId(1003L);
		secondPurchase.setCustomerId(13L);
		secondPurchase.setProductName("other product");
		Mockito.when(repository.findPurchasesByCustomerId(13L)).thenReturn(Lists.newArrayList(firstPurchase, secondPurchase));

		List<PurchaseModel> purchases = purchaseService.findPurchasesByCustomerId(13L);

		assertNotNull(purchases);
		assertThat(purchases, hasSize(2));
		assertThat(purchases.get(0).getPurchaseId(), is(1002L));
		assertThat(purchases.get(0).getCustomerId(), is(13L));
		assertThat(purchases.get(0).getProductName(), is("best product"));
		assertThat(purchases.get(1).getPurchaseId(), is(1003L));
		assertThat(purchases.get(1).getCustomerId(), is(13L));
		assertThat(purchases.get(1).getProductName(), is("other product"));
	}

	@Test
	public void expectedEmptyOptionalToBeReturnedIfNoPurchaseIsFound() {
		Mockito.when(repository.findPurchasesByCustomerId(13L)).thenReturn(Lists.emptyList());
		List<PurchaseModel> purchases = purchaseService.findPurchasesByCustomerId(13L);
		assertNotNull(purchases);
		assertThat(purchases, empty());
	}
}