package com.prueba;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import com.prueba.model.User;

@SpringBootTest
class PruebaSpringApplicationTests {


	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:" + port;
	}
	

	@Test
	void contextLoads() {
	}	

	@Test
	public void testGetUserById() {
	User user = restTemplate.getForObject(getRootUrl() + "/users/1", User.class);
	System.out.println(user.getName());
	Assert<Assert<SELF,ACTUAL>, ACTUAL>.assertNotNull(user);
	}
	
}
