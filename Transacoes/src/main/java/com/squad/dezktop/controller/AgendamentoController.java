package com.squad.dezktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.service.AgendamentoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Agendamento", description = " ")
@RestController
@RequestMapping
public class AgendamentoController {
	@Autowired
	private AgendamentoService agendamentoService;

	@ApiOperation(value = "Efetiva as transações agendadas do dia.", notes = "Pode ser chamado manualmente,"
			+ " mas está configurado para ser chamado em um horário específico através da função lambda na AWS.", produces = "application/json")
	@GetMapping("agendamento/efetivar")
	public ResponseEntity<List<TransacaoModel>> agendamentos() throws Exception{
		try {
			return ResponseEntity.ok(agendamentoService.efetuarAgendamento());
		}catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}