package com.gh.api.helpers;

import com.gh.api.dto.NotificationRequestDTO;

public class NotificationDummyBuilder {

	public static NotificationRequestDTO createNotification() {
		return NotificationRequestDTO.builder().msg("teste").build();
	}

}
