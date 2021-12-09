package com.squad.dezktop.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.squad.dezktop.model.ClienteModel;

import junit.framework.Assert;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ClienteServiceTest {
	@Autowired 
	ClienteService clienteService;
	
	ClienteModel cliente = new ClienteModel();

	@Before
	public void configCliente() {
		cliente.setCpf("12345678901");
		cliente.setDataNascimento(Date.valueOf("1900-01-01"));
		cliente.setEmail("teste@gmail.com");
		cliente.setEndereco("TesteWorld");
		cliente.setNome("cliente teste");
		cliente.setRg("111111111");
		cliente.setSenha("0000");
		cliente.setTelefone("99999999");
	}
	
	@Test
	public void criarCliente() {
		clienteService.create(cliente);
		ClienteModel clienteTeste = clienteService.getByCpf("12345678901").getBody();
		assertEquals(clienteTeste.getCpf(), "12345678901");
		assertEquals(clienteTeste.getEmail(), "teste@gmail.com");
		assertEquals(clienteTeste.getEndereco(), "TesteWorld");
		assertEquals(clienteTeste.getNome(), "cliente teste");
		assertEquals(clienteTeste.getRg(), "111111111");
		assertEquals(clienteTeste.getSenha(), "0000");
		assertEquals(clienteTeste.getTelefone(), "99999999");

	}
	@Test
	public void deletarCliente() {
		try {
			clienteService.delete("46466174042");
			assertEquals(clienteService.getByCpf("46466174042"),ResponseEntity.notFound().build());
		} catch (Exception e) {
			Assert.fail();
		}
	}
	
	
}