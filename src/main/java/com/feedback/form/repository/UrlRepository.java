package com.feedback.form.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.feedback.form.model.Url;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long>{

	Optional<Url> findById(Long id);
}
