package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.MatriculacionesDTO;
import com.adrian.colegio.entities.MatriculacionesEntity;

public interface MatriculacionesRepository extends CrudRepository<MatriculacionesEntity, Integer> {

	@Query("""
			SELECT new com.adrian.colegio.dtos.MatriculacionesDTO(
			    m.id,
			    a.id,
			    a.nombre,
			    al.id,
			    al.nombre,
			    m.fecha,
			    m.activo,
			    a.tasa
			)
			FROM MatriculacionesEntity m
			JOIN m.asignatura a
			JOIN m.alumno al
			WHERE (:id IS NULL OR m.id = :id)
			  AND (:idAlumno IS NULL OR al.id = :idAlumno)
			  AND (:idAsignatura IS NULL OR a.id = :idAsignatura)
			  AND (:nombreAlumno IS NULL OR LOWER(al.nombre) LIKE LOWER(CONCAT('%', :nombreAlumno, '%')))
			  AND (:nombreAsignatura IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAsignatura, '%')))
			  AND (:fecha IS NULL OR m.fecha = :fecha)
			  AND (:activo IS NULL OR m.activo = :activo)
			  AND (:tasa IS NULL OR a.tasa = :tasa)
			""")
	ArrayList<MatriculacionesDTO> buscarMatriculacionesAvanzado(
			@Param("id") Integer id, 
			@Param("idAlumno") Integer idAlumno,
			@Param("idAsignatura") Integer idAsignatura, 
			@Param("nombreAlumno") String nombreAlumno,
			@Param("nombreAsignatura") String nombreAsignatura,
			@Param("fecha") String fecha,
			@Param("activo") Integer activo, 
			@Param("tasa") Double tasa);

}