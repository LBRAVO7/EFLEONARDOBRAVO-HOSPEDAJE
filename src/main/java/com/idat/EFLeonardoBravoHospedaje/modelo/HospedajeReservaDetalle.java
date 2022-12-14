package com.idat.EFLeonardoBravoHospedaje.modelo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Table(name = "detalle")
public class HospedajeReservaDetalle {
	
	@Id
	private HospedajeReservaFK fk= new HospedajeReservaFK();


}
