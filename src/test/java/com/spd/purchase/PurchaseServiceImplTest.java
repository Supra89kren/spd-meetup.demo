package com.spd.purchase;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class PurchaseServiceImplTest {

	private final int CUSTOMER_ID = 13;
	private final int FIRST_PURCHASE_ID = 10;
	private final int SECOND_PURCHASE_ID = 11;
	private final String FIRST_PRODUCT_NAME = "first";
	private final String SECOND_PRODUCT_NAME = "second";

	@Mock
	private PurchaseRepository purchaseRepository;

	@InjectMocks
	private PurchaseServiceImpl purchaseService;

	@Test
	public void test() {
		List<Purchase> purchases = createPurchases();
		doReturn(purchases).when(purchaseRepository).findPurchasesByCustomerId(CUSTOMER_ID);

		List<PurchaseModel> list = purchaseService.findPurchasesByCustomerId(CUSTOMER_ID);
		assertNotNull(list);
		assertThat(list, hasSize(2));
		assertThat(list.get(0).getPurchaseId(), is(FIRST_PURCHASE_ID));
		assertThat(list.get(0).getCustomerId(), is(CUSTOMER_ID));
		assertThat(list.get(0).getProductName(), is(FIRST_PRODUCT_NAME));
		assertThat(list.get(1).getPurchaseId(), is(SECOND_PURCHASE_ID));
		assertThat(list.get(1).getCustomerId(), is(CUSTOMER_ID));
		assertThat(list.get(1).getProductName(), is(SECOND_PRODUCT_NAME));
	}

	private List<Purchase> createPurchases() {
		Purchase first = new Purchase();
		first.setId(FIRST_PURCHASE_ID);
		first.setProductName(FIRST_PRODUCT_NAME);
		first.setCustomerId(CUSTOMER_ID);
		Purchase second = new Purchase();
		second.setId(SECOND_PURCHASE_ID);
		second.setCustomerId(CUSTOMER_ID);
		second.setProductName(SECOND_PRODUCT_NAME);
		return Lists.newArrayList(first, second);
	}
}