package com.adrian.colegio.servicio.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;

import com.adrian.colegio.dtos.AlumnoDTO;



public interface IAlumnosService {
	public ArrayList<AlumnoDTO> obtenerAlumnos() throws SQLException;
	
	public  ArrayList<AlumnoDTO> obtenerAlumnosPorIdNombreApellido(Integer id, String nombre,
			String apellido, int familiaNumerosa, int activo);
	
	public int insertarAlumno(int id, String nombre, String apellido, 
			int idMunicipio, int familiaNumerosa, int activo);
	
	public int actualizarAlumno(int id, String nombre, String apellido, 
			int idMunicipio, int familiaNumerosa, int activo);
	
	public int borrarAlumno(int id);
}

