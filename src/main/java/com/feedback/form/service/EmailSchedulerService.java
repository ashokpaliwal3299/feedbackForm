package com.feedback.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.feedback.form.model.ClientSiteMaster;


@Service
public class EmailSchedulerService {

	@Autowired
	private EmailService emailService;
	
	@Autowired
	private ClientSiteMasterService siteService;
	
	public List<ClientSiteMaster> getAllSites(){
		return siteService.getAllClientSite();
	}
	
	@Scheduled(cron = "0 11 15 11 * ?")
	public void checkAndProcessForms() {
		System.out.println("scheduler runing...");
		List<ClientSiteMaster> formsToProcess = getAllSites();
		for (ClientSiteMaster site : formsToProcess) {
			triggerEmails(site);
		}
	}

	private void triggerEmails(ClientSiteMaster site) {
		String siteIncharge = site.getInchargeName();
		String to = site.getEmail();
		String subject = "Your Feedback Matters - Share Your Experience";
		String body = "Dear " + siteIncharge  + ",\n\n"
				+ "We hope this email finds you well.\n\n"
				+ "At iSmart Facitech Pvt Ltd, we strive to provide exceptional Facilities Management services. Your feedback is invaluable to us as it helps us identify areas for improvement and continue delivering the highest quality service.\n\n"
				+ "We would be grateful if you could take a few minutes to complete the attached feedback form. Your insights will help us enhance our services and better meet your needs.\n\n"
				+ "Thank you for your time and cooperation.\n\n"
				+ "Feedback form link - https://www.google.co.in/ \n\n" 
				+ "Sincerely,\r\n"
				+ "Team Operations \r\n"
				+ "iSmart Facitech Pvt Ltd \r\n";
		emailService.sendEmail(to, subject, body);
	}
}
