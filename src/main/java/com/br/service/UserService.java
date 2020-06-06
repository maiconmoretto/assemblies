package com.br.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

	public ResponseEntity<String> save(User user) {
		if (!this.isUserAbleTovote(user.getCpf())) {
			return new ResponseEntity<>("User Unable to vote and can't be registered", HttpStatus.CREATED);
		}
		repository.save(user);
		return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);

	}

	public ResponseEntity<String> update(User user) {
		Optional<User> userExist = repository.findById(user.getId());
		if (!userExist.isPresent()) {
			return new ResponseEntity<>("User does not exist", HttpStatus.BAD_REQUEST);
		}
		repository.save(user);
		return new ResponseEntity<>("User successfully updated", HttpStatus.OK);
	}

	public ResponseEntity<String> deleteById(int id) {
		repository.deleteById(id);
		return new ResponseEntity<>("User successfully deleted", HttpStatus.OK);
	}

	boolean isUserAbleTovote(String cpf) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://user-info.herokuapp.com/users/" + cpf;
		ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

		if (response.getBody().equals("{\"status\":\"UNABLE_TO_VOTE\"}")) {
			return false;
		}
		return true;
	}

}
