package com.renato.quiroga.retoquileia.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.renato.quiroga.retoquileia.models.entity.Ciudad;
import com.renato.quiroga.retoquileia.models.service.CiudadServiceImpl;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/ciudades")
public class CiudadRestController {
	
	@Autowired
	private CiudadServiceImpl ciudadService;
	
	@GetMapping("/ciudades")
	public List<Ciudad> index(){
		
		return ciudadService.findAll();
	}
	
	@GetMapping("/ciudades/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		Ciudad ciudad = null;
		Map<String, Object> response = new HashMap<>();
		
		 try {
				
			 ciudad = ciudadService.findById(id);
				
		 } catch(DataAccessException e){
				
			 response.put("mensaje", "Error al realizar la consulta en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
		 }
			
		 if(ciudad == null) {
			 response.put("mensaje", "La ciudad con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		 }
			
		 return new ResponseEntity<Ciudad>(ciudad, HttpStatus.OK);
		
	}
	
	@PostMapping("/ciudades")
	public ResponseEntity<?> create(@Valid @RequestBody Ciudad ciudad, BindingResult result){
		
		Ciudad ciudadNuevo = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			
			ciudadNuevo = ciudadService.save(ciudad);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La ciudad ha sido creado con exito!");
		response.put("ciudad", ciudadNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/ciudades/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Ciudad ciudad, BindingResult result, @PathVariable Long id){
		
		Ciudad ciudadActual = ciudadService.findById(id);
		Ciudad ciudadActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(ciudadActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la ciudad con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			ciudadActual.setNombre(ciudad.getNombre());
			ciudadActual.setHabitantes(ciudad.getHabitantes());
			ciudadActual.setSitio_turistico(ciudad.getSitio_turistico());
			ciudadActual.setMejor_hotel(ciudad.getMejor_hotel());
			

			
			ciudadActualizado = ciudadService.save(ciudadActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar los datos del tu ciudad en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La ciudad ha sido actualizado con exito!");
		response.put( "ciudad", ciudadActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/ciudades/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 ciudadService.delete(id);
			 
		 }catch(DataAccessException e) {
				response.put("mensaje", "Error al eliminar la ciudad de la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("mensaje", "La ciudad ha sido eliminado con exito");
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}

}
