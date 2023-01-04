package com.renato.quiroga.retoquileia.models.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ciudades")
public class Ciudad {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private int habitantes;
	
	private String sitio_turistico;
	
	private String mejor_hotel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getHabitantes() {
		return habitantes;
	}

	public void setHabitantes(int habitantes) {
		this.habitantes = habitantes;
	}

	public String getSitio_turistico() {
		return sitio_turistico;
	}

	public void setSitio_turistico(String sitio_turistico) {
		this.sitio_turistico = sitio_turistico;
	}

	public String getMejor_hotel() {
		return mejor_hotel;
	}

	public void setMejor_hotel(String mejor_hotel) {
		this.mejor_hotel = mejor_hotel;
	}
	
	

}
