package com.dev.algalog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.algalog.domain.model.Cliente;
import com.dev.algalog.domain.model.Entrega;
import com.dev.algalog.domain.model.StatusEntrega;
import com.dev.algalog.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EntregaService {
	
	private EntregaRepository entregaRepository;
	private ClienteService clienteService;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		
		return entregaRepository.save(entrega);
	}

}
