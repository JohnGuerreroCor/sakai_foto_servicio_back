package com.usco.edu.entities;

public class Respuesta {

	public Respuesta() {
		super();
	}
	
	
	public Respuesta(boolean estado, String mensaje, String consola) {
		super();
		this.estado = estado;
		this.mensaje = mensaje;
		this.consola = consola;
	}


	private boolean estado;
	private String mensaje;
	private String consola;
	
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getConsola() {
		return consola;
	}
	public void setConsola(String consola) {
		this.consola = consola;
	}
	
	@Override
	public String toString() {
		return "Respuesta [estado=" + estado + ", mensaje=" + mensaje + ", consola=" + consola + "]";
	}
	
}
