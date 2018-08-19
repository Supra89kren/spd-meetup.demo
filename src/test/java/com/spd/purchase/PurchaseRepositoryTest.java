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
		List<Purchase> purchases = purchaseRepository.findPurchasesByCustomerId(13);
		assertNotNull(purchases);
		assertThat(purchases, hasSize(2));
		assertThat(purchases.get(0).getId(), is(1002L));
		assertThat(purchases.get(0).getCustomerId(), is(13L));
		assertThat(purchases.get(0).getProductName(), is("best product"));
		assertThat(purchases.get(1).getId(), is(1003L));
		assertThat(purchases.get(1).getCustomerId(), is(13L));
		assertThat(purchases.get(1).getProductName(), is("other product"));
	}
}