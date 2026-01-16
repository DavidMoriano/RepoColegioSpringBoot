package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotasDTO;

public interface INotasDAO {

	int borrarNota(String id);

	ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String asignatura, Float nota,
			String fecha, int activo);

	int insertarNota(int idAlumno, int idAsignatura, String nota, String fecha);

	int actualizarNota(String id, int idAlumno, int idAsignatura, String nota, String fecha);

}
