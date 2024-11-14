package com.feedback.form.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
	
	private String month;
	private int year;
	
	private Long siteId; 
	
	private int personal1;
	private int personalOutOf1;
	
	private int personal2;
	private int personalOutOf2;
	
	private int personal3;
	private int personalOutOf3;
	
	private int personal4;
	private int personalOutOf4;
	
	private int personal5;
	private int personalOutOf5;
	
	private int cleaning1;
	private int cleaningOutOf1;
	
	private int cleaning2;
	private int cleaningOutOf2;
	
	private int cleaning3;
	private int cleaningOutOf3;
	
	private int cleaning4;
	private int cleaningOutOf4;
	
	private int cleaning5;
	private int cleaningOutOf5;
	
	private int supervision1;
	private int supervisionOutOf1;
	
	private int supervision2;
	private int supervisionOutOf2;
	
	private int supervision3;
	private int supervisionOutOf3;
	
	private int supervision4;
	private int supervisionOutOf4;
	
	private int supervision5;
	private int supervisionOutOf5;
	
	private int purchase1;
	private int purchaseOutOf1;
	
	private int purchase2;
	private int purchaseOutOf2;
	
	private int purchase3;
	private int purchaseOutOf3;
	
	private int purchase4;
	private int purchaseOutOf4;
	
	private int purchase5;
	private int purchaseOutOf5;
	
	private int controls1;
	private int controlsOutOf1;
	
	private int controls2;
	private int controlsOutOf2;
	
	private int controls3;
	private int controlsOutOf3;
	
	private int controls4;
	private int controlsOutOf4;
	
	private int controls5;
	private int controlsOutOf5;
	
	private int ho1;
	private int hoOutOf1;
	
	private int ho2;
	private int hoOutOf2;
	
	private int ho3;
	private int hoOutOf3;
	
	private int ho4;
	private int hoOutOf4;
	
	private int ho5;
	private int hoOutOf5;
	
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
