package com.gh.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gh.api.entity.Cliente;
import com.gh.api.helpers.ClientDummyBuilder;
import com.gh.api.service.ClienteService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebMvcTest(controllers = ClienteController.class)
class ClienteControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ClienteService clienteService;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void testBuscarTodos() throws Exception {

		List<Cliente> clientes = ClientDummyBuilder.createListClientes(10);
		Pageable pageable = PageRequest.of(0, 10);

		PageImpl<Cliente> page = new PageImpl<>(clientes, pageable, clientes.size());

		when(clienteService.buscarTodos(any(Pageable.class))).thenReturn(page);

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clientes"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		log.info("Test buscar clientes - {}", result.getResponse().getContentAsString());
	}

	@Test
	void testCriarCliente() throws Exception {
		Cliente cliente = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.salvar(any())).thenReturn(cliente);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.post("/clientes").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(MockMvcResultMatchers.status().isCreated()).andReturn();

		log.info("Teste criar cliente - {}", result.getResponse().getContentAsString());
	}

	@Test
	void testBuscarPorId() throws Exception {
		Cliente buildDummyUser = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.buscarPorId(any())).thenReturn(Optional.of(buildDummyUser));

		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/clientes/1"))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		log.info("Teste busca por id - {}", result.getResponse().getContentAsString());
	}

	@Test
	void testAlterarPorId() throws Exception {
		Cliente cliente = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.buscarPorId(any())).thenReturn(Optional.of(cliente));

		when(clienteService.salvar(any())).thenReturn(cliente);

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/clientes/666").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

		log.info("Teste alterar cliente por id - {}", result.getResponse().getContentAsString());
	}

	@Test
	void testAlterarPorIdEntityNotFound() throws Exception {
		Cliente cliente = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.buscarPorId(any())).thenReturn(Optional.empty());

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.put("/clientes/666").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		log.info("Teste alterar cliente inexistente - {}", result.getResponse().getStatus());
	}
	
	@Test
	void testDeletarPorId() throws Exception {
		Cliente cliente = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.buscarPorId(any())).thenReturn(Optional.of(cliente));

		doNothing().when(clienteService).excluir(any());

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/clientes/666").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(MockMvcResultMatchers.status().is2xxSuccessful()).andReturn();

		log.info("Teste deletar cliente por ID - {}", result.getResponse().getStatus());
	}
	
	@Test
	void testDeletarPorIdEntityNotPresent() throws Exception {
		Cliente cliente = ClientDummyBuilder.buildDummyUser(666);

		when(clienteService.buscarPorId(any())).thenReturn(Optional.empty());

		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.delete("/clientes/666").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(cliente)))
				.andExpect(MockMvcResultMatchers.status().isNotFound()).andReturn();

		log.info("Teste deletar por id cliente not found - {}", result.getResponse().getStatus());
	}
	
	

}
