package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="role", length = 30)
	private String nombre_rol;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre_rol() {
		return nombre_rol;
	}

	public void setNombre_rol(String nombre_rol) {
		this.nombre_rol = nombre_rol;
	}

	

	public Role(int id, String nombre_rol) {
		this.id = id;
		this.nombre_rol = nombre_rol;

	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", nombre_rol=" + nombre_rol + "]";
	}



	private static final long serialVersionUID = 1L;

}
