package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotasDTO;

public interface INotasService {
	public ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String asignatura,
			Float nota, String fecha, int activo);

	public int insertarNota(String idAlumno, String idAsignatura, String nota, String fecha);

	public int actualizarNota(String id, String idAlumno, String idAsignatura, String nota, String fecha);

	public int borrarNota(String id);

}
