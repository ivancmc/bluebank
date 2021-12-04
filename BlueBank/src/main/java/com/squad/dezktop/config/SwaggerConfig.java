package com.squad.dezktop.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig 
{
	private final ResponseMessage m201 = simpleMessage(201, "Criado");
	private final ResponseMessage m401 = simpleMessage(401, "Não autorizado");
	private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
	private final ResponseMessage m400 = simpleMessage(400, "Erro inesperado");
	
	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.squad.dezktop.controller"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.apiInfo(informations())
				.globalResponseMessage(RequestMethod.GET, Arrays.asList(m404))
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m400, m401))
				.globalResponseMessage(RequestMethod.PUT, Arrays.asList(m400, m404))
				.globalResponseMessage(RequestMethod.DELETE, Arrays.asList(m400, m404));
	}
	
	private ResponseMessage simpleMessage(int code, String msg) 
	{
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

	private ApiInfo informations() {
		return new ApiInfoBuilder().title("API Bluebank: Microsserviço de clientes e contas")
		.description("O projeto Bluebank tem como objetivo criar uma API para gerenciar as transações de um banco fictício utilizando Java, MySQL e AWS."
					+ "\nNesta página, você encontra as funções relacionadas ao nosso primeiro microsserviço: Clientes e Contas."
					+ "\nAqui, você pode cadastrar e deletar clientes e contas, atualizar dados dos clientes e listar clientes e contas."
					+ "\nVocê pode achar mais informações sobre o processo de desenvolvimento, no nosso repositório no GitHub no link abaixo :)")
		.version("1.0.0").license("Apache License Version 2.0")
		.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
		.contact(new Contact("Bluebank repository", "https://github.com/ivancmc/bluebank", "")).build();
	}
}