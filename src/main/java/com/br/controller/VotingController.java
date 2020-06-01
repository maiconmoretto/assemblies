
package com.br.controller;

import java.util.List;
import java.util.Optional;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.model.Voting;
import com.br.service.VotingService;

@RestController
public class VotingController {

	@Autowired
	private VotingService service;

	@GetMapping("/api/v1/voting")
	public List<Voting> findAll() {
		return service.findAll();
	}

	@GetMapping("/api/v1/voting/{id}")
	public Voting findById(@PathVariable int id) {
		return service.findById(id);
	}

	@PostMapping(path = "/api/v1/voting/")
	public @ResponseBody ResponseEntity save(@RequestBody Voting voting) {
		return service.save(voting);
	}

	@PutMapping(value = "/api/v1/voting/{id}")
	public ResponseEntity<String> update(@RequestBody Voting voting) {
		return service.update(voting);
	}

	@DeleteMapping(path = "/api/v1/voting/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}

}