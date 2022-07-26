package com.dev.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.algalog.api.assembler.ClienteAssembler;
import com.dev.algalog.api.model.ClienteModel;
import com.dev.algalog.api.model.input.ClienteInput;
import com.dev.algalog.domain.model.Cliente;
import com.dev.algalog.domain.repository.ClienteRepository;
import com.dev.algalog.domain.service.ClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	private ClienteService clienteService;
	private ClienteAssembler clienteAssembler;

	@GetMapping
	public List<ClienteModel> listar() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteModel> buscarPorId(@PathVariable Long id) {
		return clienteRepository.findById(id)
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/salvar")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel salvar(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		
		return clienteAssembler.toModel(clienteService.salvar(cliente));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long id, @Valid @RequestBody ClienteInput clienteInput) {
		if(existePorId(id)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente.setId(id);
		
		Cliente clienteSalvo = clienteService.salvar(cliente);
		
		return ResponseEntity.ok(clienteAssembler.toModel(clienteSalvo));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		if(existePorId(id)) {
			return ResponseEntity.notFound().build();
		}
		
		clienteService.excluir(id);
		
		return ResponseEntity.noContent().build();
	}

	private boolean existePorId(Long id) {
		return !clienteRepository.existsById(id);
	}
	
}
