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
public class SiteMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private boolean isDeleted = false;
	
	@ManyToOne
	@JoinColumn(name = "client_master_id")
	private ClientMaster clientMaster;
	
	private String location_name;
	private String location_code;
	private String client_group_code;
	private String branch_code;
	private String siteInchargeEmail;
	private String siteInchargeName;
	
	private LocalDateTime created_at;
	private LocalDateTime modified_at;
	
	@PrePersist
	protected void onCreate() {
		created_at = LocalDateTime.now();
		modified_at = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		modified_at = LocalDateTime.now();
	}
}
