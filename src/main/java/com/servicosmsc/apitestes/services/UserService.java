package com.servicosmsc.apitestes.services;

import java.util.List;

import com.servicosmsc.apitestes.domain.User;
import com.servicosmsc.apitestes.domain.dto.UserDTO;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();
	
	User create(UserDTO obj);
}
