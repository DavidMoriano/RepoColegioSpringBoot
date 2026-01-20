package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.MatriculacionesDTO;
import com.adrian.colegio.entities.MatriculacionesEntity;

public interface MatriculacionesRepository extends CrudRepository<MatriculacionesEntity, Integer> {
	@Query("SELECT new com.adrian.colegio.dtos.MatriculacionesDTO "
			+ "(m.id, a.nombre, al.nombre, m.fecha, m.activo, a.tasa) " + "FROM MatriculacionesEntity m "
			+ "JOIN m.asignatura a " + "JOIN m.alumno al " + "WHERE "
			+ "(:nombreAsignatura IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAsignatura, '%'))) "
			+ "AND (:nombreAlumno IS NULL OR LOWER(al.nombre) LIKE LOWER(CONCAT('%', :nombreAlumno, '%'))) "
			+ "AND (:fecha IS NULL OR m.fecha >= :fecha) " + "AND (:activo = -1 OR m.activo = :activo) ")

	ArrayList<MatriculacionesDTO> buscarMatriculacionesPorFiltros(@Param("nombreAsignatura") String nombreAsignatura,
			@Param("nombreAlumno") String nombreAlumno, @Param("fecha") String fecha, @Param("activo") int activo);

	@Query("SELECT new com.adrian.colegio.dtos.MatriculacionesDTO "
			+ "(m.id, a.nombre, al.nombre, m.fecha, m.activo, a.tasa) " + "FROM MatriculacionesEntity m "
			+ "JOIN m.asignatura a " + "JOIN m.alumno al "
			+ "WHERE (:nombreAsignatura IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAsignatura, '%'))) "
			+ "AND (:nombreAlumno IS NULL OR LOWER(al.nombre) LIKE LOWER(CONCAT('%', :nombreAlumno, '%'))) "
			+ "AND (:activo = -1 OR m.activo = :activo) " + "ORDER BY m.fecha DESC")
	ArrayList<MatriculacionesDTO> buscarMatriculacionesPorFiltrosSinFecha(
			@Param("nombreAsignatura") String nombreAsignatura, @Param("nombreAlumno") String nombreAlumno,
			@Param("activo") int activo);

}