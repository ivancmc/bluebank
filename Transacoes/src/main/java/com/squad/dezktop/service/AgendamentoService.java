package com.squad.dezktop.service;

import java.util.ArrayList;
import java.sql.Date;
import java.text.DateFormat;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.squad.dezktop.model.AgendamentoModel;
import com.squad.dezktop.model.TransacaoModel;
import com.squad.dezktop.repository.AgendamentoRepository;

@Service
public class AgendamentoService {

	@Autowired
	private TransacaoService transacaoService;

	@Autowired
	private AgendamentoRepository agendamentoRepository;
	

	public List<TransacaoModel> efetuarAgendamento() throws Exception {		

		Date hoje = new Date(System.currentTimeMillis());
		
		DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
		
		List<AgendamentoModel> agendamentos = agendamentoRepository.getByAgendamento(f.format(hoje).replace("-", "/"));
		
		List <TransacaoModel> transacoes = new ArrayList<>();	

		for(AgendamentoModel agendamento : agendamentos) {	
			agendamento.getTransacao().setAgendamento(null);
			if (agendamento.getTransacao().getContaExterna() != null) {
				agendamento.getTransacao().getContaExterna().setId(null);
			}			
			agendamentoRepository.deleteById(agendamento.getId());	
			transacoes.addAll(transacaoService.transferencia(agendamento.getTransacao()));
		}
		
		return transacoes;
	}
}