package com.feedback.form.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.form.model.ClientSiteMaster;
import com.feedback.form.service.ClientSiteMasterService;

@RestController
@RequestMapping("/clientSite")
public class ClientSiteMasterController {

	@Autowired
	private ClientSiteMasterService siteService;
	
	@PostMapping("/new")
	public ResponseEntity<ClientSiteMaster> addClientSiteMaster(@RequestBody ClientSiteMaster siteMaster){
		ClientSiteMaster newSite = siteService.addClientSiteMaster(siteMaster);
		return ResponseEntity.status(HttpStatus.CREATED).body(newSite);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<ClientSiteMaster>> getClientSiteMaster(@PathVariable Long id){
		Optional<ClientSiteMaster> site = siteService.getClientSiteMaster(id);
		return ResponseEntity.status(HttpStatus.OK).body(site);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<ClientSiteMaster>> getAllClientSiteMaster(){
		List<ClientSiteMaster> sites = siteService.getAllClientSite();
		return ResponseEntity.status(HttpStatus.OK).body(sites);
	}
}
