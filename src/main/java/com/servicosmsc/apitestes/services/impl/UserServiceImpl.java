package com.servicosmsc.apitestes.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.servicosmsc.apitestes.domain.User;
import com.servicosmsc.apitestes.repositories.UserRepository;
import com.servicosmsc.apitestes.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository repository;
	
	@Override
	public User findById(Integer id) {
		Optional<User> obj = repository.findById(id);
		return obj.orElse(null);
	}

}
