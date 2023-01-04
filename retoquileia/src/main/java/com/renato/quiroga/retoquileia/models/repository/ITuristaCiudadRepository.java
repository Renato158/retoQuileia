package com.renato.quiroga.retoquileia.models.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.renato.quiroga.retoquileia.models.entity.TuristaCiudad;

public interface ITuristaCiudadRepository extends CrudRepository<TuristaCiudad, Long>{
	
	@Query(
			value = "SELECT * FROM turistas_ciudades WHERE turistas_ciudades.fecha LIKE %:fecha% AND turistas_ciudades.id_ciudad LIKE %:id% ",
			nativeQuery = true
	)
	List<TuristaCiudad> fechaAndCiudad(@Param("fecha") String fecha,@Param("id") Long id);
	
	@Query(
			value = "SELECT * FROM turistas_ciudades WHERE turistas_ciudades.id_turista LIKE %:id_turista%",
			nativeQuery = true
	)
	List<TuristaCiudad> consultaPorTurista(@Param("id_turista") Long id_turista);
	
	@Query(
			value = "SELECT * FROM turistas_ciudades WHERE turistas_ciudades.id_ciudad LIKE %:id_ciudad%",
			nativeQuery = true
	)
	List<TuristaCiudad> consultaPorCiudad(@Param("id_ciudad") Long id_ciudad);
}
