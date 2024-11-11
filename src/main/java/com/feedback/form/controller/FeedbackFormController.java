package com.feedback.form.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.form.model.FeedbackForm;
import com.feedback.form.service.FeedbackFormService;

@RestController
@RequestMapping("/feedback")
public class FeedbackFormController {

	@Autowired
	private FeedbackFormService feedbackFormService;
	
	@PostMapping("/new/{siteId}")
	public ResponseEntity<FeedbackForm> addFeedback(@RequestBody FeedbackForm form, @PathVariable Long siteId){
		FeedbackForm newFeedback = feedbackFormService.addFeedbackForm(form, siteId);
		return ResponseEntity.status(HttpStatus.CREATED).body(newFeedback);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<FeedbackForm>> allFeedback(){
		List<FeedbackForm> allFeedback = feedbackFormService.allFeedbackForm();
		return ResponseEntity.status(HttpStatus.OK).body(allFeedback);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<FeedbackForm>> FeedbackFormById(@PathVariable Long id){
		Optional<FeedbackForm> feedbackForm = feedbackFormService.getFeedbackFormById(id);
		return ResponseEntity.status(HttpStatus.OK).body(feedbackForm);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteFeedback(@PathVariable Long id){
		String res = feedbackFormService.deleteFeedBack(id);
		return ResponseEntity.status(HttpStatus.OK).body(res);
	} 
}
