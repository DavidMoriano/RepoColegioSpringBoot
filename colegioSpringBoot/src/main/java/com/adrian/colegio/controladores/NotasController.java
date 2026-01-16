package com.adrian.colegio.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping("/listadoNotas")
	public String listadoNotas(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "idAlumno", required = false) Integer idAlumno,
			@RequestParam(value = "nombreAlumno", required = false) String nombreAlumno,
			@RequestParam(value = "asignatura", required = false) String nombreAsignatura,
			@RequestParam(value = "nota", required = false) Float nota,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {
		int act = (activo != null) ? 1 : 0;
		ArrayList<NotasDTO> listaNotas = notasServ.obtenerNotasPorFiltros(idAlumno, nombreAlumno, nombreAsignatura,
				nota, nombreAsignatura, act);
		model.addAttribute("lista", listaNotas);
		return "notas/listadoNotas";
	}
}
