package com.br.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.AppException;
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

	@Autowired
	private UserService userService;

	public List<Voting> findAll() {
		return (List<Voting>) votingRepository.findAll();
	}

	public Voting findById(int id) {
		return votingRepository.findById(id).get();
	}

	public ResponseEntity<String> save(Voting voting) {
		this.validate(voting);

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

	public void validate(Voting voting) {
		if (!userRepository.findById(voting.getIdUser()).isPresent()) {
			throw new AppException(404, "No User found with id " + voting.getIdUser());
		}

		if (!agendaRepository.findById(voting.getIdAgenda()).isPresent()) {
			throw new AppException(404, "No Agenda found with id " + voting.getIdAgenda());
		}

		Optional<Agenda> agendaOpen = agendaRepository.agendaOpen(voting.getIdAgenda());
		if (agendaOpen.isEmpty()) {
			throw new AppException(404, "This agenda is already closed for voting");
		}

		if (!voting.getVote().equals("Sim") && !voting.getVote().equals("Não")) {
			throw new AppException(404, "The vote is only Sim or Não");
		}
		
		Optional<Voting> userAlreadyVote = votingRepository.userAlreadyVote(voting.getIdAgenda(), voting.getIdUser());
		if (userAlreadyVote.isPresent()) {
			throw new AppException(404, "This user already voted");
		}
	}

	public ResponseEntity<String> update(Voting voting) {
		this.validate(voting);

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
