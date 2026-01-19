package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotasDTO;

public interface INotasDAO {

	int borrarNota(String id);

	int insertarNota(int idAlumno, int idAsignatura, String nota, String fecha);

	int actualizarNota(String id, int idAlumno, int idAsignatura, String nota, String fecha);

	ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
			Float nota, String fecha, int activo);

}
