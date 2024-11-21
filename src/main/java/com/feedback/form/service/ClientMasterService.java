package com.feedback.form.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.form.model.ClientMaster;
import com.feedback.form.repository.ClientMasterRepository;

@Service
public class ClientMasterService {

	@Autowired
	private ClientMasterRepository clientMasterRepo;
	
	public ClientMaster addClientMaster(ClientMaster client) {
		return clientMasterRepo.save(client);
	}
}
