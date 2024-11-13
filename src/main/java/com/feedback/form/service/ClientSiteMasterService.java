package com.feedback.form.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.form.model.ClientSiteMaster;
import com.feedback.form.repository.ClientSiteMasterRepository;

@Service
public class ClientSiteMasterService {
	
	@Autowired
	private EmailService emailService;

	@Autowired
	private ClientSiteMasterRepository clientSiteRepo;
	
	public ClientSiteMaster addClientSiteMaster(ClientSiteMaster site) {
		
		return clientSiteRepo.save(site);
	}

	public Optional<ClientSiteMaster> getClientSiteMaster(Long id) {
		return clientSiteRepo.findByIdAndIsDeletedFalse(id);
	}
	
	public List<ClientSiteMaster> getAllClientSite(){
		return clientSiteRepo.findAllByIsDeletedFalse();
	}
}
