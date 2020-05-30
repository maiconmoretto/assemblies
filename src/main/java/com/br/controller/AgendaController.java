
package com.br.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.br.model.Agenda;
import com.br.repository.AgendaRepository;

@Controller
@RequestMapping(path = "/api/v1/agenda")
public class AgendaController {

	@Autowired
	private AgendaRepository repository;

	@PostMapping(path = "/")
	public @ResponseBody ResponseEntity add(@RequestParam String description, @RequestParam int duration) {
		Agenda agenda = new Agenda();
		agenda.setDescription(description);
		agenda.setDuration(duration);
		repository.save(agenda);
		return new ResponseEntity<>("Agenda successfully registered", HttpStatus.CREATED);
	}

	@GetMapping("/")
	public List<Agenda> findAll() {
		return repository.findAll();
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Agenda> findById(@PathVariable long id) {
		return repository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {
		repository.deleteById(id);
		return new ResponseEntity<>("Agenda successfully deleted", HttpStatus.OK);
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity update(@RequestParam long id, @RequestParam String description, @RequestParam int duration,
			@RequestParam int sim, @RequestBody String createdAt, @RequestParam int nao) {
		return repository.findById(id).map(agenda -> {
			agenda.setDescription(description);
			agenda.setCreatedAt(createdAt);
			agenda.setDuration(duration);
			agenda.setNao(nao);
			agenda.setSim(sim);
			repository.save(agenda);
			return new ResponseEntity("Agenda successfully updated", HttpStatus.CREATED);
		}).orElseGet(() -> {
			return new ResponseEntity("Agenda do not updated", HttpStatus.CREATED);
		});
	}
	
}