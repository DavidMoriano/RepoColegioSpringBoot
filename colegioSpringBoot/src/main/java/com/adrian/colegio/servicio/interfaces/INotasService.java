package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.NotasDTO;

public interface INotasService {

	public int borrarNota(String id);

	public int actualizarNota(String id, int idAlumno, int idAsignatura, String nota, String fecha);

	int insertarNota(int idAlumno, int idAsignatura, String nota, String fecha);

	public ArrayList<NotasDTO> obtenerNotasPorFiltros(Integer idAlumno, String nombreAlumno, String nombreAsignatura,
			Float nota, String fecha, int activo);

}
