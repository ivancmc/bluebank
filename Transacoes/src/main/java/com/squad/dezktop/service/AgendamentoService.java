package com.squad.dezktop.service;

import java.util.ArrayList;
import java.util.*;
import java.text.*;
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

		//Date hoje = new Date(System.currentTimeMillis());
		//DateFormat f = DateFormat.getDateInstance(DateFormat.SHORT);
		//System.out.println(f.format(hoje).replace("-", "/"));
		
		Date hoje = new Date();
      		DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
      		Locale BRAZIL = new Locale("pt","BR");      
      		df = DateFormat.getDateInstance(DateFormat.SHORT, BRAZIL);
		
		System.out.println(df.format(hoje));

		//List<AgendamentoModel> agendamentos = agendamentoRepository.getByAgendamento(f.format(hoje).replace("-", "/"));
		List<AgendamentoModel> agendamentos = agendamentoRepository.getByAgendamento(df.format(hoje));
		List<TransacaoModel> transacoes = new ArrayList<>();

		if (!agendamentos.isEmpty()) {
			for (AgendamentoModel agendamento : agendamentos) {
				agendamento.getTransacao().setAgendamento(null);
				if (agendamento.getTransacao().getContaExterna() != null) {
					agendamento.getTransacao().getContaExterna().setId(null);
				}
				agendamentoRepository.deleteById(agendamento.getId());
				transacoes.addAll(transacaoService.transferencia(agendamento.getTransacao()));
			}

			return transacoes;
		} else {
			throw new Exception("Não há agendamentos para hoje.");
		}
	}
}
