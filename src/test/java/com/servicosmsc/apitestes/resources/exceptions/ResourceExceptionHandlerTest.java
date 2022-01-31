package com.servicosmsc.apitestes.resources.exceptions;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import com.servicosmsc.apitestes.services.exceptions.DataIntegrityException;
import com.servicosmsc.apitestes.services.exceptions.ObjectNotFoundException;

@SpringBootTest
class ResourceExceptionHandlerTest {
	
	private static final String ERRO_DE_VALIDACAO = "Erro de validação";

	private static final String EMAIL_JA_CADASTRADO = "E-mail Já cadastrado";

	private static final String OBJETO_NAO_ENCONTRADO = "Objeto não encontrado";
	
	@InjectMocks
	private ResourceExceptionHandler exceptionHandler;

	@BeforeEach
	void setUp(){
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testObjectNotFound() {
		ResponseEntity<StandardError> response = exceptionHandler
				.objectNotFound(new ObjectNotFoundException(OBJETO_NAO_ENCONTRADO), new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(OBJETO_NAO_ENCONTRADO, response.getBody().getMsg());
		assertEquals(404, response.getBody().getStatus());
	}

	@Test
	void testDataIntegrity() {
		ResponseEntity<StandardError> response = exceptionHandler
				.dataIntegrity(new DataIntegrityException(EMAIL_JA_CADASTRADO), new MockHttpServletRequest());
		
		assertNotNull(response);
		assertNotNull(response.getBody());
		
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(StandardError.class, response.getBody().getClass());
		assertEquals(EMAIL_JA_CADASTRADO, response.getBody().getMsg());
		assertEquals(400, response.getBody().getStatus());
	}

	@Test
	void testValidation() {
		fail("Not yet implemented");
	}

}
