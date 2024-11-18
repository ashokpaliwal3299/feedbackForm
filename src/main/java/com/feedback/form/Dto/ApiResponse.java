package com.feedback.form.Dto;

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
	private Long client_master_id;
	private String email;
	
}
