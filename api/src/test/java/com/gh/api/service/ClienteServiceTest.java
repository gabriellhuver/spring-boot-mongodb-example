package com.gh.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.gh.api.entity.Cliente;
import com.gh.api.helpers.ClientDummyBuilder;
import com.gh.api.repository.ClienteRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ClienteServiceTest {

	@InjectMocks
	ClienteService clienteService;

	@Mock
	ClienteRepository clienteRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}	

	@Test
	void testSalvar() {
		Cliente dummyUser = ClientDummyBuilder.buildDummyUser(666);

		when(clienteRepository.save(any())).thenReturn(dummyUser);

		Cliente saved = clienteService.salvar(dummyUser);

		assertEquals(dummyUser, saved);
		
		log.info("Test ClienteService - save - {}", saved);
	}
	
	@Test
	void testBuscarTodos() {
		List<Cliente> createListClientes = ClientDummyBuilder.createListClientes(10);
		
		when(clienteRepository.findAll()).thenReturn(createListClientes);

		List<Cliente> all = clienteService.buscarTodos();

		assertEquals(all.size(), 10);
		
		log.info("Test ClienteService - buscarTodos - {}", all);
	}
	
	@Test
	void testBuscarTodosPaginado() {
		List<Cliente> createListClientes = ClientDummyBuilder.createListClientes(5);
		
		PageRequest pageRequest = PageRequest.of(0, 5);
		
		when(clienteRepository.findAll(pageRequest)).thenReturn(new PageImpl<>(createListClientes));

		Page<Cliente> all = clienteService.buscarTodos(pageRequest);

		assertEquals(all.getContent().size(), 5);
		
		log.info("Test ClienteService - buscarTodos - {}", all);
	}
	
	@Test
	void testBuscarPorId() {
		Cliente dummyUser = ClientDummyBuilder.buildDummyUser(666);

		when(clienteRepository.findById(any())).thenReturn(Optional.of(dummyUser));

		Optional<Cliente> saved = clienteService.buscarPorId("666");

		assertEquals(dummyUser, saved.get());
		
		log.info("Test ClienteService - save - {}", saved);
	}
	
	@Test
	void testExcluirPorId() {
		doNothing().when(clienteRepository).deleteById("666");

		clienteService.excluir("666");
		
		log.info("Test ClienteService - save");
	}


}
