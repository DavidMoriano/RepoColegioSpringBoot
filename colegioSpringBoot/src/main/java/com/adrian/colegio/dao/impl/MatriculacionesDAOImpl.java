package com.adrian.colegio.dao.impl;

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
public class MatriculacionesDAOImpl implements IMatriculacionesDAO {

	@Autowired
	private MatriculacionesRepository repoMatri;

	@Autowired
	private AsignaturaRepository repoAsig;

	@Autowired
	private AlumnoRepository repoAlum;

	@Autowired
	private CajasRepository repoCaja;

	@Override
	public double obtenerTasaAsignatura(String idAsignatura) {
		return 0;
	}

	@Transactional
	@Override
	public int insertarMatriculacion(String idAsignatura, String idAlumno, String fecha, int activo) {
		if (idAsignatura == null || idAlumno == null || fecha == null) {
			throw new IllegalArgumentException("Parámetros obligatorios no pueden ser null");
		}

		int idAsig = Integer.parseInt(idAsignatura);
		int idAlum = Integer.parseInt(idAlumno);

		AsignaturaEntity asignatura = repoAsig.findById(idAsig)
				.orElseThrow(() -> new IllegalArgumentException("Asignatura no encontrada: " + idAsignatura));

		AlumnoEntity alumno = repoAlum.findById(idAlum)
				.orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado: " + idAlumno));

		MatriculacionesEntity nuevaMatriculacion = new MatriculacionesEntity(asignatura, alumno, fecha, activo);
		repoMatri.save(nuevaMatriculacion);

		// Registrar en caja
		double tasa = asignatura.getTasa();
		CajaEntity caja = new CajaEntity(nuevaMatriculacion, tasa);
		repoCaja.save(caja);

		return nuevaMatriculacion.getId();
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltros(String nombreAsignatura, String nombreAlumno,
			String fecha, int activo) {

		// Puedes normalizar los strings aquí si quieres (trim, toLowerCase parcial,
		// etc.)
		return repoMatri.buscarMatriculacionesPorFiltros(
				nombreAsignatura != null && !nombreAsignatura.trim().isEmpty() ? nombreAsignatura.trim() : null,
				nombreAlumno != null && !nombreAlumno.trim().isEmpty() ? nombreAlumno.trim() : null,
				fecha != null && !fecha.trim().isEmpty() ? fecha.trim() : null, activo);
	}

	@Override
	public ArrayList<MatriculacionesDTO> obtenerMatriculacionesPorFiltrosSinFecha(String nombreAsignatura,
			String nombreAlumno, int activo) {

		return repoMatri.buscarMatriculacionesPorFiltrosSinFecha(
				nombreAsignatura != null && !nombreAsignatura.trim().isEmpty() ? nombreAsignatura.trim() : null,
				nombreAlumno != null && !nombreAlumno.trim().isEmpty() ? nombreAlumno.trim() : null, activo);
	}

	@Transactional
	@Override
	public int actualizarMatriculacion(String id, String idAsignatura, String idAlumno, String fecha, String tasa) {

		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("ID de matrícula es obligatorio");
		}

		int idMatricula = Integer.parseInt(id);
		MatriculacionesEntity matriculacion = repoMatri.findById(idMatricula)
				.orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada: " + id));

		boolean modificado = false;

		// Actualizar asignatura si se proporciona
		if (idAsignatura != null && !idAsignatura.trim().isEmpty()) {
			int idAsig = Integer.parseInt(idAsignatura);
			AsignaturaEntity asignatura = repoAsig.findById(idAsig)
					.orElseThrow(() -> new IllegalArgumentException("Asignatura no encontrada"));
			matriculacion.setAsignatura(asignatura);
			modificado = true;
		}

		// Actualizar alumno si se proporciona
		if (idAlumno != null && !idAlumno.trim().isEmpty()) {
			int idAlum = Integer.parseInt(idAlumno);
			AlumnoEntity alumno = repoAlum.findById(idAlum)
					.orElseThrow(() -> new IllegalArgumentException("Alumno no encontrado"));
			matriculacion.setAlumno(alumno);
			modificado = true;
		}

		// Actualizar fecha si se proporciona
		if (fecha != null && !fecha.trim().isEmpty()) {
			matriculacion.setFecha(fecha);
			modificado = true;
		}

		if (modificado) {
			repoMatri.save(matriculacion);
		}

		// Actualizar caja si existe y la asignatura cambió (o siempre recalcular)
		CajaEntity caja = repoCaja.findByMatricula(matriculacion);
		if (caja != null) {
			double nuevaTasa = matriculacion.getAsignatura().getTasa();
			if (caja.getImporte() != nuevaTasa) {
				caja.setImporte(nuevaTasa);
				repoCaja.save(caja);
			}
		}

		return matriculacion.getId();
	}

	@Transactional
	@Override
	public int borrarMatriculacion(String id) {
		if (id == null || id.trim().isEmpty()) {
			throw new IllegalArgumentException("ID de matrícula es obligatorio");
		}

		int idInt = Integer.parseInt(id);
		MatriculacionesEntity matriculacion = repoMatri.findById(idInt)
				.orElseThrow(() -> new IllegalArgumentException("Matrícula no encontrada: " + id));

		// Eliminar entrada en caja asociada
		CajaEntity caja = repoCaja.findByMatricula(matriculacion);
		if (caja != null) {
			repoCaja.delete(caja);
		}

		repoMatri.delete(matriculacion);

		return idInt;
	}
}