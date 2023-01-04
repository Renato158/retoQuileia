package com.renato.quiroga.retoquileia.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.quiroga.retoquileia.models.entity.Turista;
import com.renato.quiroga.retoquileia.models.repository.ITuristaRepository;

@Service
public class TuristaServiceImpl implements IService<Turista> {

	@Autowired
	private ITuristaRepository turistaRepository;
	
	@Override
	public List<Turista> findAll() {
		// TODO Auto-generated method stub
		return (List<Turista>) turistaRepository.findAll();
	}

	@Override
	public Turista findById(Long id) {
		// TODO Auto-generated method stub
		return turistaRepository.findById(id).orElse(null);
	}

	@Override
	public Turista save(Turista turista) {
		// TODO Auto-generated method stub
		return turistaRepository.save(turista);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		turistaRepository.deleteById(id);
		
	}

}
