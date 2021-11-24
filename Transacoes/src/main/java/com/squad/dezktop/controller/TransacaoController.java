package com.squad.dezktop.controller;

import java.util.List;
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
	
	@GetMapping("extrato/{numeroConta}/{mes}/{ano}")
	public ResponseEntity<List<TransacaoModel>> extratoPorMes(@PathVariable String numeroConta, @PathVariable String mes, @PathVariable String ano){
		return ResponseEntity.ok(transacaoService.extratoPorMes(numeroConta, mes, ano));
	}

	@PostMapping("deposito")
	public ResponseEntity<TransacaoModel> deposito (@RequestBody TransacaoModel transacao){
		try {
			TransacaoModel deposito = transacaoService.deposito(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(deposito);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("saque")
	public ResponseEntity<TransacaoModel> saque(@RequestBody TransacaoModel transacao){
		try {
			TransacaoModel saque = transacaoService.saque(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(saque);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping({"transferencia", "pagamento"})
	public ResponseEntity<List<TransacaoModel>> transferencia (@RequestBody TransacaoModel transacao){
		try {
			List<TransacaoModel> transacoes = transacaoService.transferencia(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(transacoes);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}