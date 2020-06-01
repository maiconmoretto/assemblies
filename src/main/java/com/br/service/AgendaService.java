package com.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void save(Agenda listElement) {
		repository.save(listElement);

	}

	public void update(Agenda agenda) {
		repository.save(agenda);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

	public Optional<Agenda> agendaOpen(int idAgenda) {
		return repository.agendaOpen(idAgenda);
	}

}
