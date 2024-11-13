package com.feedback.form.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.form.model.ClientSiteMaster;

@Repository
public interface ClientSiteMasterRepository extends JpaRepository<ClientSiteMaster, Long>{

	Optional<ClientSiteMaster> findByIdAndIsDeletedFalse(Long id);
	
	List<ClientSiteMaster> findAllByIsDeletedFalse();

	Optional<ClientSiteMaster> findBySiteNameAndIsDeletedFalse(String siteName);

}
