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

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;
import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.service.ClienteService;
import com.squad.dezktop.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Cliente", description = " ")
@RestController
@RequestMapping
public class ClienteController {
	@Autowired
	private ClienteService clienteService;

	@Autowired
	ContaService contaService;

	@Autowired
	private AmazonSNSClient snsClient;

	String TOPIC_ARN = "arn:aws:sns:us-east-1:965934840569:dezktop_sns";

	@ApiOperation(value = "Retorna uma lista com todos os clientes.")
	@GetMapping(value = "clientes", produces = "application/json")
	public ResponseEntity<List<ClienteModel>> getAll() {
		return ResponseEntity.ok(clienteService.getAll());
	}

	@ApiOperation(value = "Cadastra um novo cliente.", notes = "Para cadastrar um novo cliente é necessário inserir algumas informações pessoais como: \n- Nome completo, endereço, número de telefone, data de nascimento, CPF, RG, email e senha."
			+ "\n- Os dados bancários são gerados automaticamente.")
	@PostMapping(value = "cliente", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Object> post(@RequestBody ClienteModel cliente) {
		try {
			if (clienteService.getByCpf(cliente.getCpf()).getStatusCode().equals(HttpStatus.NOT_FOUND)) {
				return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.create(cliente));
			} else {
				return new ResponseEntity<>("CPF já cadastrado", HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível criar o cliente, verifique os dados.",
					HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Retorna um cliente.")
	@GetMapping(value = "cliente/{cpf}", produces = "application/json")
	public ResponseEntity<ClienteModel> getByCpf(@PathVariable String cpf) {
		return clienteService.getByCpf(cpf);
	}

	@ApiOperation(value = "Deleta um cliente.")
	@DeleteMapping(value = "cliente/{cpf}", produces = "application/json")
	public ResponseEntity<Object> delete(@PathVariable String cpf) {
		try {
			clienteService.delete(cpf);
			return new ResponseEntity<>("Cliente deletado com sucesso.", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Atualiza os dados de um cliente.", notes = "Para atualizar os dados do cliente é necessário inserir o número de CPF no campo indicado.\n"
			+ "\n- Não é possível alterar o CPF ou os dados bancários.")
	@PutMapping(value = "cliente/{cpf}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<ClienteModel> update(@PathVariable String cpf, @RequestBody ClienteModel cliente) {
		try {
			return ResponseEntity.ok().body(clienteService.update(cpf, cliente));
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@ApiOperation(value = "Inscreve o email no tópico de notificações.")
	@GetMapping("/inscrever/{email}")
	public String inscrever(@PathVariable String email) {
		SubscribeRequest request = new SubscribeRequest(TOPIC_ARN, "email", email);
		snsClient.subscribe(request);
		return "Inscrição pendente. Para confirmar a inscrição na lista, verifique seu email : " + email;
	}

	@ApiOperation(value = "Envia o email de boas vindas para o tópico de notificações.")
	@GetMapping("/notificar")
	public String notificarParaTopico() {
		PublishRequest publishRequest = new PublishRequest(TOPIC_ARN, montarEmail(), "BlueBank: vai lá e PAN!");
		snsClient.publish(publishRequest);
		return "Notificação enviada com sucesso!";
	}

	private String montarEmail() {
		return "Caro Cliente,\n" + "\n" + "Seja bem-vindo ao Blue Bank!" + "\n";
	}
}