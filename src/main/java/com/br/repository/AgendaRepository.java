package com.br.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.br.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {
	@Query(value = "SELECT * FROM agenda a WHERE a.id =:id  and a.created_at + INTERVAL a.duration SECOND >= NOW()", nativeQuery = true)
	Optional<Agenda> agendaOpen(@Param("id") long idAgenda);
}