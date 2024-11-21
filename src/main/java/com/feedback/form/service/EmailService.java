package com.feedback.form.service;

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

	@Value("${spring.mail.username}")
	private String from;

	@Async
	public void sendEmail(String to, String subject, String body) {

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
		helper.setText("Dear Sir, \n\n"
				+ "We want to thank you for taking the time to provide us with your valuable feedback. Your insights are crucial to helping us improve our services.\n\n"
				+ "We appreciate your honest feedback.\n\n" 
				+ "Your satisfaction is our priority, and we are committed to providing you with the best possible service.\n\n"
				+ "Thank you once again for your feedback.\n\n" 
				+ "Sincerely,  \n" 
				+ "Team Operations \n"
				+ "iSmart Facitech Pvt Ltd \n\n"
				+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________");

		// Attach the file
		if (file != null && file.length != 0) {
			System.out.println("report having data");
			String attachmentName = "FeedbackReport.xlsx";

			DataSource dataSource = new ByteArrayDataSource(file,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			helper.addAttachment(attachmentName, dataSource);
		}
		javaMailSender.send(message);
		System.out.println("mail send");
	}

	@Async
	public void sendReportToAdmin(String to, byte[] file) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(to);
		helper.setSubject("Site Report - Pending Feedback Forms");
		helper.setText("Dear Sir/Madam, \n\n"
				+ "Hope this message finds you well.\n" 
				+ "Please find attached the report listing the sites that have not yet submitted their feedback forms. \n"
				+ "Kindly review the document at your earliest convenience. \n\n"
				+ "Sincerely,  \n" 
				+ "Team Operations \n"
				+ "iSmart Facitech Pvt Ltd \n\n"
				+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________");

		// Attach the file
		if (file != null && file.length != 0) {
			System.out.println("report having data");
			String attachmentName = "SiteReport.xlsx";

			DataSource dataSource = new ByteArrayDataSource(file,
					"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			helper.addAttachment(attachmentName, dataSource);
		}
		javaMailSender.send(message);
	}
}
