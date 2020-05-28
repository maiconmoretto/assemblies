
package com.br.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.model.Voting;
import com.br.model.User;
import com.br.repository.AgendaRepository;
import com.br.repository.UserRepository;
import com.br.repository.VotingRepository;

@Controller
@RequestMapping(path = "/api/v1/voting")
public class VotingController {

	@Autowired
	private VotingRepository votingRepository;
	
	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping(path = "/")
	public @ResponseBody String add(@RequestParam int idAgenda, @RequestParam int idUser, String vote) {
		if (!userRepository.findById((long) idUser).isPresent()) {
			  return "No User found with id " + idUser;
		}
		
		if (!agendaRepository.findById((long) idAgenda).isPresent()) {
			  return "No Agenda found with id " + idAgenda;
		}	
		
		Voting voting = new Voting();
		voting.setIdAgenda(idAgenda);
		voting.setIdUser(idUser);
		voting.setVote(vote);
		votingRepository.save(voting);
		return "Voting successfully registered";
	}
}