package com.gh.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gh.api.dto.NotificationRequestDTO;
import com.gh.api.dto.NotificationResponseDTO;
import com.gh.api.entity.Notification;
import com.gh.api.repository.NotificationRepository;
import com.google.common.base.Preconditions;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class NotificationService {

	@Autowired
	private NotificationRepository notificationRepository;
	
	@Autowired
	private NotificationMessagingService notificationMessageService;
	
	public NotificationResponseDTO notificar(NotificationRequestDTO notificationRequestDTO) {
		
		Preconditions.checkNotNull(notificationRequestDTO.getMsg());
		
		notificationMessageService.notificar(notificationRequestDTO);
		
		final Notification saved = notificationRepository.save(Notification.builder().msg(notificationRequestDTO.getMsg()).build());
		
		log.info("Notification Message send: {}", saved.toString());
		
		return NotificationResponseDTO.builder().id(saved.getId()).message(saved.getMsg()).build();
	}

}
