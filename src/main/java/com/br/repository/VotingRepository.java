package com.br.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.model.Voting;

public interface VotingRepository extends CrudRepository<Voting, Long> {

	Optional<Voting> findByIdUser(int idUser);

//	public Voting findByIdUser(int idUser);
//	@Query("SELECT * FROM assemblies.voting WHERE id_user = ?1")
//	Collection<Voting> findByUser(int idUser);
//	@Query("SELECT * FROM assemblies.voting WHERE id_user =: id_user")
//	Voting findByUser(@Param("id_user") Integer idUser);

}