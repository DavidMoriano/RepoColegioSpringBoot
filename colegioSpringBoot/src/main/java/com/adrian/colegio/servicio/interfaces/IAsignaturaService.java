package com.adrian.colegio.servicio.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.AsignaturaDTO;

public interface IAsignaturaService {
	
	public ArrayList<AsignaturaDTO> obtenerAsignaturasPorIdNombreCursoTasaActivo(Integer id, String nombre,
			Integer curso, Float tasa, int activo);
	
	public int insertarAsignatura(int id, String nombre, int curso, float tasa, int activo);
	
	public int actualizarAsignatura(int id, String nombre, int curso, float tasa, int activo);
	
	public int borrarAsignatura(int id);
}
