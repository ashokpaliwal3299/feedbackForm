package com.feedback.form.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.form.model.FeedbackForm;


@Repository
public interface FeedbackFormRepository extends JpaRepository<FeedbackForm, Long>{

	Optional<FeedbackForm> findByIdAndIsDeletedFalse(Long id);

	List<FeedbackForm> findAllByIsDeletedFalse();

	Optional<FeedbackForm> findBySiteIdAndIsDeletedFalse(Long siteId);

	List<FeedbackForm> findAllBySiteIdAndYearAndIsDeletedFalse(Long siteId, int year);
	
	Optional<FeedbackForm> findBySiteIdAndMonthAndYearAndIsDeletedFalse(Long siteId, String month, int year);

}
