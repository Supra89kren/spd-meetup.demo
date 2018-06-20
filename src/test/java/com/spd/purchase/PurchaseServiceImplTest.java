package com.spd.purchase;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {
	@Mock
	private PurchaseRepository repository;
	@InjectMocks
	private PurchaseServiceImpl purchaseService;

	@Test
	public void assertResultOfMappingEntityToModel() {
		Purchase purchase = new Purchase();
		purchase.setId(12L);
		purchase.setCustomerId(13L);
		purchase.setProductName("best product");
		Mockito.when(repository.findById(12L)).thenReturn(Optional.of(purchase));

		Optional<PurchaseModel> result = purchaseService.getPurchase(12L);

		assertTrue(result.isPresent());
		PurchaseModel model = result.get();
		assertThat(model.getId(), equalTo(12L));
		assertThat(model.getCustomerId(), equalTo(13L));
		assertThat(model.getProductName(), equalTo("best product"));
	}

	@Test
	public void expectedEmptyOptionalToBeReturnedIfNoPurchaseIsFound() {
		Mockito.when(repository.findById(12L)).thenReturn(Optional.empty());
		Optional<PurchaseModel> purchase = purchaseService.getPurchase(12L);
		Assert.assertThat(purchase.isPresent(), is(false));

	}
}