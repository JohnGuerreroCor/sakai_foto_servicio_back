package com.usco.edu.dto;

public class Email {
	
	private String asunto;
	private String hash;
	private String destinatario;
	private String nombreDestinatario;
	private String nombreAplicativo;
	private String textoUno;
	private String textoDos;
	private String confidencial;
	private String titulo;
	private String footer;
	private String textoTres;
	private String programa;
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getFooter() {
		return footer;
	}
	public void setFooter(String footer) {
		this.footer = footer;
	}
	
	public String getTextoTres() {
		return textoTres;
	}
	public void setTextoTres(String textoTres) {
		this.textoTres = textoTres;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getConfidencial() {
		return confidencial;
	}
	public void setConfidencial(String confidencial) {
		this.confidencial = confidencial;
	}
	public String getTextoDos() {
		return textoDos;
	}
	public void setTextoDos(String textoDos) {
		this.textoDos = textoDos;
	}
	
	public String getTextoUno() {
		return textoUno;
	}
	public void setTextoUno(String textoUno) {
		this.textoUno = textoUno;
	}
	
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getNombreDestinatario() {
		return nombreDestinatario;
	}
	public void setNombreDestinatario(String nombreDestinatario) {
		this.nombreDestinatario = nombreDestinatario;
	}
	
	public String getNombreAplicativo() {
		return nombreAplicativo;
	}
	public void setNombreAplicativo(String nombreAplicativo) {
		this.nombreAplicativo = nombreAplicativo;
	}
	
	
	@Override
	public String toString() {
		return "Email [asunto=" + asunto + ", hash=" + hash + ", destinatario=" + destinatario + ", nombreDestinatario="
				+ nombreDestinatario + ", nombreAplicativo=" + nombreAplicativo + ", textoUno=" + textoUno + ", textoDos=" + textoDos + ", confidencial=" + confidencial + ", titulo=" + titulo + "]";
	}

}
