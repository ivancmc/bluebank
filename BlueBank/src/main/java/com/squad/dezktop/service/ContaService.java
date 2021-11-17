package com.squad.dezktop.service;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.model.ContaModel;
import com.squad.dezktop.repository.ContaRepository;

@Service
public class ContaService {
	@Autowired
	private ContaRepository contaRepository;
	
	public ContaModel create(ClienteModel cliente) {
		ContaModel conta = new ContaModel();
	    conta.setAgencia("0001");
	    conta.setNumeroConta(randomDigitsAsString(10));
	    conta.setSaldo(new BigDecimal("0.0"));
	    conta.setCliente(cliente);
	    return conta;
	}
	
	
	public ResponseEntity<ContaModel> getByNumero(String numero) {
		return contaRepository.findById(numero).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
	}
	
	public ContaModel getByCpf(String cpf) {
		return contaRepository.procuraPorCpf(cpf);
	}
	
	public ContaModel mudaSaldo(String numero, String acao, String valor) {
		ContaModel conta = getByNumero(numero).getBody();
		if (conta != null) {
			if (acao.equals("credita")) {
				conta.setSaldo(conta.getSaldo().add(new BigDecimal(valor)));
			} else if (acao.equals("debita")) {
				conta.setSaldo(conta.getSaldo().subtract(new BigDecimal(valor)));
			}
			contaRepository.save(conta);
		}
		return conta;
	}
	
	private static String randomDigitsAsString(int length) {
		int leftLimit = 48; // digito '0' na tabela ASCII
	    int rightLimit = 57; // digito '9' na tabela ASCII
	    int targetStringLength = length; // quantidade de digitos
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();

	    return generatedString;
	}
	
}
