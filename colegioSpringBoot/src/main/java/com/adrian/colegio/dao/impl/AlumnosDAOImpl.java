package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IAlumnosDAO;
import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.MunicipioEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.MunicipioRepository;

@Repository
public class AlumnosDAOImpl implements IAlumnosDAO {
	@Autowired
	MunicipioRepository municipioRepository;

	@Autowired
	AlumnoRepository alumnosRepository;

	@Override
	public int insertarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa,
			int activo) {
		MunicipioEntity municipio = municipioRepository.findById(idMunicipio).get();

		AlumnoEntity alumno = new AlumnoEntity(id, nombre, apellido, familiaNumerosa, activo, municipio);

		alumnosRepository.save(alumno);
		return alumno.getId();
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerTodosAlumnos() {
		return null;
	}

	@Override
	public ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(Integer id, String nombre, String apellido,
			int familiaNumerosa, int activo) {
		return alumnosRepository.buscaAlumnoporIDyNombre(id, nombre, apellido, familiaNumerosa, activo);
	}

	@Override
	public int actualizarAlumno(int id, String nombre, String apellido, int idMunicipio, int familiaNumerosa,
			int activo) {
		MunicipioEntity municipio = municipioRepository.findById(idMunicipio).get();
		AlumnoEntity alumno = new AlumnoEntity(id, nombre, apellido, familiaNumerosa, activo, municipio);

		alumnosRepository.save(alumno);
		return alumno.getId();
	}

	@Override
	public int borrarAlumno(int id) {
		AlumnoEntity alumno = alumnosRepository.findById(id).get();
		alumno.setActivo(0);
		alumnosRepository.save(alumno);
		return alumno.getId();
	}

	@Override
	public boolean esFamiliaNumerosa(String idAlumno) {
		return false;
	}

	@Override
	public int contarAsignaturasMatriculadas(String idAlumno) {
		return 0;
	}

}
