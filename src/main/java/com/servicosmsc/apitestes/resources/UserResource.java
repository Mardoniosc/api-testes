package com.servicosmsc.apitestes.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicosmsc.apitestes.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Integer id) {
		return ResponseEntity.ok().body(new User(1, "Mardonio", "Mardonio@live.com", "123123"));
	}

}
