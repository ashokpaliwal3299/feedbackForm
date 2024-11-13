package com.feedback.form.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class feedbackFormDto {
	private long siteId;
	private String clientName;
	private String siteName;
	private String inchargeName;
	private double percentage;
}
