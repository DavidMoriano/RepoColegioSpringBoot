package com.adrian.colegio.servicio.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.INotasDAO;
import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;


@Service
public class NotasServiceImpl implements INotasService {
	@Autowired
	INotasDAO notasDAO;
	
	@Override
	public int insertarNota(int idAlumno, int idAsignatura, String nota, String fecha) {
		return notasDAO.insertarNota(idAlumno, idAsignatura, nota, fecha);
	}

	@Override
	public int actualizarNota(String id, int idAlumno, int idAsignatura, String nota, String fecha) {
		return notasDAO.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);
	}

	@Override
	public int borrarNota(String id) {
		return notasDAO.borrarNota(id);
	}

	@Override
	public ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
			Float nota, String fecha, int activo) {
		return notasDAO.obtenerNotasPorFiltros(idAlumno, nombreAlumno, nombreAsignatura, nota, fecha, activo);
	}

}
