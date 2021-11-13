package com.squad.dezktop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.ContaModel;
import com.squad.dezktop.service.ContaService;

@RestController
@RequestMapping("conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<Object> getById(@PathVariable String cpf){
		return contaService.getByCpf(cpf) != null ? ResponseEntity.ok(contaService.getByCpf(cpf)) : 
			 ResponseEntity.notFound().build();
	}
	
	@GetMapping(value = "/numero/{numero}")
	public ResponseEntity<ContaModel> getByNumero(@PathVariable String numero){
		return contaService.getByNumero(numero);
	}
}
