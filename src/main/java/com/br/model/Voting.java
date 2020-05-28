package com.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.web.bind.annotation.RequestParam;


@Entity
public class Voting {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int idAgenda;
    private int idUser;
    private String vote;
    private String createdAt;

    public Voting() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getIdAgenda() {
		return idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public int getIdUser() {
		return idUser;
	}


	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getVote() {
		return vote;
	}

	public void setVote(String vote) {
		this.vote = vote;
	}


	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Voting(@RequestParam int idAgenda, @RequestParam int idUser, String vote, String createdAt) {
	  this.idAgenda = idAgenda;
	  this.createdAt = createdAt;
	  this.idUser = idUser;
	  this.vote = vote;
    }	
}





