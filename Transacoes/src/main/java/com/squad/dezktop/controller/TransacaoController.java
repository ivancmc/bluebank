package com.squad.dezktop.controller;

import java.util.List;

import javax.annotation.Generated;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("extrato/{numeroConta}")
	public ResponseEntity<List<TransacaoModel>> extrato(@PathVariable String numeroConta){
		return ResponseEntity.ok(transacaoService.extrato(numeroConta));
	}
	
	@PostMapping("deposito/{numero}/{valor}")
	public ResponseEntity<TransacaoModel> deposito (@PathVariable String numero, @PathVariable String valor){
		TransacaoModel transacao = transacaoService.deposito(numero, valor);
		return transacao != null ? ResponseEntity.status(HttpStatus.CREATED).body(transacao) : ResponseEntity.notFound().build();
	}
	
	@PostMapping("saque/{numero}/{valor}")
	public ResponseEntity<TransacaoModel> saque(@PathVariable String numero, @PathVariable String valor){
		try {
			TransacaoModel transacao = transacaoService.saque(numero, valor);
			return transacao != null ? ResponseEntity.status(HttpStatus.CREATED).body(transacao) : ResponseEntity.notFound().build();
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}
	
	@PostMapping("transferencia")
	public ResponseEntity<List<TransacaoModel>> transferencia (@RequestBody TransacaoModel transacao){
		try {
			List<TransacaoModel> transacoes = transacaoService.transferencia(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(transacoes);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}
