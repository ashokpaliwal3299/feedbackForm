package com.feedback.form.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.form.model.ClientMaster;

@Repository
public interface ClientMasterRepository extends JpaRepository<ClientMaster, Long>{

}
