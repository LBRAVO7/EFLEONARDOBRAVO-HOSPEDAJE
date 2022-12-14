package com.idat.EFLeonardoBravoHospedaje.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Embeddable
public class HospedajeReservaFK implements Serializable {

	private static final long serialVersionUID = -5209011976958399186L;
	

	@Column(name = "id_hospedaje", nullable = false)
	private Integer idHospedaje;
	
	@Column(name = "id_reserva", nullable = false)
	private Integer idReserva;
	

}
