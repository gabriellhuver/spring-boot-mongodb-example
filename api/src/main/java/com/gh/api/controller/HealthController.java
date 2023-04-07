package com.gh.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gh.api.dto.HealthCheckDTO;

@RestController
@RequestMapping("/health")
public class HealthController {

	@GetMapping
	public ResponseEntity<HealthCheckDTO> health(){
		return ResponseEntity.ok(new HealthCheckDTO());
	}
	
}
