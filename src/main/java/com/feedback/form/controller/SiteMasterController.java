package com.feedback.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.form.model.SiteMaster;
import com.feedback.form.service.SiteMasterService;

@RestController
@RequestMapping("/site_master")
public class SiteMasterController {

	@Autowired
	private SiteMasterService siteMasterService;
	
	@PostMapping("/new")
	public ResponseEntity<SiteMaster> addSiteMaster(@RequestBody SiteMaster site){
		SiteMaster newSiteMaster = siteMasterService.addSiteMaster(site);
		return ResponseEntity.status(HttpStatus.CREATED).body(newSiteMaster);
	}
}
