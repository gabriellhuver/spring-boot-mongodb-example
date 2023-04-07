package com.gh.api.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.gh.api.entity.Cliente;

public class ClientDummyBuilder {

	

	public static List<Cliente> createListClientes(int num) {
		List<Cliente> clientes = new ArrayList<>();
		IntStream.range(0, num).forEach(i -> {
			clientes.add(buildDummyUser(i));
		});
		return clientes;
	}

	public static Cliente buildDummyUser(int id) {
		return Cliente.builder().id(String.valueOf(id)).email(id + "-teste@example.com").nome(id + "-teste teste")
				.build();
	}
	
}
