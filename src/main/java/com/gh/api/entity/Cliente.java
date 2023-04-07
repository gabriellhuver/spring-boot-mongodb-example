package com.gh.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document(collection = "clientes")
@Data
@SuperBuilder
@NoArgsConstructor
public class Cliente {

	@Id
    private String id;
    private String nome;
    private String email;
    
}