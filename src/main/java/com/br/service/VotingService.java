package com.br.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.br.model.Agenda;
import com.br.model.User;
import com.br.model.Voting;
import com.br.repository.AgendaRepository;
import com.br.repository.UserRepository;
import com.br.repository.VotingRepository;
import org.springframework.web.client.RestTemplate;

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
		
		Optional<User> user = userRepository.findById(voting.getIdUser());
		
		RestTemplate restTemplate = new RestTemplate(); 
		String url = "https://user-info.herokuapp.com/users/" + user.get().getCpf() ; 
		ResponseEntity<String> response
		  = restTemplate.getForEntity(url, String.class); 
		
		if (response.getBody().equals("{\"status\":\"UNABLE_TO_VOTE\"}")) {
			return new ResponseEntity<>("This user is UNABLE_TO_VOTE", HttpStatus.BAD_REQUEST);
		}
		
		if (!userRepository.findById(voting.getIdAgenda()).isPresent()) {
			return new ResponseEntity<>("No User found with id " + voting.getIdUser(), HttpStatus.BAD_REQUEST);
		}

		if (!agendaRepository.findById(voting.getIdAgenda()).isPresent()) {
			return new ResponseEntity<>("No Agenda found with id " + voting.getIdAgenda(), HttpStatus.BAD_REQUEST);
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
		if (!userRepository.findById(voting.getIdUser()).isPresent()) {
			return new ResponseEntity<>("No User found with id " + voting.getIdUser(), HttpStatus.BAD_REQUEST);
		}

		if (!agendaRepository.findById(voting.getIdAgenda()).isPresent()) {
			return new ResponseEntity<>("No Agenda found with id " + voting.getIdAgenda(), HttpStatus.BAD_REQUEST);
		}

		Optional<Agenda> agendaOpen = agendaRepository.agendaOpen(voting.getIdAgenda());
		if (agendaOpen.isEmpty()) {
			return new ResponseEntity<>("This Agenda is already close for vote", HttpStatus.BAD_REQUEST);
		}

		if (!voting.getVote().equals("Sim") && !voting.getVote().equals("Não")) {
			return new ResponseEntity<>("The vote is only Sim or Não", HttpStatus.BAD_REQUEST);
		}
		
		Optional<Voting> votingOld = votingRepository.findById(voting.getId());
		Agenda agenda = agendaRepository.findById(voting.getIdAgenda()).get();
		if (!voting.getVote().equals(votingOld.get().getVote())) {
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
		return new ResponseEntity<>("Voting successfully updated", HttpStatus.CREATED);
	}

	public ResponseEntity<String> deleteById(int id) {
		votingRepository.deleteById(id);
		return new ResponseEntity<>("Voting successfully deleted", HttpStatus.CREATED);
	}
}
