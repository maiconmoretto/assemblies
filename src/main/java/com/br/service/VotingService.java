package com.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.model.Agenda;
import com.br.model.Voting;
import com.br.repository.AgendaRepository;
import com.br.repository.UserRepository;
import com.br.repository.VotingRepository;

@Service
public class VotingService {
	
	@Autowired
	private VotingRepository votingRepository;

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private UserRepository userRepository;


	public List<Voting> findAll() {
		return (List<Voting>) votingRepository.findAll();
	}

	public Voting findById(int id) {
		return votingRepository.findById(id).get();
	}

	public ResponseEntity<String> save(Voting voting) {
		this.validateFields(voting);
		
		Agenda agenda = agendaRepository.findById(voting.getIdAgenda()).get();
		if (voting.getVote().equals("Sim")) {
			agenda.setSim(agenda.getSim() + 1);
		} else {
			agenda.setNao(agenda.getNao() + 1);
		}
		agendaRepository.save(agenda);

		votingRepository.save(voting);
		return new ResponseEntity<>("Voting successfully registered", HttpStatus.CREATED);
	}

	public ResponseEntity<String> update(Voting voting) {
		this.validateFields(voting);
		Voting votingOld = votingRepository.findById(voting.getId()).get();
		Agenda agenda = agendaRepository.findById(voting.getIdAgenda()).get();
		if (!voting.getVote().equals(votingOld.getVote())) {
			if (voting.getVote().equals("Sim")) {
				agenda.setSim(agenda.getSim() + 1);
				agenda.setNao(agenda.getNao() - 1);
			} else {
				agenda.setSim(agenda.getSim() - 1);
				agenda.setNao(agenda.getNao() + 1);
			}
		}
		agendaRepository.save(agenda);

		votingRepository.save(voting);
		return new ResponseEntity<>("Voting successfully registered", HttpStatus.CREATED);
	}

	public void deleteById(int id) {
		votingRepository.deleteById(id);
	}
	
	public ResponseEntity<String> validateFields(Voting voting) {
		if (!userRepository.findById(voting.getIdUser()).isPresent()) {
			return new ResponseEntity<>("No User found with id " + voting.getIdUser(), HttpStatus.BAD_REQUEST);
		}

		if (!agendaRepository.findById(voting.getIdAgenda()).isPresent()) {
			return new ResponseEntity<>("No Agenda found with id " + voting, HttpStatus.BAD_REQUEST);
		}

		Optional<Agenda> agendaOpen = agendaRepository.agendaOpen(voting.getIdAgenda());
		if (agendaOpen.isEmpty()) {
			return new ResponseEntity<>("This Agenda is already close for vote", HttpStatus.BAD_REQUEST);
		}

		if (!voting.getVote().equals("Sim") && !voting.getVote().equals("Não")) {
			return new ResponseEntity<>("The vote is only Sim or Não", HttpStatus.BAD_REQUEST);
		}

		Optional<Voting> userAlreadyVote = votingRepository.findByIdUser(voting.getIdUser());

		if (userAlreadyVote.isPresent()) {
			return new ResponseEntity<>("This user already vote", HttpStatus.BAD_REQUEST);
		}
		return null;
	}

}
