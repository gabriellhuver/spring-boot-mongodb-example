package com.gh.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gh.api.dto.NotificationRequestDTO;
import com.gh.api.dto.NotificationResponseDTO;
import com.gh.api.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

	@Autowired
	private NotificationService notificacionService;
	
	@PostMapping
	public ResponseEntity<NotificationResponseDTO> notificar(@RequestBody NotificationRequestDTO notificationRequestDTO) {
		return ResponseEntity.ok(notificacionService.notificar(notificationRequestDTO));
	}
	
	
	
	
}
