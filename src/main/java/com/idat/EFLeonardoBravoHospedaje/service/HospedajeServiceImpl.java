package com.idat.EFLeonardoBravoHospedaje.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idat.EFLeonardoBravoHospedaje.cliente.OpenFeignClient;
import com.idat.EFLeonardoBravoHospedaje.dto.HospedajeDTO;
import com.idat.EFLeonardoBravoHospedaje.dto.TuristaDTO;
import com.idat.EFLeonardoBravoHospedaje.modelo.Hospedaje;
import com.idat.EFLeonardoBravoHospedaje.modelo.HospedajeReservaDetalle;
import com.idat.EFLeonardoBravoHospedaje.modelo.HospedajeReservaFK;
import com.idat.EFLeonardoBravoHospedaje.repository.DetalleHospedajeReservaRepository;
import com.idat.EFLeonardoBravoHospedaje.repository.HospedajeRepository;


@Service
public class HospedajeServiceImpl implements HospedajeService {
	
	@Autowired
	private HospedajeRepository repository;
	
	@Autowired
	private DetalleHospedajeReservaRepository detallerepository;
	
	@Autowired
	private OpenFeignClient client;
	

	@Override
	public List<HospedajeDTO> listar() {
		List<HospedajeDTO> listadto = new ArrayList<>();
		HospedajeDTO dto = null;
                
        for (Hospedaje hospedaje : repository.findAll()) {
            dto = new HospedajeDTO();
            dto.setNom(hospedaje.getNombre());
            dto.setCod(hospedaje.getIdHospedaje());
            listadto.add(dto);
        }
        
		return listadto;
	}

	@Override
	public HospedajeDTO obtenerId(Integer id) {
		Hospedaje hospedaje = repository.findById(id).orElse(null);
		HospedajeDTO dto = new HospedajeDTO();
		 dto.setNom(hospedaje.getNombre());
         dto.setCod(hospedaje.getIdHospedaje());
		return dto;
	}

	@Override
	public void guardar(HospedajeDTO hospedaje) {
		Hospedaje hospe = new Hospedaje();
		hospe.setNombre(hospedaje.getNom());
		hospe.setIdHospedaje(hospedaje.getCod());
		repository.save(hospe);
		
	}
	
	@Override
	public void asignarTuristaHospedaje() {
		// TODO Auto-generated method stub
		List<TuristaDTO> listado = client.listarTurista();
		HospedajeReservaFK fk = null;
		HospedajeReservaDetalle detallehr = null;
		for (TuristaDTO turistaDTO : listado) {
			fk = new HospedajeReservaFK();
			fk.setIdHospedaje(turistaDTO.getCod());
			fk.setIdReserva(1);
			
			detallehr = new HospedajeReservaDetalle();
			detallehr.setFk(fk);
			detallerepository.save(detallehr);
		}
	}
}
