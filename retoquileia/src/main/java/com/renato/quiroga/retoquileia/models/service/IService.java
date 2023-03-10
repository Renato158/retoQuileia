package com.renato.quiroga.retoquileia.models.service;

import java.util.List;

public interface IService<T> {
	
	public List<T> findAll();
	
	public T findById(Long id);
	
	public T save(T t);
	
	public void delete(Long id);

}
