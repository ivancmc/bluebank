package com.squad.dezktop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.squad.dezktop.model.ContaModel;
import com.squad.dezktop.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Conta", description = " ")
@RestController
@RequestMapping("conta")
public class ContaController {
	@Autowired
	private ContaService contaService;
	
	@ApiOperation(value = "Retorna uma conta ao buscar por CPF.", produces = "application/json")
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<ContaModel> getByCpf(@PathVariable String cpf){
		try {
			return ResponseEntity.ok(contaService.getByCpf(cpf));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "Retorna uma conta ao buscar por número da conta.")
	@GetMapping(value = "/numero/{numero}", produces = "application/json")
	public ResponseEntity<ContaModel> getByNumero(@PathVariable String numero){
		return contaService.getByNumero(numero);
	}
	
	@ApiOperation(value = "Adiciona valor na conta especificada. Só deve ser chamado pelo serviço de transações e não deve ser requisitado diretamente.")
	@PutMapping("{numero}/credita/{valor}")
	public ResponseEntity<ContaModel> credita(@PathVariable String numero, @PathVariable String valor){
		contaService.mudaSaldo(numero, "credita", valor);
		return contaService.getByNumero(numero);
	}

	@ApiOperation(value = "Diminui valor na conta especificada. Só deve ser chamado pelo serviço de transações e não deve ser requisitado diretamente.")
	@PutMapping("{numero}/debita/{valor}")
	public ResponseEntity<ContaModel> debita(@PathVariable String numero, @PathVariable String valor){
		contaService.mudaSaldo(numero, "debita", valor);
		return contaService.getByNumero(numero);
	}
}