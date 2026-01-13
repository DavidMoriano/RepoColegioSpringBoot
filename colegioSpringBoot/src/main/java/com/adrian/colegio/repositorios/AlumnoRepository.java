package com.adrian.colegio.repositorios;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.entities.AlumnoEntity;

public interface AlumnoRepository extends CrudRepository<AlumnoEntity, Integer>{
	
	@Query("select new com.adrian.colegio.dtos.AlumnoDTO "
			+ "(a.id,a.nombre,a.apellidos,a.municipio.nombre,a.municipio.idMunicipio,a.famNumerosa,a.activo) "
			+ "FROM com.adrian.colegio.entities.AlumnoEntity a "
			+ "WHERE (:id IS NULL OR CAST(a.id AS string) LIKE CONCAT('%', :id, '%'))  "
			+ "AND a.nombre LIKE CONCAT ('%',:nombre,'%') "
			+ "AND a.apellidos LIKE CONCAT ('%',:apellidos,'%') "
			+ "AND a.activo = :activo "
			+ "AND a.famNumerosa = :famNumerosa")
	ArrayList<AlumnoDTO>buscaAlumnoporIDyNombre(@Param("id") Integer id,
			@Param("nombre") String nombre,
			@Param("apellidos") String apellidos,
			@Param("activo") Integer activo,
			@Param("famNumerosa") Integer familiaNumerosa);

}
