package com.servicosmsc.apitestes.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.servicosmsc.apitestes.domain.User;
import com.servicosmsc.apitestes.domain.dto.UserDTO;
import com.servicosmsc.apitestes.services.impl.UserServiceImpl;

@SpringBootTest
class UserResourceTest {
	
	private static final Integer ID   = 1;
	private static final String NOME  = "Mardonio";
	private static final String EMAIL = "Mardonio@live.com";
	private static final String SENHA = "123";
	
	@InjectMocks
	private UserResource resource;
	
	@Mock
	private UserServiceImpl service;
	
	@Mock
	private ModelMapper mapper;
	
	private User user;	
	private UserDTO userDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void quandoBuscarPorIdRetornaSucesso() {
		when(service.findById(anyInt())).thenReturn(user);
		when(mapper.map(any(), any())).thenReturn(userDTO);
		
		ResponseEntity<UserDTO> response = resource.findById(ID);
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(ResponseEntity.class ,response.getClass());
		assertEquals(UserDTO.class, response.getBody().getClass());
		
		
		assertEquals(ID, response.getBody().getId());
		assertEquals(NOME, response.getBody().getNome());
		assertEquals(EMAIL, response.getBody().getEmail());
		assertEquals(SENHA, response.getBody().getSenha());
	}

	@Test
	void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	void testCreate() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdate() {
		fail("Not yet implemented");
	}

	@Test
	void testDelete() {
		fail("Not yet implemented");
	}
	
	private void startUser() {
		user = new User(ID, NOME, EMAIL, SENHA);
		userDTO = new UserDTO(ID, NOME, EMAIL, SENHA);
	}

}
