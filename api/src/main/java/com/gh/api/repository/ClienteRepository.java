package com.gh.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.gh.api.entity.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {
    
	Page<Cliente> findAll(Pageable pageable);
	
}