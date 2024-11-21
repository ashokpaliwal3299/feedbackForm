package com.feedback.form.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feedback.form.model.ClientMaster;
import com.feedback.form.service.ClientMasterService;

@RestController
@RequestMapping("/client_master")
public class ClientMasterController {

	@Autowired
	private ClientMasterService clientMasterservice;
	
	@PostMapping("/new")
	public ResponseEntity<ClientMaster> addClient(@RequestBody ClientMaster client){
		ClientMaster newClient = clientMasterservice.addClientMaster(client);
		return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
	}
}
