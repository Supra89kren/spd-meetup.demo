package com.spd.purchase;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerTest {
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private PurchaseService purchaseService;

	@Test
	public void responseShouldContainsOnePurchase() throws Exception {
		PurchaseModel firstPurchase = new PurchaseModel();
		firstPurchase.setPurchaseId(1002L);
		firstPurchase.setCustomerId(13L);
		firstPurchase.setProductName("best product");

		PurchaseModel secondPurchase = new PurchaseModel();
		secondPurchase.setPurchaseId(1003L);
		secondPurchase.setCustomerId(13L);
		secondPurchase.setProductName("other product");

		doReturn(Lists.newArrayList(firstPurchase, secondPurchase)).when(purchaseService).findPurchasesByCustomerId(13L);

		mockMvc.perform(get("/api/purchases/customers/{id}", 13))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].purchaseId", is(1002)))
				.andExpect(jsonPath("$[0].customerId", is(13)))
				.andExpect(jsonPath("$[0].productName", is("best product")))
				.andExpect(jsonPath("$[1].purchaseId", is(1003)))
				.andExpect(jsonPath("$[1].customerId", is(13)))
				.andExpect(jsonPath("$[1].productName", is("other product")));
	}

	@Test
	public void expected404IfPurchaseIsNotFound() throws Exception {
		Mockito.when(purchaseService.findPurchasesByCustomerId(13L)).thenReturn(Lists.emptyList());
		mockMvc.perform(get("/api/purchases/{id}", 13L))
				.andExpect(status().isNotFound());
	}
}