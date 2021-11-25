package com.demo.springSES.springSESDemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.demo.springSES.springSESDemo.dto.EmailDetails;
import com.demo.springSES.springSESDemo.service.SesService;

@RestController
public class sesController {
	
	@Autowired
	private SesService sesService;
	
	@GetMapping("/sendmail")
	public String sendMessage(@RequestParam String fromEmail,
							  @RequestParam String toEmail,
							  @RequestParam String subject,
							  @RequestParam String body) {
		
		SendEmailRequest request = new SendEmailRequest();
		
		
		SimpleMailMessage simpleMailmessage = new SimpleMailMessage();
		simpleMailmessage.setFrom(fromEmail);
		simpleMailmessage.setTo(toEmail);
		simpleMailmessage.setSubject(subject);
		simpleMailmessage.setText(body);
		
		sesService.sendMessage(simpleMailmessage);
		
		return "Mail Sent";
		
	}
	
	@RequestMapping(value = "/sendHTML", method = RequestMethod.POST)
    public ResponseEntity < String > persistPerson(@RequestBody EmailDetails emailDetails) {
     
		
		sesService.sendHTMLMessage(emailDetails);
		
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
