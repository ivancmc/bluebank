package com.squad.dezktop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSSNSConfig {

	// Ana
	public static final String ACCESS_KEY = "AKIA6BZRT7L4YR72JVVZ";
	public static final String SECRET_KEY = "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";
	
//	public static final String SECRET_KEY = "AKIA6BZRT7L4ZKEEEO5K";
//	public static final String ACCESS_KEY = "pTqPnN2kGFxrT8gteWa89olynqj5yReEdjTT46xj";

	@Primary
	@Bean
	public AmazonSNSClient getSnsClient() {
		return (AmazonSNSClient) AmazonSNSClientBuilder.standard().withRegion(Regions.US_EAST_1)
				.withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY)))
				.build();
	}
}