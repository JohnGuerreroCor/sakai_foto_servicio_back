package com.usco.edu.entities;

import java.io.Serializable;

public class RespuestaToken  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean estado;
	private String mensaje;
	private String consola;
	
	
	public boolean isEstado() {
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
		return "RespuestaToken [estado=" + estado + ", mensaje=" + mensaje + ", consola=" + consola + "]";
	}
	
	public RespuestaToken(RespuestaToken respuestaToken) {
		// TODO Auto-generated constructor stub
	}
	public RespuestaToken(boolean estado, String mensaje, String consola) {
		this.estado = estado;
		this.mensaje = mensaje;
		this.consola = consola;
	}
	public RespuestaToken() {
		// TODO Auto-generated constructor stub
	}
	


	
	
}
