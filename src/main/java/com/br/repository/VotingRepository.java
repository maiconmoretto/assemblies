package com.br.repository;


import org.springframework.data.repository.CrudRepository;

import com.br.model.Voting;

public interface VotingRepository extends CrudRepository<Voting, Long> {

}