package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.AsignaturaDTO;

public interface IAsignaturasDAO {
	ArrayList<AsignaturaDTO> obtenerTodasAsignaturas();
	
	int insertarAsignatura(int id, String nombre, int curso, float tasa, int activo);
	int actualizarAsignatura(int id, String nombre, int curso, float tasa, int activo);
	int borrarAsignatura(int id);

	ArrayList<AsignaturaDTO> obtenerAsignaturaPorIdNombreCursoTasaActivo(Integer id, String nombre, Integer curso,
			Float tasa, int activo);
	
}
