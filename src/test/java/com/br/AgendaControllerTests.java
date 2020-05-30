package com.br;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
public class AgendaControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void getByIdWithInvalidId() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/agenda/0").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getByIdWithoutId() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void addWithoutDescription() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	@Test
	public void add() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/?description=new agenda&duration=10").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(content().string(containsString("Agenda successfully registered")));
	}

}
