package com.user;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void addUserTest() {
		String body = this.restTemplate.postForObject("/user/?name=joao", "/", String.class);
		assertThat(body).isEqualTo("User successfully registered");
	}

}
