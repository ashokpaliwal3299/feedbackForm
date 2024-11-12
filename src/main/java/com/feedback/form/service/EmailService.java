package com.feedback.form.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender javaMailSender;


	@Value("$(spring.mail.username)")
	private String from;

	@Async
	public void sendEmail(String to, String subject, String body){
				
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		message.setFrom(from);
		
		javaMailSender.send(message);
	}
	
	@Async
	public void sendReportToSiteIncharge(String to, byte[] file) throws MessagingException, IOException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject("Feedback form report.");
		helper.setText("Dear %s, \n\n"
				+ "Thank you for your time, please find attached feedback report.\n\n"
				+ "If you have any questions or need further assistance, feel free to reach out to us.\r\n"
				+ "\r\n" + "Thanks & Regards \n" + "Ismart Facitech Pvt. Ltd.");

		// Attach the file
		if (file != null && file.length != 0) {
			System.out.println("report having data");
			String attachmentName = "FeedbackReport.xlsx"; 
	        
	        DataSource dataSource = new ByteArrayDataSource(file, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        helper.addAttachment(attachmentName, dataSource);
		}
		javaMailSender.send(message);
		System.out.println("mail send");
	}
}
