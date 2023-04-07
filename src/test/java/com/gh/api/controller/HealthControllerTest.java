package com.gh.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import lombok.extern.slf4j.Slf4j;

@WebMvcTest(controllers = HealthController.class)
@Slf4j
class HealthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void test() throws Exception {
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/health"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		log.info("HealthCheck teste ok: {}", result.getResponse().getContentAsString());
	}

}