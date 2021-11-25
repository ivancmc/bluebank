package com.squad.dezktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.service.AgendamentoService;

@RestController
@RequestMapping
public class AgendamentoController {

	
	@Autowired
	private AgendamentoService agendamentoService;

	@GetMapping("agendamento/efetivar")
	public ResponseEntity<List<TransacaoModel>> agendamentos() throws Exception{
		return ResponseEntity.ok(agendamentoService.efetuarAgendamento());
	}
	
	
	
	
	
	
	
	
	
	
	
}
