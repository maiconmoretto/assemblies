package com.br.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "agenda")
public class Agenda implements Serializable {
 
	@Id
	@Column(name = "id")
	int id;
	@Column(name = "description")
	String description;
	@Column(name = "created_at")
	String createdAt;
	@Column(name = "duration")
	int duration;
	@Column(name = "sim")
	int sim;
	@Column(name = "nao")
	int nao;
	@Column(name = "sent_to_messager")
	boolean sentToMessager;

    public Agenda() {
    }
       	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getSim() {
		return sim;
	}

	public void setSim(int sim) {
		this.sim = sim;
	}

	public int getNao() {
		return nao;
	}

	public void setNao(int nao) {
		this.nao = nao;
	}
	
	

	public boolean isSentToMessager() {
		return sentToMessager;
	}

	public void setSentToMessager(boolean sentToMessager) {
		this.sentToMessager = sentToMessager;
	}

	public Agenda(String description, String createdAt, int duration, int sim, int nao) {
	  this.description = description;
	  this.createdAt = createdAt;
	  this.duration = duration;
	  this.sim = sim;
	  this.nao = nao;
    }
}
