package com.squad.dezktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.service.ClienteService;
import com.squad.dezktop.service.ContaService;

@RestController
@RequestMapping(value = "cliente")
public class ClienteController 
{
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	ContaService contaService;

	@GetMapping
	public ResponseEntity<List<ClienteModel>> getAll(){
		return ResponseEntity.ok(clienteService.getAll());
	}
	
	@PostMapping
	public ResponseEntity<ClienteModel> post (@RequestBody ClienteModel cliente){
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
	}
	
	@GetMapping(value = "/{cpf}")
	public ResponseEntity<ClienteModel> getById(@PathVariable String cpf){
		return clienteService.getByCpf(cpf);
	}
	
	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<Void> delete(@PathVariable String cpf) {
	clienteService.delete(cpf);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{cpf}")
	public ResponseEntity<ClienteModel> update(@PathVariable String cpf, @RequestBody 
			ClienteModel cliente){
		return ResponseEntity.ok().body(clienteService.update(cpf, cliente));
	}

}
