package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotasDTO;

public interface INotasDAO {

	int insertarNota(Integer idAlumno, String idAsignatura, String nota, String fecha);

	int borrarNota(String id);

	ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String asignatura, Float nota,
			String fecha, int activo);

	int actualizarNota(Integer idAlumno, String idAsignatura, String nota, String fecha);

}
