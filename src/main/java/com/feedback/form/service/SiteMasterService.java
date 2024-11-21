package com.feedback.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feedback.form.model.SiteMaster;
import com.feedback.form.repository.SiteMasterRepository;

@Service
public class SiteMasterService {

	@Autowired
	private SiteMasterRepository siteMasterRepo;
	
	public SiteMaster addSiteMaster(SiteMaster site) {
		return siteMasterRepo.save(site);
	}
	
	public List<SiteMaster> getAllSiteMaster(){
		return siteMasterRepo.findAllByIsDeletedFalse();
	}
}
