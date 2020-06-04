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

import com.br.model.User;
import com.fasterxml.jackson.databind.Module.SetupContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

	@Autowired
	private MockMvc mvc;
	
	private User user;
	
	@Before
	public void setup() {
		User user = new User("joao", "01-01-01 01:01:01", "11111111111");
	}

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveWithoutDescription() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void save() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}	

	@Test
	public void findByIdMethodNotAllowed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/user/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void findById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/user/").content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
	}
		
	@Test
	public void deleteById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/user/1").accept(MediaType.APPLICATION_JSON))
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
