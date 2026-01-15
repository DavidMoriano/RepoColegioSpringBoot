package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.entities.NotasEntity;

public interface NotasRepository extends CrudRepository<NotasEntity, Integer> {
	@Query("""
			SELECT new com.adrian.colegio.dtos.NotasDTO(
			    n.id,
			    n.nota,
			    a.id,
			    a.nombre,
			    al.id,
			    al.nombre,
			    n.fecha
			)
			FROM NotasEntity n
			JOIN n.asignatura a
			JOIN n.alumno al
			WHERE (:id IS NULL OR n.id = :id)
			AND (:idAlumno IS NULL OR al.id = :idAlumno)
			AND (:idAsignatura IS NULL OR a.id = :idAsignatura)
			AND (:nota IS NULL OR n.nota LIKE CONCAT('%', :nota, '%'))
			AND (:nombreAlumno IS NULL OR LOWER(al.nombre) LIKE LOWER(CONCAT('%', :nombreAlumno, '%')))
			AND (:nombreAsignatura IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombreAsignatura, '%')))
			AND (:fecha IS NULL OR n.fecha >= :fecha)
			""")
	ArrayList<NotasDTO> buscarNotasPorFiltros(
			@Param("idAlumno") Integer idAlumno,
			@Param("nombreAlumno") String nombreAlumno, 
			@Param("nombreAsignatura") String nombreAsignatura,
			@Param("nota") Float nota,
			@Param("fecha") String fecha,
			@Param("activo") int activo);
}