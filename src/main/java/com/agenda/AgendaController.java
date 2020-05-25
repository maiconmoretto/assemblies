
package com.agenda;

import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/agenda")
public class AgendaController {

	@Autowired
	private AgendaRepository AgendaRepository;

	@PostMapping(path = "/")
	public @ResponseBody String add(@RequestParam String description) {		
		Agenda agenda = new Agenda();
		agenda.setDescription(description);
		AgendaRepository.save(agenda);
		return "Agenda successfully registered";
	}
}