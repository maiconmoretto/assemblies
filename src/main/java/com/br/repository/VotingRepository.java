package com.br.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.br.model.Voting;

public interface VotingRepository extends CrudRepository<Voting, Long> {
	Optional<Voting> findByIdUser(int idUser);
}