package com.br.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Agenda;
import com.br.service.AgendaService;

@RestController
public class AgendaController {

	@Autowired
	private AgendaService service;

	@GetMapping("/api/v1/agenda")
	public List<Agenda> findAll() {
		return service.findAll();
	}

	@GetMapping("/api/v1/agenda/{id}")
	public Agenda findById(@PathVariable int id) {
		return service.findById(id);
	}

	@PostMapping(path = "/api/v1/agenda/")
	public @ResponseBody ResponseEntity save(@RequestBody Agenda agenda) {
		service.save(agenda);
		return new ResponseEntity<>("Agenda successfully registered", HttpStatus.CREATED);
	}

	@PutMapping(value = "/api/v1/agenda/{id}")
	public ResponseEntity<String> update(@RequestBody Agenda agenda) {
		service.update(agenda);
		return new ResponseEntity<>("Agenda successfully updated", HttpStatus.OK);
	}

	@DeleteMapping(path = "/api/v1/agenda/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		service.deleteById(id);
		return new ResponseEntity<>("Agenda successfully deleted", HttpStatus.OK);
	}

}
