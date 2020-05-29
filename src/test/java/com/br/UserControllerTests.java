package com.br;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void getByIdWithoutId() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/2").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void addWithoutName() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	@Test
	public void add() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/?name=joaquin").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

}
