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

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Transações", description = " ")
@RestController
@RequestMapping
public class TransacaoController {
	@Autowired
	private TransacaoService transacaoService;

	@ApiOperation(value = "Retorna o extrato da conta especificada no mês corrente.", produces = "application/json")
	@GetMapping("extrato/{numeroConta}")
	public ResponseEntity<List<TransacaoModel>> extrato(@PathVariable String numeroConta) {
		try {
			return ResponseEntity.ok(transacaoService.extrato(numeroConta));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Retorna o extrato da conta especificada durante o mês e ano informado.", produces = "application/json")
	@GetMapping("extrato/{numeroConta}/{mes}/{ano}")
	public ResponseEntity<List<TransacaoModel>> extratoPorMes(@PathVariable String numeroConta,
			@PathVariable String mes, @PathVariable String ano) {
		try {
			return ResponseEntity.ok(transacaoService.extratoPorMes(numeroConta, mes, ano));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Realiza uma transação de depósito na conta especificada.", consumes = "application/json", produces = "application/json", notes = "Para realizar um depósito informe a conta destino e o valor.")
	@PostMapping("deposito")
	public ResponseEntity<TransacaoModel> deposito(@RequestBody TransacaoModel transacao) {
		try {
			TransacaoModel deposito = transacaoService.deposito(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(deposito);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "Realiza uma transação de saque na conta especificada.", consumes = "application/json", produces = "application/json", notes = "Para realizar um saque informe a conta de origem e o valor.")
	@PostMapping("saque")
	public ResponseEntity<TransacaoModel> saque(@RequestBody TransacaoModel transacao) {
		try {
			TransacaoModel saque = transacaoService.saque(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(saque);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);
		}
	}

	@ApiOperation(value = "Realiza transações (agendadas ou não) para contas internas e externas incluindo pagamentos.", consumes = "application/json", produces = "application/json", notes = "Para realizar transferências entre contas Bluebank informe:"
			+ "\n- Conta destino, conta origem, valor e tipo da transação."
			+ "\n\nPara realizar transferências para uma conta externa informe:"
			+ "\n- Conta origem, valor, tipo da transação e os dados cabíveis da conta externa."
			+ "\n\nPara realizar um pagamento informe:"
			+ "\n- Conta origem, valor, tipo da transação e os dados cabíveis da conta externa."
			+ "\n\nOs tipos de transações são (informe apenas o número correspondente): "
			+ "\n- 1.TED, 2.DOC, 3.PIX, 6.Pagamento, 7.Transferencia"
			+ "\n\nTodas as transações acima podem ser agendadas, para isso, informe a data de sua preferência:"
			+ "\n- Use este formato: dd/mm/yyyy."
			+ "\n\nOs campos não citados devem ser ignorados e apagados do corpo JSON.")
	@PostMapping("transacao")
	public ResponseEntity<List<TransacaoModel>> transferencia(@RequestBody TransacaoModel transacao) {
		try {
			List<TransacaoModel> transacoes = transacaoService.transferencia(transacao);
			return ResponseEntity.status(HttpStatus.CREATED).body(transacoes);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
	}
}