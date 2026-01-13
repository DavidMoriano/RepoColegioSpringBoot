package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.IAsignaturasDAO;
import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.repositorios.AsignaturaRepository;

@Repository
public class AsignaturasDAOImpl implements IAsignaturasDAO {
	@Autowired
	AsignaturaRepository asignaturaRepository;

	public ArrayList<AsignaturaDTO> obtenerTodasAsignaturas() {
		return null;
	}

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturaPorIdNombreCursoTasaActivo(Integer id, String nombre, Integer curso,
			Float tasa, int activo) {
		return asignaturaRepository.buscarAsignaturaPorIDNombreCursoTasa(id, nombre, curso, tasa, activo);
	}

	@Override
	public int insertarAsignatura(int id, String nombre, int curso, float tasa, int activo) {
		AsignaturaEntity asignatura = new AsignaturaEntity(id, nombre, curso, tasa, activo);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

	@Override
	public int actualizarAsignatura(int id, String nombre, int curso, float tasa, int activo) {
		AsignaturaEntity asignatura = new AsignaturaEntity(id, nombre, curso, tasa, activo);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

	@Override
	public int borrarAsignatura(int id) {
		AsignaturaEntity asignatura = asignaturaRepository.findById(id).get();
		asignatura.setActivo(0);
		asignaturaRepository.save(asignatura);
		return asignatura.getId();
	}

}
