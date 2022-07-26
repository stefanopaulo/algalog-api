package com.dev.algalog.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.dev.algalog.domain.model.StatusEntrega;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntregaModel {
	
	private Long id;
	private ClienteResumoModel cliente;
	private DestinatarioModel destinatario;
	private BigDecimal taxa;
	private StatusEntrega statusEntrega;
	private OffsetDateTime dataPedido;
	private OffsetDateTime dataFinalizacao;

}
