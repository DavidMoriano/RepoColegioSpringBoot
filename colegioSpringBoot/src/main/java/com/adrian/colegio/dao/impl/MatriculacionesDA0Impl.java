package com.adrian.colegio.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.adrian.colegio.dao.interfaces.IMatriculacionesDAO;
import com.adrian.colegio.dtos.MatriculacionesDTO;
import com.adrian.colegio.entities.AlumnoEntity;
import com.adrian.colegio.entities.AsignaturaEntity;
import com.adrian.colegio.entities.CajaEntity;
import com.adrian.colegio.entities.MatriculacionesEntity;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.AsignaturaRepository;
import com.adrian.colegio.repositorios.CajasRepository;
import com.adrian.colegio.repositorios.MatriculacionesRepository;

import jakarta.transaction.Transactional;


@Repository
public class MatriculacionesDA0Impl implements IMatriculacionesDAO {

	@Autowired
	MatriculacionesRepository repoMatri;
	
	@Autowired
	AsignaturaRepository repoAsig;
	
	@Autowired
	AlumnoRepository repoAlum;
	
	@Autowired
	CajasRepository repoCaja;
	
	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		AsignaturaEntity asignatura = repoAsig.findById(Integer.parseInt(idAsignatura)).get();
		return asignatura.getTasa();
	}

	@Transactional
	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, int activo)
			throws SQLException {
		AlumnoEntity alumno = repoAlum.findById(Integer.parseInt(idAlumno)).get();
		AsignaturaEntity asignatura = repoAsig.findById(Integer.parseInt(idAsignatura)).get();
		
		MatriculacionesEntity nuevaMatriculacion = new MatriculacionesEntity(asignatura, alumno, fecha, activo);
		
		repoMatri.save(nuevaMatriculacion);
		
		CajaEntity caja = new CajaEntity(nuevaMatriculacion, obtenerTasaAsignatura(idAsignatura));
		repoCaja.save(caja);
		
		return nuevaMatriculacion.getId();
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa)
			throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int borrarMatriculacion(String id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
