package com.dev.algalog.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.algalog.domain.model.Cliente;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@GetMapping
	public List<Cliente> listar() {
		Cliente cliente1 = new Cliente(1L, "João", "joaodascouves@gmail.com", "34 99999-1111");
		Cliente cliente2 = new Cliente(2L, "Maria", "mariadasneves@gmail.com", "11 97777-4343");
		
		return Arrays.asList(cliente1, cliente2);
	}
	
}
