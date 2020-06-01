package com.br.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "voting")
public class Voting implements Serializable{

	
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "id_agenda")
	int idAgenda;
	@Column(name = "id_user")
	int idUser;
	@Column(name = "created_at")
	String createdAt;
	@Column(name = "vote")
	String vote;

    public Voting() {
    } 

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
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

	public Voting(int idAgenda, int idUser, String vote, String createdAt) {
	  this.idAgenda = idAgenda;
	  this.createdAt = createdAt;
	  this.idUser = idUser;
	  this.vote = vote;
    }	
}





