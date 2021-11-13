package com.squad.dezktop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.service.TransacaoService;


@RestController
@RequestMapping
public class TransacaoController 
{
	@Autowired
	private TransacaoService transacaoService;
	
	@PostMapping(value = "deposito")
	public ResponseEntity<Object> post (@RequestBody TransacaoModel transacao){
		return ResponseEntity.status(HttpStatus.CREATED).body(transacaoService.deposito(transacao));
	}
}
