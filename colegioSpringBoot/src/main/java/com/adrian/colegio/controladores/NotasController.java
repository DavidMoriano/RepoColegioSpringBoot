package com.adrian.colegio.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;

@Controller
@RequestMapping("/notas")
public class NotasController {

	@Autowired
	INotasService notasServ;

	@Autowired
	IDesplegablesDAO desplegables;

	@GetMapping("/listadoNotas")
	public String formularioListadoAsignaturas() {
		return "notas/listadoNotas";
	}

	@SuppressWarnings("null")
	@PostMapping("/listadoNotas")
	public String listadoNotas(@RequestParam(required = false) Integer idAlumno,
			@RequestParam(required = false) String nombreAlumno, @RequestParam(required = false) String asignatura,
			@RequestParam(required = false) Float nota, @RequestParam(required = false) String fecha,
			@RequestParam(required = false) String activo, ModelMap model) {

		int activoInt = "on".equals(activo) || "true".equalsIgnoreCase(activo) || "1".equals(activo) ? 1 : 0;

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

		ArrayList<NotasDTO> listaNotas = notasServ.obtenerNotasPorFiltros(idAlumno, nombreAlumno, asignatura, nota,
				fechaFiltro.toString(), activoInt);

		model.addAttribute("lista", listaNotas);

		model.addAttribute("idAlumno", idAlumno);
		model.addAttribute("nombreAlumno", nombreAlumno);
		model.addAttribute("asignatura", asignatura);
		model.addAttribute("nota", nota);
		model.addAttribute("fecha", fecha);
		model.addAttribute("activo", activoInt == 1);

		return "notas/listadoNotas";
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("insertarNota")
	public String formularioInsertarNotas(ModelMap model) {
		ArrayList<DesplegableDTO> listaAlumnos = desplegables.desplegableAlumnos();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegables.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", listaAlumnos);
		model.addAttribute("desplegableAsignaturas", listaAsignaturas);

		return "notas/insertarNota";
	}

	@PostMapping("insertarNota")
	public String insertarAsignaturas(@RequestParam("alumno") int idAlumno,
			@RequestParam("asignatura") int idAsignatura, @RequestParam("nota") String nota,
			@RequestParam("fecha") String fecha, ModelMap model) {

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

		Integer resultado = notasServ.insertarNota(idAlumno, idAsignatura, nota, fechaFiltro.toString());
		model.addAttribute("resultado", resultado);
		return "notas/insertarNota";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("formularioActualizarNotas")
	public String formularioModificarNotas(ModelMap model) {
		return "notas/actualizarNotas";
	}

	@PostMapping("formularioActualizarNotas")
	public String formularioModificarNotas(@RequestParam("nombreAlumno") String nombreAlumno,
			@RequestParam("asignatura") String nombreAsignatura, @RequestParam("fecha") String fecha, ModelMap model) {

		ArrayList<DesplegableDTO> listaAlumnos = desplegables.desplegableAlumnos();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegables.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", listaAlumnos);
		model.addAttribute("desplegableAsignaturas", listaAsignaturas);

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
		
		
		ArrayList<NotasDTO> listaNotas = notasServ.obtenerNotasPorFiltros(null, nombreAlumno, nombreAsignatura, null,
				fechaFiltro.toString(), 1);

		model.addAttribute("lista", listaNotas);

		return "notas/actualizarNotas";
	}

	@PostMapping("actualizarNotas")
	public String modificarNotas(@RequestParam("id") String id, @RequestParam("alumno") int idAlumno,
			@RequestParam("asignatura") int idAsignatura, @RequestParam("nota") String nota,
			@RequestParam("fecha") String fecha, ModelMap model) {

		ArrayList<DesplegableDTO> listaAlumnos = desplegables.desplegableAlumnos();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegables.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", listaAlumnos);
		model.addAttribute("desplegableAsignaturas", listaAsignaturas);

		Integer resultado = notasServ.actualizarNota(id, idAlumno, idAsignatura, nota, fecha);
		model.addAttribute("resultado", resultado);

		return "notas/actualizarNotas";
	}

	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@GetMapping("formularioBorrarNotas")
	public String formularioBorrarNotas() {
		return "notas/borrarNotas";
	}

	@PostMapping("formularioBorrarNotas")
	public String formularioBorrarNotas(@RequestParam("nombreAlumno") String nombreAlumno,
			@RequestParam("asignatura") String nombreAsignatura, @RequestParam("fecha") String fecha, ModelMap model) {

		ArrayList<DesplegableDTO> listaAlumnos = desplegables.desplegableAlumnos();
		ArrayList<DesplegableDTO> listaAsignaturas = desplegables.desplegableAsignaturas();

		model.addAttribute("desplegableAlumnos", listaAlumnos);
		model.addAttribute("desplegableAsignaturas", listaAsignaturas);

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
		
		ArrayList<NotasDTO> listaNotas = notasServ.obtenerNotasPorFiltros(null, nombreAlumno, nombreAsignatura, null,
				fechaFiltro.toString(), 1);

		model.addAttribute("lista", listaNotas);

		return "notas/borrarNotas";
	}

	@PostMapping("borrarNotas")
	public String borrarNotas(@RequestParam("id") String idNota, ModelMap model) {
		Integer resultado = notasServ.borrarNota(idNota);
		model.addAttribute("resultado", resultado);

		return "notas/borrarNotas";
	}
}
