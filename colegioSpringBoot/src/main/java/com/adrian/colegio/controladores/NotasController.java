package com.adrian.colegio.controladores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dtos.NotasDTO;
import com.adrian.colegio.servicio.interfaces.INotasService;

@Controller
@RequestMapping("/notas")
public class NotasController {

	@Autowired
	INotasService notasServ;

	@GetMapping("/listadoNotas")
	public String formularioListadoAsignaturas() {
		return "notas/listadoNotas";
	}

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
}
