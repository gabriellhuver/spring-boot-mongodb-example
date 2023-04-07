package com.gh.api.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gh.api.entity.Cliente;
import com.gh.api.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }
    
    public List<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }
    
    public Optional<Cliente> buscarPorId(String id) {
        return clienteRepository.findById(id);
    }
    
    public void excluir(String id) {
        clienteRepository.deleteById(id);
    }

	public Page<Cliente> buscarTodos(Pageable pageable) {
		return clienteRepository.findAll(pageable);
	}
    
    
}




