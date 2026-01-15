package com.adrian.colegio.dao.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adrian.colegio.dao.interfaces.INotasDAO;
import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.entities.NotasEntity;
import com.adrian.colegio.repositorios.NotasRepository;

@Repository
public class NotasDAOImpl implements INotasDAO {
	@Autowired
	NotasRepository notasRepository;
	
	@Override
	public ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String asignatura,
			Float nota, String fecha, int activo) {
		return notasRepository.buscarNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota, fecha, activo);
	}

	@Override
	public int insertarNota(Integer idAlumno, String idAsignatura, String nota, String fecha) {
		AsignaturaEntity recogerAsignatura = asignatur
		NotasEntity nuevaNota = new NotasEntity(idAlumno, idAsignatura, nota, fecha);
		notasRepository.save(nuevaNota);
		return nuevaNota.getId();
	}

	@Override
	public int actualizarNota(Integer idAlumno, String idAsignatura, String nota, String fecha) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int borrarNota(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
