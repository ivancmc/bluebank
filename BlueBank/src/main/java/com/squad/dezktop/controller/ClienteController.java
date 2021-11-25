package com.squad.dezktop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.service.ClienteService;
import com.squad.dezktop.service.ContaService;

@RestController
@RequestMapping
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@Autowired
	ContaService contaService;

	@GetMapping("clientes")
	public ResponseEntity<List<ClienteModel>> getAll() {
		return ResponseEntity.ok(clienteService.getAll());
	}

	@PostMapping("cliente")
	public ResponseEntity<Object> post(@RequestBody ClienteModel cliente) {
		try {
			if (clienteService.getByCpf(cliente.getCpf()).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
			} else {
				return new ResponseEntity<>("CPF já cadastrado", HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível criar o cliente, verifique os dados.",
					HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "cliente/{cpf}")
	public ResponseEntity<ClienteModel> getByCpf(@PathVariable String cpf) {
		return clienteService.getByCpf(cpf);
	}

	@DeleteMapping(value = "cliente/{cpf}")
	public ResponseEntity<Object> delete(@PathVariable String cpf) {
		clienteService.delete(cpf);
		return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
	}

	@PutMapping(value = "cliente/{cpf}")
	public ResponseEntity<ClienteModel> update(@PathVariable String cpf, @RequestBody ClienteModel cliente) {
		return ResponseEntity.ok().body(clienteService.update(cpf, cliente));
	}

}
