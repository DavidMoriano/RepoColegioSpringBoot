package com.adrian.colegio.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.adrian.colegio.entities.CajaEntity;

public interface CajasRepository  extends CrudRepository<CajaEntity, Integer>  {
	
}
