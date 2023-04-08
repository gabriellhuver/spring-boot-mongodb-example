package com.gh.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gh.api.dto.NotificationRequestDTO;
import com.gh.api.dto.NotificationResponseDTO;
import com.gh.api.service.ClienteService;
import com.gh.api.service.NotificationService;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(controllers = NotificationController.class)
@Slf4j
class NotificationControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private NotificationService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void test() throws JsonProcessingException, Exception {

		when(service.notificar(any())).thenReturn(NotificationResponseDTO.builder().id("teste").message("teste").build());
	
		NotificationRequestDTO request = new NotificationRequestDTO();
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/notification")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(request)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		
		log.info("Test notification {}", result.getResponse().getContentAsString());
	}

}
