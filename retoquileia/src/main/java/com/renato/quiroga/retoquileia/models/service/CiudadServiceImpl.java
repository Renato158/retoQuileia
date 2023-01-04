package com.renato.quiroga.retoquileia.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.quiroga.retoquileia.models.entity.Ciudad;
import com.renato.quiroga.retoquileia.models.repository.ICiudadRepository;

@Service
public class CiudadServiceImpl implements IService<Ciudad> {
	
	@Autowired
	private ICiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> findAll() {
		// TODO Auto-generated method stub
		return (List<Ciudad>) ciudadRepository.findAll();
	}

	@Override
	public Ciudad findById(Long id) {
		// TODO Auto-generated method stub
		return ciudadRepository.findById(id).orElse(null);
	}

	@Override
	public Ciudad save(Ciudad ciudad) {
		// TODO Auto-generated method stub
		return ciudadRepository.save(ciudad);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		ciudadRepository.deleteById(id);
		
	}

}
