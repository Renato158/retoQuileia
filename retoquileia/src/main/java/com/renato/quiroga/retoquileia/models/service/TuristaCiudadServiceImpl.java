package com.renato.quiroga.retoquileia.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.quiroga.retoquileia.models.entity.TuristaCiudad;
import com.renato.quiroga.retoquileia.models.repository.ITuristaCiudadRepository;

@Service
public class TuristaCiudadServiceImpl implements ITuristaCiudadService{

	@Autowired
	private ITuristaCiudadRepository turistaCiudadRepository;
	
	@Override
	public List<TuristaCiudad> findAll() {
		// TODO Auto-generated method stub
		return (List<TuristaCiudad>) turistaCiudadRepository.findAll() ;
	}

	@Override
	public TuristaCiudad findById(Long id) {
		// TODO Auto-generated method stub
		return turistaCiudadRepository.findById(id).orElse(null);
	}

	@Override
	public TuristaCiudad save(TuristaCiudad turistaCiudad) {
		// TODO Auto-generated method stub
		return turistaCiudadRepository.save(turistaCiudad);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		turistaCiudadRepository.deleteById(id);
		
	}
	
	@Override
	public List<TuristaCiudad> fechaAndCiudad(String fecha, Long id) {
		
		return (List<TuristaCiudad>) turistaCiudadRepository.fechaAndCiudad(fecha, id);
	}

	@Override
	public List<TuristaCiudad> consultaPorTurista(Long id_turista) {
		// TODO Auto-generated method stub
		return (List<TuristaCiudad>) turistaCiudadRepository.consultaPorTurista(id_turista);
	}

	@Override
	public List<TuristaCiudad> consultaPorCiudad(Long id_ciudad) {
		// TODO Auto-generated method stub
		return (List<TuristaCiudad>) turistaCiudadRepository.consultaPorCiudad(id_ciudad);
	}

}
