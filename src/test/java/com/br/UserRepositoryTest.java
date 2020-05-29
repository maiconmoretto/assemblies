package com.br;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.br.model.User;
import com.br.repository.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserRepositoryTest {
	
	@Mock
	private UserRepository userRepository;
	
	@Before
	public void setup(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAll(){
		List<User> userList = new ArrayList<User>();
		userList.add(new User("user 1", "01-01-01"));
		userList.add(new User("user 2", "02-02-02"));
		userList.add(new User("user 3","01-03-03"));
		when(userRepository.findAll()).thenReturn(userList);
		
		List<User> result = userRepository.findAll();
		assertEquals(3, result.size());
	}

}