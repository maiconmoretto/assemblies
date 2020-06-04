package com.br;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import com.br.model.Agenda;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AgendaControllerTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void findAll() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void saveWithoutDescription() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void save() throws Exception {
		Agenda agenda = new Agenda("agenda 1", "01-01-01", 60, 0, 0);
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/").content(asJsonString(agenda))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}	

	@Test
	public void findByIdMethodNotAllowed() throws Exception {
		mvc.perform(MockMvcRequestBuilders.post("/api/v1/agenda/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isMethodNotAllowed());
	}
	
	@Test
	public void findById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/api/v1/agenda/1").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void update() throws Exception {
		Agenda agenda = new Agenda("agenda 1", "01-01-01", 60, 0, 0);
		mvc.perform(MockMvcRequestBuilders.put("/api/v1/agenda/").content(asJsonString(agenda))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

	}
		
	@Test
	public void deleteById() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/api/v1/agenda/1").accept(MediaType.APPLICATION_JSON))
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
