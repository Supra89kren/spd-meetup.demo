package ex3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JsonAssertTestFine {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void test() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/{id}", 12))
				.andExpect(status().isOk())
				.andDo(print())
				.andExpect(jsonPath("$.email", is("supra89kren@gmail.com")))
				.andExpect(jsonPath("$.firstName", is("Vladyslav")))
				.andExpect(jsonPath("$.lastName", is("Diachenko")));
	}
}
