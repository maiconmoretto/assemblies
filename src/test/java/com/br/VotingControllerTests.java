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
public class VotingControllerTests {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void idUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithoutIdUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithoutVote() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithVoteWrong() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/?vote=abc").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithIdUserWrong() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/?idUser=9999999999999999999").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithIdAgendaWrong() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/?idAgenda=9999999999999999999").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
	
//	@Test
//	public void add() throws Exception {
//		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/?description=new agenda&duration=10").accept(MediaType.APPLICATION_JSON))
//				.andExpect(status().isCreated());
//	}

}
