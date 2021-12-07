package com.squad.dezktop.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.squad.dezktop.model.ContaModel;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class ContaServiceTest {
	@Autowired 
	ContaService contaService;
	
	ContaModel conta = new ContaModel();
	
	@Test
	public void testeCreditar() throws Exception {
		ContaModel contaVerifica = contaService.getByCpf("40680153098");
		ContaModel contaTeste = contaService.mudaSaldo(contaVerifica.getNumeroConta(),"credita" , "50");
		
		assertEquals(contaVerifica.getSaldo().add(new BigDecimal(50)), contaTeste.getSaldo());
	}
	
	@Test
	public void testeDebitar() throws Exception {
		ContaModel contaVerifica = contaService.getByCpf("40680153098");
		ContaModel contaTeste = contaService.mudaSaldo(contaVerifica.getNumeroConta(),"debita" , "50");
		
		assertEquals(contaVerifica.getSaldo().subtract(new BigDecimal(50)), contaTeste.getSaldo());
		
	}
	
}
