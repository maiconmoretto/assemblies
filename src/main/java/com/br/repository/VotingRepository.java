package com.br.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.model.Agenda;
import com.br.model.Voting;
 
public interface VotingRepository extends CrudRepository<Voting, Integer> {
	
	@Query(value = "SELECT * FROM voting v WHERE v.id_agenda =:id_agenda and v.id_user =:id_user", nativeQuery = true)
	Optional<Voting> userAlreadyVote(@Param("id_agenda") int idAgenda, @Param("id_user") int idUser);

	Optional<Voting> findByIdUser(int idUser);  
}