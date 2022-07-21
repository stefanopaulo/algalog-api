package com.dev.algalog.api.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.algalog.domain.model.Cliente;
import com.dev.algalog.domain.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;

	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @RequestBody Cliente cliente) {
		if(existePorId(id)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(id);
		cliente = clienteRepository.save(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if(existePorId(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	private boolean existePorId(Long id) {
		return !clienteRepository.existsById(id);
	}
	
}
