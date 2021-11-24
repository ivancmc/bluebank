package com.squad.dezktop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.repository.ClienteRepository;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired ContaService contaService;
	
	public ClienteModel create(ClienteModel cliente) {
		cliente.setConta(contaService.create(cliente));
		return clienteRepository.save(cliente);
	}
	
	public List<ClienteModel> getAll() {
		return clienteRepository.findAll();
	}
	
	public ResponseEntity<ClienteModel> getByCpf(String cpf) {
		return clienteRepository.findById(cpf).map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
	}
	
	public ClienteModel update(String cpf, ClienteModel cliente) {
		ClienteModel clienteAtualizado = getByCpf(cpf).getBody();
		
		if (clienteAtualizado == null) {
			return null;
		}
		
		clienteAtualizado.setDataNascimento(cliente.getDataNascimento());
		clienteAtualizado.setEmail(cliente.getEmail());
		clienteAtualizado.setEndereco(cliente.getEndereco());
		clienteAtualizado.setNome(cliente.getNome());
		clienteAtualizado.setRg(cliente.getRg());
		clienteAtualizado.setSenha(cliente.getSenha());
		clienteAtualizado.setTelefone(cliente.getTelefone());
		
		return clienteRepository.save(clienteAtualizado);
	}
	
	public void delete(String cpf) {
		ClienteModel cliente = getByCpf(cpf).getBody();
		clienteRepository.delete(cliente);
	}
	

}
