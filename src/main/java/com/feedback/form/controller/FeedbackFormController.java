package com.feedback.form.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.feedback.form.Dto.ApiResponse;
import com.feedback.form.Dto.feedbackFormDto;
import com.feedback.form.model.FeedbackForm;
import com.feedback.form.service.EmailSchedulerService;
import com.feedback.form.service.FeedbackFormService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/feedback")
public class FeedbackFormController {

	@Autowired
	private FeedbackFormService feedbackFormService;
	
	@Autowired
	private EmailSchedulerService emailSchedulerService;

	@PostMapping("/new/{siteId}")
	public ResponseEntity<FeedbackForm> addFeedback(@RequestBody FeedbackForm form, @PathVariable Long siteId)
			throws IOException, MessagingException {
		FeedbackForm newFeedback = feedbackFormService.addFeedbackForm(form, siteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(newFeedback);
	}

	@GetMapping("/")
	public ResponseEntity<List<FeedbackForm>> allFeedback() {
		List<FeedbackForm> allFeedback = feedbackFormService.allFeedbackForm();
		return ResponseEntity.status(HttpStatus.OK).body(allFeedback);
	}

	@GetMapping("/all-percenatage")
	public ResponseEntity<List<feedbackFormDto>> allFeedbackPercentage() {
		List<feedbackFormDto> allFeedback = feedbackFormService.allFeedbackFormPercentage();
		return ResponseEntity.status(HttpStatus.OK).body(allFeedback);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Optional<FeedbackForm>> FeedbackFormById(@PathVariable Long id) {
		Optional<FeedbackForm> feedbackForm = feedbackFormService.getFeedbackFormById(id);
		return ResponseEntity.status(HttpStatus.OK).body(feedbackForm);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFeedback(@PathVariable Long id) {
		String res = feedbackFormService.deleteFeedBack(id);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@GetMapping("/avg-percenatage/{siteId}/year/{year}")
	public ResponseEntity<Double> avgPercentage(@PathVariable Long siteId, @PathVariable int year) {
		double avgPercentaeg = feedbackFormService.allFeedbackFormOfSiteByYear(siteId, year);
		return ResponseEntity.status(HttpStatus.OK).body(avgPercentaeg);
	}

	@GetMapping("/excel/{id}")
	public ResponseEntity<byte[]> generateExcel(@PathVariable Long id) throws IOException {
		byte[] excelData = feedbackFormService.excelExportOfInspectionForm(id);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=feedback.xlsx");
		headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		return new ResponseEntity<>(excelData, headers, HttpStatus.OK);
	}
	
	@GetMapping("/get-clients")
    public String getClients() {
        return emailSchedulerService.getClients();
    }
	
}
