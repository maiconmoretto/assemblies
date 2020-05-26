package com.agenda;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String createAt;
    private int duration;


    public Agenda() {
    }
       
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}


	public String getCreateAt() {
		return createAt;
	}


	public void setCreateAt(String createAt) {
		this.createAt = createAt;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public Agenda(String description, String createAt, int duration) {
	  this.description = description;
	  this.createAt = createAt;
	  this.duration = duration;
    }
	
}





