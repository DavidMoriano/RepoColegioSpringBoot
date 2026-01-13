package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.entities.AsignaturaEntity;

public interface AsignaturaRepository extends CrudRepository<AsignaturaEntity, Integer> {

	@Query("SELECT new com.adrian.colegio.dtos.AsignaturaDTO " 
			+ "(a.id, a.nombre, a.curso, a.tasa, a.activo) "
			+ "FROM AsignaturaEntity a " 
			+ "WHERE (:id IS NULL OR CAST(a.id AS string) LIKE CONCAT('%', :id, '%')) "
			+ "AND (:nombre IS NULL OR a.nombre LIKE CONCAT('%', :nombre, '%')) "
			+ "AND (:curso IS NULL OR a.curso = :curso) " 
			+ "AND (:tasa IS NULL OR a.tasa = :tasa) "
			+ "AND (:activo IS NULL OR a.activo = :activo)")
	ArrayList<AsignaturaDTO> buscarAsignaturaPorIDNombreCursoTasa(
			@Param("id") Integer id, 
			@Param("nombre") String nombre,
			@Param("curso") Integer curso, 
			@Param("tasa") Float tasa, 
			@Param("activo") Integer activo);
}