package com.renato.quiroga.retoquileia.models.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.renato.quiroga.retoquileia.models.entity.TuristaCiudad;

public interface ITuristaCiudadService {
	
	public List<TuristaCiudad> findAll();
	
	public TuristaCiudad findById(Long id);
	
	public TuristaCiudad save(TuristaCiudad turistaCiudad);
	
	public void delete(Long id);
	
	public List<TuristaCiudad> fechaAndCiudad(String fecha, Long id);
	
	public List<TuristaCiudad> consultaPorTurista(@Param("id") Long id);
	
	public List<TuristaCiudad> consultaPorCiudad(@Param("id") Long id);

}
