package com.br.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.repository.UserRepository;
import com.br.model.User;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<User> findAll() {
		return (List<User>) repository.findAll();
	}

	public User findById(int id) {
		return repository.findById(id).get();
	}

	public void save(User listElement) {
		repository.save(listElement);
	}

	public void update(User user) {
		repository.save(user);
	}

	public void deleteById(int id) {
		repository.deleteById(id);
	}

}
