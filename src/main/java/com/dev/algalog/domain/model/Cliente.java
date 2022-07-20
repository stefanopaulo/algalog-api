package com.dev.algalog.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Cliente {

	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
}
