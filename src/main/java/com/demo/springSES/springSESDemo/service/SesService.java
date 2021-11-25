package com.demo.springSES.springSESDemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.demo.springSES.springSESDemo.dto.EmailDetails;


@Service
public class SesService {
	
	 @Autowired
	 private MailSender mailSender;
	 
	 @Autowired
	 private AmazonSimpleEmailService amazonSimpleEmailService;
	 
	 public void sendMessage(SimpleMailMessage simpleMailmessage) {
		 
		 this.mailSender.send(simpleMailmessage);
	 }

	public void sendHTMLMessage(EmailDetails emailDetails) {
		String HTMLBODY = "<h1>Amazon SES test (AWS SDK for Java)</h1>"
			      + "<p>This email was sent with <a href='https://aws.amazon.com/ses/'>"
			      + "Amazon SES</a> using the <a href='https://aws.amazon.com/sdk-for-java/'>" 
			      + "AWS SDK for Java</a>";
		
		String TEXTBODY = "This email was sent through Amazon SES "
			      + "using the AWS SDK for Java.";
		
		 String CONFIGSET = "ConfigSet";
		
		 try {
			 
			 SendEmailRequest sendEmailRequest = new SendEmailRequest()
			          .withDestination(
			              new Destination().withToAddresses(emailDetails.getToMail()))
			          .withMessage(new Message()
			              .withBody(new Body()
			                  .withHtml(new Content()
			                      .withCharset("UTF-8").withData(HTMLBODY))
			                  .withText(new Content()
			                      .withCharset("UTF-8").withData(TEXTBODY)))
			              .withSubject(new Content()
			                  .withCharset("UTF-8").withData(emailDetails.getSubject())))
			          .withSource(emailDetails.getFromMail());
			          // Comment or remove the next line if you are not using a
			          // configuration set
			        //  .withConfigurationSetName(CONFIGSET);
			 
			 
			 
			 amazonSimpleEmailService.sendEmail(sendEmailRequest);
		      System.out.println("Email sent!");
		    } catch (Exception ex) {
		      System.out.println("The email was not sent. Error message: " 
		          + ex.getMessage());
		    }
		
		
	}

}
