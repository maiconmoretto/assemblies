package com.br;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	private User joao;
	private User jose;
	private User maria;
	private List<User> userList = new ArrayList<User>();
	 
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		User joao = new User();
		joao.setCpf("11111111111");
		joao.setCreatedAt("01-01-01 01:01:01");
		joao.setName("joao");
		userList.add(joao);
		User maria = new User();
		maria.setCpf("22222222222");
		maria.setCreatedAt("02-02-02 02:02:02");
		maria.setName("maria");
		userList.add(maria);
		User jose = new User();
		maria.setCpf("33333333333");
		maria.setCreatedAt("03-03-03 03:03:03");
		maria.setName("jose");
		userList.add(jose);
	}

	@Test
	public void findAll() {		
		when(userRepository.findAll()).thenReturn(userList);
		List<User> result = (List<User>) userRepository.findAll();
		assertEquals(3, result.size());
	}

	@Test
	public void deleteById() {
		User user = new User("User 1", "01-01-01", "11111111111");
		userRepository.deleteById(user.getId());
		verify(userRepository, times(1)).deleteById(user.getId());
	}

	@Test
	public void save() {				
		User user = new User("User 1", "01-01-01", "11111111111");
		when(userRepository.save(user)).thenReturn(user);
		User result = userRepository.save(user);
		assertEquals("User 1", result.getName());
		assertEquals("01-01-01", result.getCreatedAt());
		assertEquals("11111111111", result.getCpf());
	}
	
	@Test
	public void update() {				
		User user = new User("User 1", "01-01-01", "11111111111");
		when(userRepository.save(user)).thenReturn(user);
		User result = userRepository.save(user);
		assertEquals("User 1", result.getName());
		assertEquals("01-01-01", result.getCreatedAt());
		assertEquals("11111111111", result.getCpf());
	}
	
	@Test
	public void findById() {				
		Optional<User> user = Optional.of(new User("User 1", "01-01-01", "11111111111"));
		when(userRepository.findById(1)).thenReturn(user);
		Optional<User> result = userRepository.findById(1);
		assertEquals("User 1", result.get().getName());
		assertEquals("01-01-01", result.get().getCreatedAt());
		assertEquals("11111111111", result.get().getCpf());
	}	
}