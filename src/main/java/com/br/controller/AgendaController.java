
package com.br.controller;

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

import com.br.model.Agenda;
import com.br.model.User;
import com.br.repository.AgendaRepository;

@Controller
@RequestMapping(path = "/api/v1/agenda")
public class AgendaController {

	@Autowired
	private AgendaRepository repository;

	@PostMapping(path = "/")
	public @ResponseBody String add(@RequestParam String description, @RequestParam int duration) {
		Agenda agenda = new Agenda();
		agenda.setDescription(description);
		agenda.setDuration(duration);
		repository.save(agenda);
		return "Agenda successfully registered";
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Agenda> findById(@PathVariable long id){
	   return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
}