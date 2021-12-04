package com.squad.dezktop;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import com.squad.dezktop.model.ClienteModel;
import com.squad.dezktop.service.ClienteService;
import com.squad.dezktop.service.ContaService;
@SpringBootApplication
@EnableEurekaClient
public class BlueBankApplication implements CommandLineRunner {
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ContaService contaService;
	
	public static void main(String[] args) {
		SpringApplication.run(BlueBankApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		if (clienteService.getAll().isEmpty()) {			
			Calendar cal = Calendar.getInstance();
			
			ClienteModel cliente1 = new ClienteModel();
			cliente1.setCpf("40680153098");
			cal.set(Calendar.YEAR, 1985);
			cal.set(Calendar.MONTH, 10);
			cal.set(Calendar.DAY_OF_MONTH, 17);
			cliente1.setDataNascimento(cal);
			cliente1.setEmail("alana@gmail.com");
			cliente1.setEndereco("Maceio");
			cliente1.setNome("Alana Amaral");
			cliente1.setRg("483761126");
			cliente1.setSenha("2294");
			cliente1.setTelefone("34884483");
			clienteService.create(cliente1);
			contaService.mudaSaldo(cliente1.getConta().getNumeroConta(), "credita", "3000");
			
			ClienteModel cliente2 = new ClienteModel();
			cliente2.setCpf("46466174042");
			cal.set(Calendar.YEAR, 1978);
			cal.set(Calendar.MONTH, 3);
			cal.set(Calendar.DAY_OF_MONTH, 3);
			cliente2.setDataNascimento(cal);
			cliente2.setEmail("vinicius@gmail.com");
			cliente2.setEndereco("Joao Pessoa");
			cliente2.setNome("Vinicius Silva");
			cliente2.setRg("408112396");
			cliente2.setSenha("5276");
			cliente2.setTelefone("29284845");
			clienteService.create(cliente2);
			contaService.mudaSaldo(cliente2.getConta().getNumeroConta(), "credita", "500");
			
			ClienteModel cliente3 = new ClienteModel();
			cliente3.setCpf("60799848069");
			cal.set(Calendar.YEAR, 1988);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DAY_OF_MONTH, 5);
			cliente3.setDataNascimento(cal);
			cliente3.setEmail("carla@gmail.com");
			cliente3.setEndereco("Maceio");
			cliente3.setNome("Carla Braga");
			cliente3.setRg("409810721");
			cliente3.setSenha("8632");
			cliente3.setTelefone("26389359");
			clienteService.create(cliente3);
			contaService.mudaSaldo(cliente3.getConta().getNumeroConta(), "credita", "6000");
			
			ClienteModel cliente4 = new ClienteModel();
			cliente4.setCpf("64973227013");
			cal.set(Calendar.YEAR, 2001);
			cal.set(Calendar.MONTH, 9);
			cal.set(Calendar.DAY_OF_MONTH, 12);
			cliente4.setDataNascimento(cal);
			cliente4.setEmail("Pedro@gmail.com");
			cliente4.setEndereco("Rio de Janeiro");
			cliente4.setNome("Pedro Araujo");
			cliente4.setRg("178377727");
			cliente4.setSenha("1995");
			cliente4.setTelefone("23132550");
			clienteService.create(cliente4);
			contaService.mudaSaldo(cliente4.getConta().getNumeroConta(), "credita", "9000");
			
			ClienteModel cliente5 = new ClienteModel();
			cliente5.setCpf("84214772008");
			cal.set(Calendar.YEAR, 1996);
			cal.set(Calendar.MONTH, 6);
			cal.set(Calendar.DAY_OF_MONTH, 20);
			cliente5.setDataNascimento(cal);
			cliente5.setEmail("leticia@gmail.com");
			cliente5.setEndereco("Sao paulo");
			cliente5.setNome("Leticia Souza");
			cliente5.setRg("363104604");
			cliente5.setSenha("3790");
			cliente5.setTelefone("32208108");
			clienteService.create(cliente5);
			contaService.mudaSaldo(cliente5.getConta().getNumeroConta(), "credita", "7500");
		}
	}
}