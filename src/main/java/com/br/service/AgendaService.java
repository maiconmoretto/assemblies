package com.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.repository.AgendaRepository;
import com.br.model.Agenda;

@Service
public class AgendaService {

	@Autowired
	private AgendaRepository repository;

	public List<Agenda> findAll() {
		return (List<Agenda>) repository.findAll();
	}

	public Agenda findById(int id) {
		return repository.findById(id).get();
	}

	public ResponseEntity<String> save(Agenda agenda) {
		repository.save(agenda);
		return new ResponseEntity<>("Agenda successfully registered", HttpStatus.CREATED);
	}

	public ResponseEntity<String> update(Agenda agenda) {
		repository.save(agenda);
		return new ResponseEntity<>("Agenda successfully updated", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteById(int id) {
		repository.deleteById(id);
		return new ResponseEntity<>("Agenda successfully deleted", HttpStatus.OK);
	}

	public Optional<Agenda> agendaOpen(int idAgenda) {
		return repository.agendaOpen(idAgenda);
	}

}