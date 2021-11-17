package com.squad.dezktop;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.squad.dezktop.model.ContaModel;

@Component
@FeignClient(name = "cliente-conta", path = "/conta")
public interface WorkerFeignClient {

	@GetMapping(value = "numero/{numero}")
	ResponseEntity<ContaModel> getByNumero(@PathVariable String numero);
	
	@GetMapping(value = "cpf/{cpf}")
	ResponseEntity<ContaModel> getByCpf(@PathVariable String cpf);
	
	@PutMapping("{numero}/credita/{valor}")
	ResponseEntity<ContaModel> credita(@PathVariable String numero, @PathVariable String valor);

	@PutMapping("{numero}/debita/{valor}")
	ResponseEntity<ContaModel> debita(@PathVariable String numero, @PathVariable String valor);
}