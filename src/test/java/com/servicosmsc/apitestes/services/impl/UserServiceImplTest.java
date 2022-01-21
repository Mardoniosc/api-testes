package com.servicosmsc.apitestes.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
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
import com.servicosmsc.apitestes.services.exceptions.DataIntegrityException;
import com.servicosmsc.apitestes.services.exceptions.ObjectNotFoundException;

@SpringBootTest
public class UserServiceImplTest {

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	private static final String E_MAIL_JA_CADASTRADO_NA_BASE = "E-mail já cadastrado na base!";
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
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));
		
		try {
			service.findById(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	@Test
	void quandoBuscarTodosRetorneUmaListaDeUsuarios() {
		when(repository.findAll()).thenReturn(List.of(user));
		
		List<User> response = service.findAll();	
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(User.class, response.get(0).getClass());
		assertEquals(ID, response.get(0).getId());
		assertEquals(NOME, response.get(0).getNome());
		assertEquals(EMAIL, response.get(0).getEmail());
	}
	
	@Test
	void quandoCriarRetorneSucesso() {
		when(repository.save(any())).thenReturn(user);
		
		User response = service.create(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void quandoCriarRetorneErroDeIntegridadeBancoDeDados() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.create(userDTO);			
		} catch (Exception e) {
			assertEquals(DataIntegrityException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NA_BASE, e.getMessage());
		}
		
	}
	
	@Test
	void quandoAtualizarRetorneSucesso() {
		when(repository.save(any())).thenReturn(user);
		
		User response = service.update(userDTO);
		
		assertNotNull(response);
		assertEquals(User.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getNome());
		assertEquals(EMAIL, response.getEmail());
	}
	
	@Test
	void quandoAtualizarRetorneErroDeIntegridadeBancoDeDados() {
		when(repository.findByEmail(anyString())).thenReturn(optionalUser);
		
		try {
			optionalUser.get().setId(2);
			service.update(userDTO);			
		} catch (Exception e) {
			assertEquals(DataIntegrityException.class, e.getClass());
			assertEquals(E_MAIL_JA_CADASTRADO_NA_BASE, e.getMessage());
		}
		
	}
	
	@Test
	void quandoDeletarSucesso() {
		when(repository.findById(anyInt())).thenReturn(optionalUser);
		doNothing().when(repository).deleteById(anyInt());
		service.delete(ID);
		
		verify(repository, times(1)).deleteById(anyInt());
	}
	
	@Test
	void quandoDeletarComErroNaoEncontradoId() {
		when(repository.findById(anyInt())).thenThrow(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO));

		try {
			service.delete(ID);
		} catch (Exception e) {
			assertEquals(ObjectNotFoundException.class, e.getClass());
			assertEquals(OBJETO_NAO_ENCONTRADO, e.getMessage());
		}
	}
	
	
	private void startUser() {
		user = new User(ID, NOME, EMAIL, SENHA);
		userDTO = new UserDTO(ID, NOME, EMAIL, SENHA);
		optionalUser = Optional.of(new User(ID, NOME, EMAIL, SENHA));
	}
}
