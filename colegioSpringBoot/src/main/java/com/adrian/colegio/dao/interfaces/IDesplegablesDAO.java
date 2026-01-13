package com.adrian.colegio.dao.interfaces;

import java.util.ArrayList;

import com.adrian.colegio.dtos.DesplegableDTO;

public interface IDesplegablesDAO {
	ArrayList<DesplegableDTO> desplegableMunicipios();

	ArrayList<DesplegableDTO> desplegableAlumnos();

	ArrayList<DesplegableDTO> desplegableAsignaturas();
}
