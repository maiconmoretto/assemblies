package com.br.repository;


import org.springframework.data.repository.CrudRepository;

import com.br.model.Agenda;

public interface AgendaRepository extends CrudRepository<Agenda, Long> {

}