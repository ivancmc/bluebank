package com.squad.dezktop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.squad.dezktop.model.AgendamentoModel;
import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.repository.AgendamentoRepository;
import com.squad.dezktop.repository.ContaExternaRepository;
import com.squad.dezktop.repository.TransacaoRepository;


@Service
public class AgendamentoService {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	
	@Autowired
	private TransacaoRepository transacaoRepository;
	
	@Autowired
	private ContaExternaRepository contaExternaRepository;	

	public List<TransacaoModel> efetuarAgendamento() throws Exception {
		
		Locale.setDefault(new Locale("pt", "BR"));

		Date hoje = new Date(System.currentTimeMillis());
				
		List<AgendamentoModel> agendamentos = agendamentoRepository.getByAgendamento(hoje);
		
		List <TransacaoModel> transacoes = new ArrayList<>();
		
		for(AgendamentoModel agendamento : agendamentos) {	
			agendamento.getTransacao().setAgendamento(null);
			if (agendamento.getTransacao().getContaExterna() != null) {
				agendamento.getTransacao().getContaExterna().setId(null);
			}
			transacoes.addAll(transacaoService.transferencia(agendamento.getTransacao()));
			transacaoRepository.deleteById(agendamento.getTransacao().getId());		
		}		
		
		return transacoes;
	}
}