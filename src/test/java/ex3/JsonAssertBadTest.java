package ex3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JsonAssertBadTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		String expectedResponse = "{\"email\":\"supra89kren@gmail.com\",\"firstName\":\"Vladyslav\",\"lastName\":\"Diachenko\"}";
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 12))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(content().string(expectedResponse));
	}
}
