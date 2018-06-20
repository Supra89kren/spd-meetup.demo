package com.spd.purchase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.is;
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
		PurchaseModel model = new PurchaseModel();
		model.setId(12L);
		model.setCustomerId(13L);
		model.setProductName("best product");
		Mockito.when(purchaseService.getPurchase(12L)).thenReturn(Optional.of(model));
		mockMvc.perform(get("/api/purchases/{id}", 12L))
				.andExpect(status().isOk())
				.andExpect(content().contentType(APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.id", is(12)))
				.andExpect(jsonPath("$.customerId", is(13)))
				.andExpect(jsonPath("$.productName", is("best product")));
	}

	@Test
	public void expected404IfPurchaseIsNotFound() throws Exception {
		Mockito.when(purchaseService.getPurchase(12L)).thenReturn(Optional.empty());
		mockMvc.perform(get("/api/purchases/{id}", 12L))
				.andExpect(status().isNotFound());
	}
}