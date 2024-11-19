package com.feedback.form.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
		
	private Long id;
	private String name;
	private String client_group_code;
	private String client_master_id;
	private String created_at;
	private String modified_at;
	private String email;
	
}
