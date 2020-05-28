
package com.br.controller;

import java.util.Optional;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.br.model.User;
import com.br.repository.UserRepository;

@Controller
@RequestMapping(path = "/api/v1/user")
public class UserController {

	@Autowired
	private UserRepository repository;

	@PostMapping(path = "/")
	public @ResponseBody String add(@RequestParam String name) {
		User user = new User();
		user.setName(name);
		repository.save(user);
		return "User successfully registered";
	}
}