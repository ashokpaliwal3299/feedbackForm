package com.feedback.form.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FeedbackForm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isDeleted = false;
	
	@ManyToOne
	@JoinColumn(name = "site_id")
	private ClientSiteMaster siteId; 
	
	private int personal1;
	private int personal2;
	private int personal3;
	private int personal4;
	private int personal5;
	private int personalOutOf = 5;
	
	private int cleaning1;
	private int cleaning2;
	private int cleaning3;
	private int cleaning4;
	private int cleaning5;
	private int cleaningOutOf = 5;
	
	private int supervision1;
	private int supervision2;
	private int supervision3;
	private int supervision4;
	private int supervision5;
	private int supervisionOutOf = 5;
	
	private int purchase1;
	private int purchase2;
	private int purchase3;
	private int purchase4;
	private int purchase5;
	private int purchaseOutOf = 5;
	
	private int controls1;
	private int controls2;
	private int controls3;
	private int controls4;
	private int controls5;
	private int controlsOutOf = 5;
	
	private int ho1;
	private int ho2;
	private int ho3;
	private int ho4;
	private int ho5;
	private int hoOutOf = 5;
	
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
		updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		updatedAt = LocalDateTime.now();
	}
	
}
