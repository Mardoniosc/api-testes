package com.servicosmsc.apitestes.services;

import java.util.List;

import com.servicosmsc.apitestes.domain.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();
}
