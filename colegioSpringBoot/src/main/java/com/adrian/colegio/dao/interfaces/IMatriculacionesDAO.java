package com.adrian.colegio.dao.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.adrian.colegio.dtos.MatriculacionesDTO;

public interface IMatriculacionesDAO {
	double obtenerTasaAsignatura(String idAsignatura);

	int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, int activo) throws SQLException;

	ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo);

	ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura, String nombreAlumno,
			int activo);

	int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa)
			throws SQLException;

	int borrarMatriculacion(String id) throws SQLException;
}
