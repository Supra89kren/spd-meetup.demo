package com.spd.purchase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/test/purchase-test.sql")
public class PurchaseRepositoryTest {
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Test
	public void expectedTwoPurchase() {
		int customerId = 13;
		int firstPurchaseId = 1002;
		int secondPurchaseId = 1003;
		String firstProductName = "best product";
		String secondProductName = "other product";

		List<Purchase> purchases = purchaseRepository.findPurchasesByCustomerId(customerId);
		assertNotNull(purchases);
		assertThat(purchases, hasSize(2));
		assertThat(purchases.get(0).getId(), is(firstPurchaseId));
		assertThat(purchases.get(0).getCustomerId(), is(customerId));
		assertThat(purchases.get(0).getProductName(), is(firstProductName));
		assertThat(purchases.get(1).getId(), is(secondPurchaseId));
		assertThat(purchases.get(1).getCustomerId(), is(customerId));
		assertThat(purchases.get(1).getProductName(), is(secondProductName));
	}
}