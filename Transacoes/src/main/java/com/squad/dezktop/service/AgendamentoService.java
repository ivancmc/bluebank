package com.squad.dezktop.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

		Calendar cal = Calendar.getInstance();
		
		cal.set(Calendar.HOUR_OF_DAY, 00);
		cal.set(Calendar.MINUTE, 00);
		cal.set(Calendar.SECOND, 00);
		cal.set(Calendar.MILLISECOND, 000);
		
		List<AgendamentoModel> agendamentos = agendamentoRepository.getByAgendamento(cal.getTime());
		
		List <TransacaoModel> transacoes = new ArrayList<>();
		
		for(AgendamentoModel agendamento : agendamentos) {
	
			agendamento.getTransacao().setAgendamento(null);
			
			transacoes.addAll(transacaoService.transferencia(agendamento.getTransacao()));
			agendamento.getTransacao().setAgendamento(agendamento);
			System.out.println(agendamento);
			//agendamentoRepository.deleteByid(agendamento);
		
		}
		
		agendamentoRepository.deleteByAgendamento(cal.getTime());
		
		return transacoes;

	}

}
