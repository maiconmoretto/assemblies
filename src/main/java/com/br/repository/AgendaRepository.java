package com.br.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.model.Agenda;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer>{
	@Query(value = "SELECT * FROM agenda a WHERE a.id =:id and a.created_at + INTERVAL a.duration SECOND >= NOW()", nativeQuery = true)
	Optional<Agenda> agendaOpen(@Param("id") long idAgenda);

	@Query(value = "SELECT * FROM agenda a WHERE a.sent_to_messager = false and a.created_at + INTERVAL a.duration SECOND <= NOW()", nativeQuery = true)
	List<Agenda> agendasClosed();

	@Query(value = "SELECT * FROM agenda a WHERE a.sent_to_messager = false and a.id =:id and a.created_at + INTERVAL a.duration SECOND <= NOW()", nativeQuery = true)
	Agenda findAgendaClosedById(int id);
}
