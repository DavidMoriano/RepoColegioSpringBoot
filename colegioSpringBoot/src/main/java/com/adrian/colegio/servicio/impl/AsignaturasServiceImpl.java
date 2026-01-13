package com.adrian.colegio.servicio.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IAsignaturasDAO;
import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.servicio.interfaces.IAsignaturaService;

@Service
public class AsignaturasServiceImpl implements IAsignaturaService {
	@Autowired
	IAsignaturasDAO asignaturasDAO;

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturas() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<AsignaturaDTO> obtenerAsignaturasPorIdNombreCursoTasaActivo(Integer id, String nombre,
			Integer curso, Float tasa, int activo) {
		return asignaturasDAO.obtenerAsignaturaPorIdNombreCursoTasaActivo(id, nombre, curso, tasa, activo);
	}

	@Override
	public int insertarAsignatura(int id, String nombre, int curso, float tasa, int activo) {
		return asignaturasDAO.insertarAsignatura(id, nombre, curso, tasa, activo);
	}

	@Override
	public int actualizarAsignatura(int id, String nombre, int curso, float tasa, int activo) {
		return asignaturasDAO.actualizarAsignatura(id, nombre, curso, tasa, activo);
	}

	@Override
	public int borrarAsignatura(int id) {
		return asignaturasDAO.borrarAsignatura(id);
	}

}
