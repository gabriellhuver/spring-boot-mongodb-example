package com.gh.api.dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class HealthCheckDTO {

	private LocalDateTime now;

	public HealthCheckDTO() {
		super();
		this.now = LocalDateTime.now();
	}
	
	
	
}
