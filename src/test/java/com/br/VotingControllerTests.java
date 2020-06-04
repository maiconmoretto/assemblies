package com.br;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestParam;

import com.br.model.Voting;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class VotingControllerTests {

	@Autowired
	private MockMvc mvc;	
	
	private Voting voting;
	
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
	public void addWithNoExistIdVoting() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=1&idVoting=0&vote=Sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest())
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithNoExistIdAgenda() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=0&idVoting=2&vote=Sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	public void addWithNoValidVote() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/?idAgenda=1&idVoting=2&vote=sim")
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
	
	@Before
	public void setup() {
		Voting voting = new Voting(1, 1, "Sim", "01-01-01 01:01:01");
	}

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/voting/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveWithoutDescription() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void save() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/").content(asJsonString(voting))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}	

	@Test
	public void findByIdMethodNotAllowed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/voting/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void findById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/voting/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/voting/").content(asJsonString(voting))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
	}
		
	@Test
	public void deleteById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/voting/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
