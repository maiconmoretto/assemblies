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
import org.springframework.web.bind.annotation.RequestParam;

@SpringBootTest
@AutoConfigureMockMvc
public class VotingControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void addWithoutVote() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void addWithInvalidVote() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?vote=abc").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithNoExistIdUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=1&idUser=0&vote=Sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithNoExistIdAgenda() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=0&idUser=2&vote=Sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithNoValidVote() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=1&idUser=2&vote=sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
