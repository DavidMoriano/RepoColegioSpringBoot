package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.MatriculacionesDTO;

public interface IMatriculacionesService {
	public double calcularTasa(String idAlumno, String idAsignatura);

	int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, String tasa);

	ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo);

	ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura, String nombreAlumno,
			int activo);

	int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa);

	int borrarMatriculacion(String id);
}
