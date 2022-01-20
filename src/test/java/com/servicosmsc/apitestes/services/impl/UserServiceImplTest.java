package com.servicosmsc.apitestes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.servicosmsc.apitestes.domain.User;
import com.servicosmsc.apitestes.domain.dto.UserDTO;
import com.servicosmsc.apitestes.repositories.UserRepository;
import com.servicosmsc.apitestes.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

	private static final Integer ID   = 1;
	private static final String NOME  = "Mardonio";
	private static final String EMAIL = "Mardonio@live.com";
	private static final String SENHA = "123";

	@InjectMocks
	private UserServiceImpl service;

	@Mock
	private UserRepository repository;

	@Mock
	private ModelMapper mapper;
	
	private User user;	
	private UserDTO userDTO;
	private Optional<User> optionalUser;
	
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}
	
	@Test
	void quandoBuscarPorIdRetorneUmaInstanciaDeUsuario() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		
		User response = service.findById(ID);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void quandoBuscarPorIdRetornaExececaoDeNaoEncontrado() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException("Objeto não encontrado"));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals("Objeto não encontrado", e.getMessage());
		}
	}
	
	@Test
	void create(Integer id) {
	}
	
	@Test
	void update(Integer id) {
	}
	
	@Test
	void delete(Integer id) {
	}
	
	private void startUser() {
		user = new User(ID, NOME, EMAIL, SENHA);
		userDTO = new UserDTO(ID, NOME, EMAIL, SENHA);
		optionalUser = Optional.of(new User(ID, NOME, EMAIL, SENHA));
	}
}
