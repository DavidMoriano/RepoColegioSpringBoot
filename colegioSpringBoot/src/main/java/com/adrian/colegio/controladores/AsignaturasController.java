package com.adrian.colegio.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import com.adrian.colegio.dtos.AsignaturaDTO;
import com.adrian.colegio.servicio.interfaces.IAsignaturaService;

@Controller
@RequestMapping("/asignaturas")
public class AsignaturasController {

	@Autowired
	IAsignaturaService asignaturaServices;

	@GetMapping("/insertarAsignatura")
	public String formularioInsertarAsignatura() {
		return "asignaturas/insertarAsignatura";
	}

	@PostMapping("/insertarAsignatura")
	public String insertarAsignaturas(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
			@RequestParam("curso") int curso, @RequestParam("tasa") float tasa,
			@RequestParam(value = "activo", required = false) Integer activo, ModelMap model) {
		int act = activo != null ? 1 : 0;
		Integer resultado = asignaturaServices.insertarAsignatura(id, nombre, curso, tasa, act);
		model.addAttribute("resultado", resultado);
		return "asignaturas/insertarAsignatura";

	}

	//////////////////////////////////////////////////////////////////////

	@GetMapping("/listadoAsignaturas")
	public String formularioListadoAsignaturas() {
		return "asignaturas/listadoAsignaturas";
	}

	@PostMapping("/listadoAsignaturas")
	public String listadoAsignaturas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Float tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {
		int act = (activo != null) ? 1 : 0;
		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturaServices.obtenerAsignaturasPorIdNombreCursoTasaActivo(id,
				nombre, curso, tasa, act);
		model.addAttribute("lista", listaAsignaturas);
		return "asignaturas/listadoAsignaturas";
	}

	//////////////////////////////////////////////////////////////////////

	@GetMapping(value = "/formularioActualizarAsignaturas")
	public String modificarAsignatura() {
		return "asignaturas/actualizarAsignaturas";
	}

	@PostMapping(value = "/formularioActualizarAsignaturas")
	public String formularioModificarAsignatura(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Float tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {
		int act = (activo != null) ? 1 : 0;
		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturaServices.obtenerAsignaturasPorIdNombreCursoTasaActivo(id,
				nombre, curso, tasa, act);
		model.addAttribute("lista", listaAsignaturas);
		return "asignaturas/actualizarAsignaturas";
	}

	@PostMapping(value = "/actualizarAsignaturas")
	public String modificarAsignatura(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "curso", required = false) int curso,
			@RequestParam(value = "tasa", required = false) Float tasa,
			@RequestParam(value = "activo", required = false) Integer activo, ModelMap model) {
		Integer act = activo != null ? 1 : 0;
		int resultado = asignaturaServices.actualizarAsignatura(id, nombre, curso, tasa, act);
		model.addAttribute("resultado", resultado);
		return "asignaturas/actualizarAsignaturas";
	}

	//////////////////////////////////////////////////////////////////////

	@GetMapping(value = "/formularioBorrarAsignaturas")
	public String borrarAsignaturas() {
		return "asignaturas/borrarAsignaturas";
	}

	@PostMapping(value = "/formularioBorrarAsignaturas")
	public String formularioEliminarAsignaturas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "curso", required = false) Integer curso,
			@RequestParam(value = "tasa", required = false) Float tasa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {
		int act = (activo != null) ? 1 : 0;
		ArrayList<AsignaturaDTO> listaAsignaturas = asignaturaServices.obtenerAsignaturasPorIdNombreCursoTasaActivo(id,
				nombre, curso, tasa, act);
		model.addAttribute("lista", listaAsignaturas);
		return "asignaturas/borrarAsignaturas";
	}

	@PostMapping(value = "/borrarAsignaturas")
	public String eliminarAlumnos(@RequestParam(value = "id", required = false) Integer id, ModelMap model) {
		Integer resultado = asignaturaServices.borrarAsignatura(id);
		model.addAttribute("resultado", resultado);
		return "asignaturas/borrarAsignaturas";
	}

}
