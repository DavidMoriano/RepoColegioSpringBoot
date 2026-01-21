package com.adrian.colegio.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.dtos.MatriculacionesDTO;
import com.adrian.colegio.servicio.interfaces.IMatriculacionesService;

@Controller
@RequestMapping("/matriculaciones")
public class MatriculacionesController {

	@Autowired
	private IMatriculacionesService matriculacionesService;

	@Autowired
	private IDesplegablesDAO desplegables;

	@GetMapping("/listadoMatriculaciones")
	public String formularioListadoMatriculaciones() {
		return "matriculaciones/listadoMatriculaciones";
	}

	@PostMapping("/listadoMatriculaciones")
	public String listadoMatriculaciones(
			@RequestParam(value = "nombreAsignatura", required = false) String nombreAsignatura,
			@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "activo", required = false) String activoStr, ModelMap model) {

		int activo = 1;
		if (activoStr == null || "0".equals(activoStr)) {
			activo = 0;
		}

		LocalDate fechaDesde = null;
		if (fecha != null && !fecha.trim().isEmpty()) {
			try {
				fechaDesde = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				fechaDesde = LocalDate.now().minusYears(1);
			}
		}

		List<MatriculacionesDTO> lista = matriculacionesService.obtenerMatriculacionesPorFiltros(nombreAsignatura,
				nombreAlumno, fechaDesde != null ? fechaDesde.toString() : null, activo);

		model.addAttribute("lista", lista);

		model.addAttribute("nombreAsignatura", nombreAsignatura);
		model.addAttribute("nombreAlumno", nombreAlumno);
		model.addAttribute("fecha", fecha);
		model.addAttribute("activo", activo == 1);

		return "matriculaciones/listadoMatriculaciones";
	}

	@GetMapping("/insertarMatriculacion")
	public String formularioInsertarMatriculacion(ModelMap model) {
		ArrayList<DesplegableDTO> alumnos = desplegables.desplegableAlumnos();
		ArrayList<DesplegableDTO> asignaturas = desplegables.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", alumnos);
		model.addAttribute("desplegableAsignaturas", asignaturas);

		return "matriculaciones/insertarMatriculacion";
	}

	@PostMapping("/insertarMatriculacion")
	public String insertarMatriculacion(@RequestParam("alumno") int idAlumno,
			@RequestParam("asignatura") int idAsignatura, @RequestParam("fecha") String fecha,
			@RequestParam(value = "activo", defaultValue = "1") String activo, ModelMap model) {

		System.out.print("Alumno: " + idAlumno + "\n");
		System.out.print("Asignatura: " + idAsignatura + "\n");
		System.out.print("Fecha: " + fecha + "\n");
		System.out.print("Activo: " + activo + "\n");

		LocalDate fechaFiltro;
		if (fecha != null || fecha.isEmpty()) {
			try {
				fechaFiltro = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				fechaFiltro = LocalDate.now();
			}
		} else {
			fechaFiltro = null;
		}

		Integer idGenerado = matriculacionesService.insertarMatriculacion(String.valueOf(idAsignatura),
				String.valueOf(idAlumno), fechaFiltro.toString(), activo);

		model.addAttribute("resultado", idGenerado);

		model.addAttribute("desplegableAlumnos", desplegables.desplegableAlumnos());
		model.addAttribute("desplegableAsignaturas", desplegables.desplegableAsignaturas());

		return "matriculaciones/insertarMatriculacion";
	}

	@GetMapping("/formularioActualizarMatriculaciones")
	public String formularioModificarMatriculaciones() {
		return "matriculaciones/actualizarMatriculaciones";
	}

	@PostMapping("/formularioActualizarMatriculaciones")
	public String buscarParaActualizar(@RequestParam("nombreAlumno") String nombreAlumno,
			@RequestParam("asignatura") String nombreAsignatura, @RequestParam("fecha") String fecha, ModelMap model) {

		model.addAttribute("desplegableAlumnos", desplegables.desplegableAlumnos());
		model.addAttribute("desplegableAsignaturas", desplegables.desplegableAsignaturas());

		LocalDate fechaFiltro = null;
		if (fecha != null && !fecha.trim().isEmpty()) {
			try {
				fechaFiltro = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				fechaFiltro = LocalDate.now();
			}
		}

		List<MatriculacionesDTO> lista = matriculacionesService.obtenerMatriculacionesPorFiltros(nombreAsignatura,
				nombreAlumno, fechaFiltro != null ? fechaFiltro.toString() : null, 1);

		model.addAttribute("lista", lista);
		model.addAttribute("nombreAlumno", nombreAlumno);
		model.addAttribute("asignatura", nombreAsignatura);
		model.addAttribute("fecha", fecha);

		return "matriculaciones/actualizarMatriculaciones";
	}

	@PostMapping("/actualizarMatriculacion")
	public String actualizarMatriculacion(@RequestParam("id") String id,
			@RequestParam(value = "alumno", required = false) Integer idAlumno,
			@RequestParam(value = "asignatura", required = false) Integer idAsignatura,
			@RequestParam(value = "fecha", required = false) String fecha,
			@RequestParam(value = "tasa", required = false) String tasa, ModelMap model) {

		model.addAttribute("desplegableAlumnos", desplegables.desplegableAlumnos());
		model.addAttribute("desplegableAsignaturas", desplegables.desplegableAsignaturas());

		Integer resultado = matriculacionesService.actualizarMatriculacion(id,
				idAsignatura != null ? idAsignatura.toString() : null, idAlumno != null ? idAlumno.toString() : null,
				fecha, tasa);

		model.addAttribute("resultado", resultado);

		return "matriculaciones/actualizarMatriculaciones";
	}

	@GetMapping("/formularioBorrarMatriculaciones")
	public String formularioBorrarMatriculaciones() {
		return "matriculaciones/borrarMatriculaciones";
	}

	@PostMapping("/formularioBorrarMatriculaciones")
	public String buscarParaBorrar(@RequestParam("nombreAlumno") String nombreAlumno,
			@RequestParam("asignatura") String nombreAsignatura, @RequestParam("fecha") String fecha, ModelMap model) {

		model.addAttribute("desplegableAlumnos", desplegables.desplegableAlumnos());
		model.addAttribute("desplegableAsignaturas", desplegables.desplegableAsignaturas());

		LocalDate fechaFiltro = null;
		if (fecha != null && !fecha.trim().isEmpty()) {
			try {
				fechaFiltro = LocalDate.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE);
			} catch (DateTimeParseException e) {
				fechaFiltro = LocalDate.now();
			}
		}

		List<MatriculacionesDTO> lista = matriculacionesService.obtenerMatriculacionesPorFiltros(nombreAsignatura,
				nombreAlumno, fechaFiltro != null ? fechaFiltro.toString() : null, 1);

		model.addAttribute("lista", lista);
		model.addAttribute("nombreAlumno", nombreAlumno);
		model.addAttribute("asignatura", nombreAsignatura);
		model.addAttribute("fecha", fecha);

		return "matriculaciones/borrarMatriculaciones";
	}

	@PostMapping("/borrarMatriculacion")
	public String borrarMatriculacion(@RequestParam("id") String idMatricula, ModelMap model) {

		Integer resultado = matriculacionesService.borrarMatriculacion(idMatricula);
		model.addAttribute("resultado", resultado);
		return "matriculaciones/borrarMatriculaciones";
	}
}