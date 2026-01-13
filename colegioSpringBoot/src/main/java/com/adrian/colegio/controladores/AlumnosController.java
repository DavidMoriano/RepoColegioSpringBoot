package com.adrian.colegio.controladores;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.adrian.colegio.dao.interfaces.IDesplegablesDAO;
import com.adrian.colegio.dtos.AlumnoDTO;
import com.adrian.colegio.dtos.DesplegableDTO;
import com.adrian.colegio.repositorios.AlumnoRepository;
import com.adrian.colegio.repositorios.MunicipioRepository;
import com.adrian.colegio.servicio.interfaces.IAlumnosService;

@Controller
@RequestMapping("/alumnos")
public class AlumnosController {

	@Autowired
	IAlumnosService alumnosService;
	@Autowired
	IDesplegablesDAO desplegables;
	@Autowired
	AlumnoRepository alumnoRepository;
	@Autowired
	MunicipioRepository municipioRepository;
	
	//-----------------
	//INSERTAR ALUMNO
	//-----------------
	@GetMapping("/insertarAlumno")
	public String formularioInsertarAlumno(ModelMap model) {
		ArrayList<DesplegableDTO> listaMunicipios = desplegables.desplegableMunicipios();

		model.addAttribute("desplegableMunicipios", listaMunicipios);

		return "alumnos/insertarAlumno";
	}

	@PostMapping("/insertarAlumno")
	public String insertarAlumno(@RequestParam("id") Integer id, @RequestParam("nombre") String nombre,
			@RequestParam("apellido") String apellido,
			@RequestParam(value = "famNumerosa", required = false) String famNumerosa,
			@RequestParam(value = "activo", required = false) String activo,
			@RequestParam("municipios") Integer idMunicipio, ModelMap model) {

		ArrayList<DesplegableDTO> listaMunicipios = desplegables.desplegableMunicipios();
		model.addAttribute("desplegableMunicipios", listaMunicipios);

		Integer familiaNumerosa = famNumerosa != null ? 1 : 0;
		Integer act = activo != null ? 1 : 0;

		Integer resultado = alumnosService.insertarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, act);

		model.addAttribute("resultado", resultado);

		return "alumnos/insertarAlumno";
	}
	
	//-----------------
	//LISTADO ALUMNOS
	//-----------------
	@GetMapping("/listadoAlumnos")
	public String formularioListadoAlumnos() {
		return "alumnos/listadoAlumnos";
	}

	@PostMapping("/listadoAlumnos")
	public String listadoAlumnos(@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "nombre", required = false) String nombre,
			@RequestParam(value = "apellido", required = false) String apellido,
			@RequestParam(value = "famNumerosa", required = false) String famNumerosa,
			@RequestParam(value = "activo", required = false) String activo, ModelMap model) {

		int familiaNumerosa = (famNumerosa != null) ? 1 : 0;
		int act = (activo != null) ? 1 : 0;

		ArrayList<AlumnoDTO> listaAlumnos = alumnosService.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido,
				familiaNumerosa, act);

		model.addAttribute("lista", listaAlumnos);

		return "alumnos/listadoAlumnos";
	}
	
	
	//-------------------------
	//MODIFICAR ALUMNO
	//-------------------------
	
	//Método que simplemente nos mostrará el formulario
	@GetMapping(value = "/formularioActualizarAlumnos")
	public String formularioModificarAlumnos(ModelMap model) {
	    return "alumnos/actualizarAlumnos";
	}

	//Método que se encarga de la búsqueda de alumnos para actualizar
	@PostMapping(value = "/formularioActualizarAlumnos")
	public String formularioModificarAlumnos(@RequestParam(value = "id", required = false) Integer id,
	                                         @RequestParam(value = "nombre", required = false) String nombre,
	                                         @RequestParam(value = "apellido", required = false) String apellido,
	                                         @RequestParam(value = "famNumerosa", required = false) String famNumerosa,
	                                         @RequestParam(value = "activo", required = false) String activo,
	                                         ModelMap model) {
	    
		ArrayList<DesplegableDTO> listaMunicipios = desplegables.desplegableMunicipios();
		model.addAttribute("desplegableMunicipios", listaMunicipios);
		
	    Integer familiaNumerosa = famNumerosa != null ? 1 : 0;
	    Integer act = activo != null ? 1 : 0;

	    ArrayList<AlumnoDTO> listaAlumnos = alumnosService.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido, familiaNumerosa, act);

	    model.addAttribute("lista", listaAlumnos);
	    return "alumnos/actualizarAlumnos";
	}

	//Método que lleva a cabo la actualización
	@PostMapping(value = "/actualizarAlumno")
	public String modificarAlumnos(@RequestParam("id") Integer id, 
	                               @RequestParam("nombre") String nombre,
	                               @RequestParam("apellido") String apellido,
	                               @RequestParam(value = "famNumerosa", required = false) Integer famNumerosa,
	                               @RequestParam(value = "activo", required = false) Integer activo,
	                               @RequestParam("municipio") Integer idMunicipio, 
	                               ModelMap model) {
	    

		ArrayList<DesplegableDTO> listaMunicipios = desplegables.desplegableMunicipios();
		model.addAttribute("desplegableMunicipios", listaMunicipios);

	    Integer familiaNumerosa = famNumerosa != null ? 1 : 0;
	    Integer act = activo != null ? 1 : 0;

	    Integer resultado = alumnosService.actualizarAlumno(id, nombre, apellido, idMunicipio, familiaNumerosa, act);

	    model.addAttribute("resultado", resultado);
	    return "alumnos/actualizarAlumnos";
	}
	
	
	//-------------------------
	//BORRAR ALUMNO
	//-------------------------
	//Método que se usa simplemente para mostrar el formulario.
	@GetMapping(value = "/formularioBorrarAlumnos")
	public String getFormularioEliminarAlumnos() {
	    return "alumnos/borrarAlumnos";
	}

	//Método que se usa para buscar los registros a borrar
	@PostMapping(value = "/formularioBorrarAlumnos")
	public String formularioEliminarAlumnos(@RequestParam(value= "id", required = false) Integer id, 
	                                        @RequestParam("nombre") String nombre,
	                                        @RequestParam("apellido") String apellido,
	                                        @RequestParam(value = "famNumerosa", required = false) Integer famNumerosa,
	                                        @RequestParam(value = "activo", required = false) Integer activo, 
	                                        ModelMap model) {
	    
	    Integer familiaNumerosa = famNumerosa != null ? 1 : 0;
	    Integer act = activo != null ? 1 : 0;

	    ArrayList<AlumnoDTO> listaAlumnos = alumnosService.obtenerAlumnosPorIdNombreApellido(id, nombre, apellido, familiaNumerosa, act);

	    model.addAttribute("lista", listaAlumnos);
	    return "alumnos/borrarAlumnos";
	}

	@PostMapping(value = "/borrarAlumno")
	public String eliminarAlumnos(@RequestParam("id") Integer id, ModelMap model) {
	    Integer resultado = alumnosService.borrarAlumno(id);

	    model.addAttribute("resultado", resultado);
	    return "alumnos/borrarAlumnos";
	}
		
	
}
