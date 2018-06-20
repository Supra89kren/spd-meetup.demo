package com.spd.purchase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@Sql("/test/purchase-test.sql")
public class PurchaseRepositoryTest {
	@Autowired
	private PurchaseRepository purchaseRepository;

	@Test
	public void expectedEntityWithId12ToBeReturned() {
		Optional<Purchase> result = purchaseRepository.findById(12L);

		assertThat(result.isPresent(), is(true));
		Purchase purchase = result.get();
		assertThat(purchase.getId(), equalTo(12L));
		assertThat(purchase.getCustomerId(), equalTo(13L));
		assertThat(purchase.getProductName(), equalTo("best product"));
	}
}