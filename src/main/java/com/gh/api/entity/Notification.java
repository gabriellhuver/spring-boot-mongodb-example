package com.gh.api.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Document(collection = "notification")
@Data
@SuperBuilder
@NoArgsConstructor
public class Notification {

	

	@Id
    private String id;
	
	private String msg;
	
    
}
