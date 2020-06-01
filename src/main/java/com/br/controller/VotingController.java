
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.model.Voting;
import com.br.model.Agenda;
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
	public @ResponseBody ResponseEntity add(@RequestParam int idAgenda, @RequestParam int idUser,
			@RequestParam String vote) {
		if (!userRepository.findById((long) idUser).isPresent()) {
			return new ResponseEntity<>("No User found with id " + idUser, HttpStatus.BAD_REQUEST);
		}

		if (!agendaRepository.findById(idAgenda).isPresent()) {
			return new ResponseEntity<>("No Agenda found with id " + idAgenda, HttpStatus.BAD_REQUEST);
		}

		Optional<Agenda> agendaOpen = agendaRepository.agendaOpen((long) idAgenda);
		if (agendaOpen.isEmpty()) {
			return new ResponseEntity<>("This Agenda is already close for vote", HttpStatus.BAD_REQUEST);
		}

		if (!vote.equals("Sim") && !vote.equals("Não")) {
			return new ResponseEntity<>("The vote is only Sim or Não", HttpStatus.BAD_REQUEST);
		}

		Optional<Voting> userAlreadyVote = votingRepository.findByIdUser(idUser);

		if (userAlreadyVote.isPresent()) {
			return new ResponseEntity<>("This user already vote", HttpStatus.BAD_REQUEST);
		}

		Agenda agenda = agendaRepository.findById(idAgenda).get();
		if (vote.equals("Sim")) {
			agenda.setSim(agenda.getSim() + 1);
		} else {
			agenda.setNao(agenda.getNao() + 1);
		}
		agendaRepository.save(agenda);

		Voting voting = new Voting();
		voting.setIdAgenda(idAgenda);
		voting.setIdUser(idUser);
		voting.setVote(vote);
		votingRepository.save(voting);
		return new ResponseEntity<>("Voting successfully registered", HttpStatus.CREATED);
	}

	@GetMapping(path = "/")
	public List<Voting> findAll() {
		return (List<Voting>) votingRepository.findAll();
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity delete(@PathVariable long id) {
		votingRepository.deleteById(id);
		return new ResponseEntity("Voting successfully deleted", HttpStatus.OK);
	}

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<Voting> findById(@PathVariable long id) {
		return votingRepository.findById(id).map(record -> ResponseEntity.ok().body(record))
				.orElse(ResponseEntity.notFound().build());
	}

	@PutMapping(path = { "/{id}" })
	public @ResponseBody ResponseEntity add(@PathVariable long id, @RequestParam int idAgenda, @RequestParam int idUser,
			@RequestParam String vote) {
		if (!votingRepository.findById((long) id).isPresent()) {
			return new ResponseEntity<>("No Voting found with id " + idUser, HttpStatus.BAD_REQUEST);
		}
		
		if (!userRepository.findById((long) idUser).isPresent()) {
			return new ResponseEntity<>("No User found with id " + idUser, HttpStatus.BAD_REQUEST);
		}

		if (!agendaRepository.findById(idAgenda).isPresent()) {
			return new ResponseEntity<>("No Agenda found with id " + idAgenda, HttpStatus.BAD_REQUEST);
		}

		Optional<Agenda> agendaOpen = agendaRepository.agendaOpen((long) idAgenda);
		if (agendaOpen.isEmpty()) {
			return new ResponseEntity<>("This Agenda is already close for vote", HttpStatus.BAD_REQUEST);
		}

		if (!vote.equals("Sim") && !vote.equals("Não")) {
			return new ResponseEntity<>("The vote is only Sim or Não", HttpStatus.BAD_REQUEST);
		}

		Voting votingOld = votingRepository.findById((long) id).get();
		
		Agenda agenda = agendaRepository.findById(idAgenda).get();
		if (!vote.equals(votingOld.getVote())) {
			if (vote.equals("Sim")) {
				agenda.setSim(agenda.getSim() + 1);
				agenda.setNao(agenda.getNao() - 1);
			} else {
				agenda.setSim(agenda.getSim() - 1);
				agenda.setNao(agenda.getNao() + 1);
			}
		}
		agendaRepository.save(agenda);

		Voting voting = new Voting();
		voting.setIdAgenda(idAgenda);
		voting.setIdUser(idUser);
		voting.setVote(vote);
		votingRepository.save(voting);
		return new ResponseEntity<>("Voting successfully updated", HttpStatus.CREATED);
	}

}