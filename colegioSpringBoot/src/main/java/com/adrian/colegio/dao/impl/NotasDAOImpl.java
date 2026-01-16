package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.INotasDAO;
import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.NotasEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.NotasRepository;

@Repository
public class NotasDAOImpl implements INotasDAO {
	@Autowired
	NotasRepository notasRepository;
	@Autowired
	AsignaturaRepository asignaturaRepo;
	
	@Autowired
	AlumnoRepository alumnoRepo;
	
	
	@Override
	public ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String asignatura,
			Float nota, String fecha, int activo) {
		return notasRepository.buscarNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota, fecha, activo);
	}

	@Override
	public int insertarNota(int idAlumno, int idAsignatura, String nota, String fecha) {
		AsignaturaEntity asignatura = asignaturaRepo.findById(idAsignatura).get();
		AlumnoEntity alumno = alumnoRepo.findById(idAlumno).get();
		
		NotasEntity nuevaNota = new NotasEntity(alumno, asignatura, nota, fecha);
		notasRepository.save(nuevaNota);
		return nuevaNota.getId();
	}

	@Override
	public int actualizarNota(String id, int idAlumno, int idAsignatura, String nota, String fecha) {
		AsignaturaEntity asignatura = asignaturaRepo.findById(idAsignatura).get();
		AlumnoEntity alumno = alumnoRepo.findById(idAlumno).get();
	
		NotasEntity nuevaNota = new NotasEntity(Integer.parseInt(id), alumno, asignatura, nota, fecha);
		notasRepository.save(nuevaNota);
		return nuevaNota.getId();
	}

	@Override
	public int borrarNota(String id) {
		NotasEntity nota = notasRepository.findById(Integer.parseInt(id)).get();
		notasRepository.delete(nota);
		return nota.getId();
	}

}
