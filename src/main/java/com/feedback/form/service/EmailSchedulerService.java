package com.feedback.form.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.feedback.form.Dto.ApiRequest;
import com.feedback.form.Dto.ApiResponse;
import com.feedback.form.model.ClientMaster;
import com.feedback.form.model.ClientSiteMaster;
import com.feedback.form.model.FeedbackForm;
import com.feedback.form.repository.ClientMasterRepository;

@Service
public class EmailSchedulerService {

	private String url = "https://feed-back-form-nine.vercel.app/feedBackForm/"; 

	@Autowired
	private EmailService emailService;

	@Autowired
	private ClientSiteMasterService siteService;

	@Autowired
	private FeedbackFormService formService;

	// auto mail procces to all sites in our Local system
	@Scheduled(cron = "0 00 15 19 * ?")
	public void checkAndProcessForms() {
		System.out.println("scheduler runing...");
		List<ClientSiteMaster> formsToProcess = siteService.getAllClientSite();

		for (ClientSiteMaster site : formsToProcess) {
			triggerEmails(site, url + site.getId());
		}
	}

	private void triggerEmails(ClientSiteMaster site, String url) {
		if (site != null && site.getEmail() != null) {
			String to = site.getEmail();
			System.out.println("to " + to);
			String subject = "Your Feedback Matters - Share Your Experience";
			String body = "Dear Sir, \n\n" + "We hope this email finds you well.\n\n"
					+ "At iSmart Facitech Pvt Ltd, we strive to provide exceptional Facilities Management services. Your feedback is invaluable to us as it helps us identify areas for improvement and continue delivering the highest quality service.\n\n"
					+ "We would be grateful if you could take a few minutes to complete the attached feedback form. Your insights will help us enhance our services and better meet your needs.\n\n"
					+ "Thank you for your time and cooperation.\n\n" + "Feedback form link - " + url + " \n\n"
					+ "Sincerely,\r\n" + "Team Operations \r\n" + "iSmart Facitech Pvt Ltd \n\n"
					+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________";
			emailService.sendEmail(to, subject, body);
		}
	}

	// Auto Mail to remaining sites who not fill feedback form in current month
	// remaining sites who not fill feedback form in current month
	public List<ClientSiteMaster> remainigClientSites() {

		LocalDate currentDate = LocalDate.now();
		Integer year = currentDate.getYear();

		String month = currentDate.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);

		List<ClientSiteMaster> allSite = siteService.getAllClientSite();

		List<FeedbackForm> formFilledSites = formService.siteWhoFilledFormInYearAndMonth(year, month);

		List<ClientSiteMaster> remainingSites = allSite.stream()
				.filter(site -> formFilledSites.stream().noneMatch(form -> form.getSiteId().equals(site.getId())))
				.collect(Collectors.toList());

		return remainingSites;
	}

	@Scheduled(cron = "0 00 18 19 * ?")
	public void checkRemainSiteAndReminder() {
		System.out.println("scheduler runing...");
		List<ClientSiteMaster> remainSites = remainigClientSites();

		for (ClientSiteMaster site : remainSites) {
			triggerRemainderEmails(site, url + site.getId());
		}
	}

	private void triggerRemainderEmails(ClientSiteMaster site, String url) {
		if (site != null && site.getEmail() != null) {
			String to = site.getEmail();
			System.out.println("to " + to);
			String subject = "Remainder: Your Feedback Matters - Share Your Experience";
			String body = "Dear Sir, \n\n" + "We hope this email finds you well.\n\n"
					+ "We're following up on our previous email regarding your feedback on the Facilities Management services provided by iSmart Facitech Pvt Ltd. Your insights are crucial to help us improve our services.\n\n"
					+ "Please take a moment to review the attached feedback form and share your thoughts. Your feedback will directly contribute to enhancing our future service delivery.\n\n"
					+ "Thank you for your time and cooperation.\n\n" + "Feedback form link - " + url + " \n\n"
					+ "Sincerely,\r\n" + "Team Operations \r\n" + "iSmart Facitech Pvt Ltd \n\n"
					+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________";
			emailService.sendEmail(to, subject, body);
		}
	}

	// ******* close auto mail remaining sites ***********************************

//	@Autowired
//	private RestTemplate restTemplate;
//	
//	@Autowired
//	private ClientMasterRepository clientRepo;
//
//	private static final String THIRD_PARTY_API_URL = "https://smartfm.ismartfacitechpl.com/smart_to_maxus_api.php";
//
//	public List<ApiResponse> callAllClients() {
//		ApiRequest request = new ApiRequest("all_clients");
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.set("Content-Type", "application/json");
//
//		HttpEntity<ApiRequest> entity = new HttpEntity<>(request, headers);
//
//		ResponseEntity<String> rawResponse = restTemplate.exchange(THIRD_PARTY_API_URL, HttpMethod.POST, entity,
//				String.class);
//
//		System.out.println("Raw Response: " + rawResponse.getBody());
//
//		// Deserialize JSON response ApiResponse
//		ResponseEntity<List<ApiResponse>> response = restTemplate.exchange(THIRD_PARTY_API_URL, HttpMethod.POST, entity,
//				new ParameterizedTypeReference<List<ApiResponse>>() {
//				});
//
//		List<ApiResponse> apiResponseList = response.getBody();
//
//		if (apiResponseList != null) {
//			apiResponseList.forEach(client -> {
//
//				if (client.getClient_group_code() == null) {
//					client.setClient_group_code(null);
//				}
//				if (client.getId() == null) {
//					client.setId(null);
//				}
//				if (client.getName() == null) {
//					client.setName(null);
//				}
//			});
//		}
//		
//		return apiResponseList;
//	}

	// Auto mail to all client in our system
//		@Scheduled(cron = "0 55 17 12 * ?")
//		public void checkAndProcessForms1() {
//			System.out.println("scheduler runing...");
//			List<ApiResponse> formsToProcess =  callAllClients();
	//
//			for (ApiResponse site : formsToProcess) {
//				triggerEmails(site, url + site.getId());
//			}
//		}

//		private void triggerEmails(ApiResponse site, String url) {
//			if (site != null && site.getEmail() != null) {
//				String to = site.getEmail();
//				System.out.println("to " + to);
//				String subject = "Your Feedback Matters - Share Your Experience";
//				String body = "Dear Sir, \n\n" + "We hope this email finds you well.\n\n"
//						+ "At iSmart Facitech Pvt Ltd, we strive to provide exceptional Facilities Management services. Your feedback is invaluable to us as it helps us identify areas for improvement and continue delivering the highest quality service.\n\n"
//						+ "We would be grateful if you could take a few minutes to complete the attached feedback form. Your insights will help us enhance our services and better meet your needs.\n\n"
//						+ "Thank you for your time and cooperation.\n\n" + "Feedback form link - " + url + " \n\n"
//						+ "Sincerely,\r\n" + "Team Operations \r\n" + "iSmart Facitech Pvt Ltd \r\n"
//						+ "This is a system generated email and not manned. To communicate with us, kindly email on  _____________";
//				emailService.sendEmail(to, subject, body);
//			}
//		}
	
//	 public String getClients() {
//	        String url = "https://smartfm.ismartfacitechpl.com/smart_to_maxus_api.php";
//
//	        String jsonPayload = "{\"action\": \"all_sites\"}";
//
//	        HttpHeaders headers = new HttpHeaders();
//	        headers.setContentType(MediaType.APPLICATION_JSON);
//
//	        HttpEntity<String> entity = new HttpEntity<>(jsonPayload, headers);
//
//	        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);
//	        System.out.println("response is : " + response);
//	        return response.getBody();
//	    }

}
