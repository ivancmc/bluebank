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
	private final ResponseMessage m406 = simpleMessage(406, "Não aceito");
	private final ResponseMessage m404 = simpleMessage(404, "Não encontrado");
	private final ResponseMessage m400 = simpleMessage(400, "Erro inesperado");
	private final ResponseMessage m401 = simpleMessage(401, "Não autorizado");
	
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
				.globalResponseMessage(RequestMethod.POST, Arrays.asList(m201, m400, m406, m401));
	}
	
	private ResponseMessage simpleMessage(int code, String msg) 
	{
		return new ResponseMessageBuilder().code(code).message(msg).build();
	}

	private ApiInfo informations() {
		return new ApiInfoBuilder().title("API Bluebank: Microsserviços de Transações")
				.description("O projeto Bluebank tem como objetivo criar uma API para gerenciar as transações de um banco fictício utilizando Java, MySQL e AWS."
							+ "\nNesta página, você encontra as funções relacionadas ao nosso segundo microsserviço: Transações."
							+ "\nAqui, você pode realizar agendamentos, pagamentos, transferências internas e externas e consultar extratos."
							+ "\nVocê pode achar mais informações sobre o processo de desenvolvimento, no nosso repositório no GitHub no link abaixo :)")
				.version("1.0.0").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Bluebank repository", "https://github.com/ivancmc/bluebank", "")).build();
	}
}