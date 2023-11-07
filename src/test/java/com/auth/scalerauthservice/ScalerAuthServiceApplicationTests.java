package com.auth.scalerauthservice;

import com.auth.scalerauthservice.models.Session;
import com.auth.scalerauthservice.models.User;
import com.auth.scalerauthservice.repositories.SessionRepository;
import com.auth.scalerauthservice.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScalerAuthServiceApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SessionRepository sessionRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void saveUser() {
		User user = new User();
		user.setFirstName("Ayush");
		user.setLastName("Sinha");
		user.setEmail("ayush.sinha@gmail");
		user.setPassword("password");
		userRepository.save(user);
	}

	@Test
	void getUser() {
		User user = userRepository.findByEmail("ayush.sinha@gmail").get();
		System.out.println(user.getFirstName());
	}

	@Test
	void getToken(){
		User user = userRepository.findByEmail("ayush.sinha@gmail").get();
		Session session = sessionRepository.findByUser(user);
		System.out.println(session.getToken());
	}


}
