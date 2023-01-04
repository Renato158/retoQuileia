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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.renato.quiroga.retoquileia.models.entity.TuristaCiudad;
import com.renato.quiroga.retoquileia.models.service.TuristaCiudadServiceImpl;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/turistasC")
public class TuristaCiudadRestController {
	
	@Autowired
	private TuristaCiudadServiceImpl turistaCiudadService;
	
	@GetMapping("/turistasC")
	public List<TuristaCiudad> index(){
		
		return turistaCiudadService.findAll();
	}
	
	@GetMapping("/turistasC/{id}")
	public ResponseEntity<?> show(@PathVariable Long id){
		
		TuristaCiudad turistaCiudad = null;
		Map<String, Object> response = new HashMap<>();
		
		 try {
				
			 turistaCiudad = turistaCiudadService.findById(id);
				
		 } catch(DataAccessException e){
				
			 response.put("mensaje", "Error al realizar la consulta en la base de datos");
			 response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
				
		 }
			
		 if(turistaCiudad == null) {
			 response.put("mensaje", "La asignacion con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			 return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
			
		 }
			
		 return new ResponseEntity<TuristaCiudad>(turistaCiudad, HttpStatus.OK);
		
	}
	
	@PostMapping("/turistasC")
	public ResponseEntity<?> create(@Valid @RequestBody TuristaCiudad turistaCiudad, BindingResult result){
		
		TuristaCiudad turistaCNuevo = null;
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
			
			
			turistaCNuevo = turistaCiudadService.save(turistaCiudad);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La asignacion ha sido creado con exito!");
		response.put("turistaCiudad", turistaCNuevo);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/turistasC/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody TuristaCiudad ciudad, BindingResult result, @PathVariable Long id){
		
		TuristaCiudad turistaCActual = turistaCiudadService.findById(id);
		TuristaCiudad turistaCActualizado = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {
			
			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '"+ err.getField() + "' " +err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if(turistaCActual == null) {
			response.put("mensaje", "Error, no se pudo editar, la ciudad con el ID: ".concat(id.toString().concat(" no existe en la base de datos")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		try {
			
			
			turistaCActual.setFecha(ciudad.getFecha());
			
			turistaCActualizado = turistaCiudadService.save(turistaCActual);
			
		}catch(DataAccessException e) {
			response.put("mensaje", "Error al actualizar los datos del la asignacion en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "La asignacion ha sido actualizado con exito!");
		response.put( "ciudad", turistaCActualizado);
		
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/turistasC/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id){
		
		 Map<String, Object> response = new HashMap<>();
		 
		 try {
			 turistaCiudadService.delete(id);
			 
		 }catch(DataAccessException e) {
				response.put("mensaje", "Error al eliminar la asignacion de la base de datos");
				response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			response.put("mensaje", "La asignacion ha sido eliminado con exito");
			
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/filtro/{id}")
	public List<TuristaCiudad> fechaAndCiudad(@RequestParam String fecha,@PathVariable Long id){
		
		
		return turistaCiudadService.fechaAndCiudad(fecha, id);
	}
	
	@GetMapping("/filtroTurista/{id_turista}")
	public List<TuristaCiudad> buscarPorTurista(@PathVariable Long id_turista){
		
		
		return turistaCiudadService.consultaPorTurista(id_turista);
	}
	
	@GetMapping("/filtroCiudad/{id_ciudad}")
	public List<TuristaCiudad> buscarPorCiudad(@PathVariable Long id_ciudad){
		
		
		return turistaCiudadService.consultaPorCiudad(id_ciudad);
	}
	

}
