package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.entities.MunicipioEntity;
import com.adrian.colegio.repositorios.MunicipioRepository;

@Repository
public class DesplegablesDAOImpl implements IDesplegablesDAO {

	@Autowired // Inyectamos el repository
	private MunicipioRepository municipioRepository;

	@Override
	public ArrayList<DesplegableDTO> desplegableMunicipios() {
		// Utilizamos el método que nos "regala" Spring data JPA
		Iterable<MunicipioEntity> listaEntidadesMunicipios = municipioRepository.findAll();
		ArrayList<DesplegableDTO> listaMunicipios = mapeoEntidadMunicioComboDTO(listaEntidadesMunicipios);
		return listaMunicipios;
	}

	private ArrayList<DesplegableDTO> mapeoEntidadMunicioComboDTO(Iterable<MunicipioEntity> listaEntidadesMunicipios) {
		ArrayList<DesplegableDTO> listaCombos = new ArrayList<>();
		for (MunicipioEntity municipiosEntity : listaEntidadesMunicipios) {
			listaCombos.add(new DesplegableDTO(municipiosEntity.getIdMunicipio(), municipiosEntity.getNombre()));
		}
		return listaCombos;

	}

	@Override
	public ArrayList<DesplegableDTO> desplegableAlumnos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableAsignaturas() {
		// TODO Auto-generated method stub
		return null;
	}

}
