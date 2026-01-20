package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.MunicipioEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.MunicipioRepository;

@Repository
public class DesplegablesDAOImpl implements IDesplegablesDAO {

	@Autowired
	private MunicipioRepository municipioRepository;

	@Autowired
	private AlumnoRepository alumnosRepo;

	@Autowired
	private AsignaturaRepository asigRepo;

	private ArrayList<DesplegableDTO> mapeoEntidadMunicioComboDTO(Iterable<MunicipioEntity> listaEntidadesMunicipios) {
		ArrayList<DesplegableDTO> listaCombos = new ArrayList<>();
		for (MunicipioEntity municipiosEntity : listaEntidadesMunicipios) {
			listaCombos.add(new DesplegableDTO(municipiosEntity.getIdMunicipio(), municipiosEntity.getNombre()));
		}
		return listaCombos;

	}

	private ArrayList<DesplegableDTO> mapeoEntidadAlumnoComboDTO(Iterable<AlumnoEntity> listaEntidadesAlumno) {
		ArrayList<DesplegableDTO> listaCombos = new ArrayList<>();

		for (AlumnoEntity alumnosEntity : listaEntidadesAlumno) {
			listaCombos.add(new DesplegableDTO(alumnosEntity.getId(), alumnosEntity.getNombre()));
		}
		return listaCombos;

	}

	private ArrayList<DesplegableDTO> mapeoEntidadAsignaturaComboDTO(
			Iterable<AsignaturaEntity> listaEntidadesAsignatura) {
		ArrayList<DesplegableDTO> listaCombos = new ArrayList<>();

		for (AsignaturaEntity asignaturaEntity : listaEntidadesAsignatura) {
			listaCombos.add(new DesplegableDTO(asignaturaEntity.getId(), asignaturaEntity.getNombre()));
		}
		return listaCombos;
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableMunicipios() {
		Iterable<MunicipioEntity> listaEntidadesMunicipios = municipioRepository.findAll();
		ArrayList<DesplegableDTO> listaMunicipios = mapeoEntidadMunicioComboDTO(listaEntidadesMunicipios);
		return listaMunicipios;
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableAlumnos() {
		Iterable<AlumnoEntity> listaEntidadesAlumnos = alumnosRepo.findAll();
		ArrayList<DesplegableDTO> listaAlumnos = mapeoEntidadAlumnoComboDTO(listaEntidadesAlumnos);
		return listaAlumnos;
	}

	@Override
	public ArrayList<DesplegableDTO> desplegableAsignaturas() {
		Iterable<AsignaturaEntity> listaEntidadesAsignaturas = asigRepo.findAll();
		ArrayList<DesplegableDTO> listaAsignaturas = mapeoEntidadAsignaturaComboDTO(listaEntidadesAsignaturas);
		return listaAsignaturas;
	}

}
