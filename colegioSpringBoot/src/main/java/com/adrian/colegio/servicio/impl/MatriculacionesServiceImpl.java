package com.adrian.colegio.servicio.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adrian.colegio.dao.interfaces.IMatriculacionesDAO;
import com.adrian.colegio.dtos.MatriculacionesDTO;
import com.adrian.colegio.servicio.interfaces.IMatriculacionesService;

@Service
public class MatriculacionesServiceImpl implements IMatriculacionesService {
	@Autowired
	IMatriculacionesDAO matriculacionesDAO;

	@Override
	public double calcularTasa(String idAlumno, String idAsignatura) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, String tasa) {
		try {
			return matriculacionesDAO.insertarMatriculacion(idAsignatura, idAlumno, fecha, Integer.parseInt(tasa));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {
		return matriculacionesDAO.obtenerMatriculacionesPorFiltros(nombreAsignatura, nombreAlumno, fecha, activo);
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {
		return matriculacionesDAO.obtenerMatriculacionesPorFiltrosSinFecha(nombreAsignatura, nombreAlumno, activo);
	}

	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa) {

		try {
			return matriculacionesDAO.actualizarMatriculacion(id, idAsignatura, idAlumno, fecha, tasa);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int borrarMatriculacion(String id) {
		try {
			return matriculacionesDAO.borrarMatriculacion(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
