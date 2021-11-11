package com.squad.dezktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.repository.ClienteRepository;

@RestController
@RequestMapping(value = "cliente")
public class ClienteController 
{
	@Autowired
	private ClienteRepository clienteRepository;
	
	@GetMapping
	public ResponseEntity<List<ClienteModel>> getAll(){
		return ResponseEntity.ok(clienteRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<ClienteModel> post (@RequestBody ClienteModel cliente){
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteRepository.save(cliente));
	}
}
