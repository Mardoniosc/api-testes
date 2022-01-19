package com.servicosmsc.apitestes.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.servicosmsc.apitestes.domain.User;
import com.servicosmsc.apitestes.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig {
	
	@Autowired
	private UserRepository repository;
	
	@Bean
	public void startDB() {
		User u1 = new User(null, "Mardonio Costa", "mardonio@live.com", "123123");
		User u2 = new User(null, "Maria Costa", "Maria@live.com", "123123");
		
		repository.saveAll(Arrays.asList(u1, u2));
	}

}
