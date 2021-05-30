package com.unla.nahuel.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.unla.nahuel.entities.Lugar;
import com.unla.nahuel.entities.Permiso;
import com.unla.nahuel.entities.PermisoDiario;
import com.unla.nahuel.entities.Persona;
import com.unla.nahuel.helpers.ViewRouteHelper;
import com.unla.nahuel.services.ILugarService;
import com.unla.nahuel.services.IPermisoDiarioService;
import com.unla.nahuel.services.IPermisoService;
import com.unla.nahuel.services.IPersonaService;

@Controller
@RequestMapping("/permiso_diario")
public class PermisoDiarioController {
	
	@Autowired
	@Qualifier("permisoDiarioService")
	private IPermisoDiarioService permisoDiarioService;
	
	@Autowired
	@Qualifier("personaService")
	private IPersonaService personaService;
	
	@Autowired
	@Qualifier("lugarService")
	private ILugarService lugarService;
	
	@Autowired
	@Qualifier("permisoService")
	private IPermisoService permisoService;
	
	
	@GetMapping("/")
	public String crear(Model model) {
		
		PermisoDiario permiso = new PermisoDiario();
		List<Persona> personas = personaService.getAll();
		List<Lugar> lugares = lugarService.getAll();
		
		
		model.addAttribute("titulo", "Nuevo Permiso");
		model.addAttribute("permiso", permiso);
		model.addAttribute("personas", personas);
		model.addAttribute("lugares", lugares);
		return ViewRouteHelper.PERMISO_DIARIO_CREAR;
	}
	
	@PostMapping("/")
	public String guardar(PermisoDiario permiso,Model model) {
		
		System.out.println(permiso.getFecha()); 
		System.out.println(permiso.getPersona());
		permisoDiarioService.save(permiso);
		System.out.println("Permiso guardado con exito!");
		return ViewRouteHelper.PERMISO_DIARIO_REDIRECT;
		
	}
	
	@GetMapping("/lista")
	public String listarClientes(Model model) {
		List<PermisoDiario> listado = permisoDiarioService.getAll();
		model.addAttribute("titulo","Lista de perfiles");
		model.addAttribute("lista",listado);
		
		return ViewRouteHelper.PERMISO_DIARIO_LISTA;
	}

}