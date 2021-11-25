package com.demo.springSES.springSESDemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;

import io.awspring.cloud.ses.SimpleEmailServiceMailSender;

@Configuration
public class SesConfiguration {
	
	@Value("${cloud.aws.credentials.access-key}")
	private String accessKey;
	
	@Value("${cloud.aws.credentials.secret-key}")
	private String secretKey;

	
	
	  @Bean
	  public MailSender mailSender(AmazonSimpleEmailService amazonSimpleEmailService) {
	    return new SimpleEmailServiceMailSender(amazonSimpleEmailService);
	  }
	  
	  @Bean
	  public AmazonSimpleEmailService amazonSimpleEmailService() {

		  BasicAWSCredentials creds = new BasicAWSCredentials(accessKey, secretKey);
		  
	    return AmazonSimpleEmailServiceClientBuilder
	    	.standard()
	        .withCredentials(new AWSStaticCredentialsProvider(creds))
	        .withRegion(Regions.AP_SOUTH_1)
	        .build();
	  }

}
