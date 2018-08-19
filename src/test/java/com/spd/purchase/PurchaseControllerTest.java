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

import java.util.List;

import static org.assertj.core.util.Lists.newArrayList;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PurchaseControllerTest {
	@Autowired
	private MockMvc mockMvc;

	private final Integer USER_ID = 13;
	private final Integer FIRST_PURCHASE_ID = 10;
	private final Integer SECOND_PURCHASE_ID = 11;
	private final String SECOND_PRODUCT_NAME = "secondProduct";
	private final String FIRST_PRODUCT_NAME = "firstProduct";
	@MockBean
	private PurchaseService purchaseService;

	@Test
	public void test() throws Exception {
		List<PurchaseModel> purchases = createPurchaseModels();
		doReturn(purchases).when(purchaseService).findPurchasesByCustomerId(USER_ID);

		mockMvc.perform(get("/api/purchases/customers/{id}", USER_ID))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].purchaseId", is(FIRST_PURCHASE_ID)))
				.andExpect(jsonPath("$[0].customerId", is(USER_ID)))
				.andExpect(jsonPath("$[0].productName", is(FIRST_PRODUCT_NAME)))
				.andExpect(jsonPath("$[1].purchaseId", is(SECOND_PURCHASE_ID)))
				.andExpect(jsonPath("$[1].customerId", is(USER_ID)))
				.andExpect(jsonPath("$[1].productName", is(SECOND_PRODUCT_NAME)));

	}

	private List<PurchaseModel> createPurchaseModels() {
		PurchaseModel first = new PurchaseModel();
		first.setPurchaseId(FIRST_PURCHASE_ID);
		first.setProductName(FIRST_PRODUCT_NAME);
		first.setCustomerId(USER_ID);
		PurchaseModel second = new PurchaseModel();
		second.setPurchaseId(SECOND_PURCHASE_ID);
		second.setProductName(SECOND_PRODUCT_NAME);
		second.setCustomerId(USER_ID);
		return newArrayList(first, second);
	}

}