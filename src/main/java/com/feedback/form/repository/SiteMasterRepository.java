package com.feedback.form.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.form.model.SiteMaster;

@Repository
public interface SiteMasterRepository extends JpaRepository<SiteMaster, Long>{

	List<SiteMaster> findAllByIsDeletedFalse();

	Optional<SiteMaster> findByIdAndIsDeletedFalse(Long siteId);

}
