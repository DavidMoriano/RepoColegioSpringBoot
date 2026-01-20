package com.adrian.colegio.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.adrian.colegio.entities.CajaEntity;
import com.adrian.colegio.entities.MatriculacionesEntity;

public interface CajasRepository extends CrudRepository<CajaEntity, Integer> {

	@Query("SELECT c FROM CajaEntity c WHERE c.matricula = :matricula")
	CajaEntity findByMatricula(@Param("matricula") MatriculacionesEntity matriculacion);

}
