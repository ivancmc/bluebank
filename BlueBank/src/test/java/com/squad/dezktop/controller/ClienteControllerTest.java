package com.squad.dezktop.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.service.ClienteService;

import junit.framework.Assert;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClienteControllerTest {
	/*
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@Test
	public void mostrarTodosOsClientes() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/cliente",
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode()));
	}
	
	@Autowired
	private ClienteService contatoService;
	*/
}
