package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "asignatura", schema = "dbo")
public class Asignatura implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "asi_codigo", columnDefinition = "integer")
	private int codigo;
	
	@Column(name = "asi_nombre")
	private String nombre;
	
	@Column(name = "asi_nombre_corto")
	private String nombreCorto;
	
	@Column(name = "asi_nombre_impresion")
	private String nombreImpresion;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "uaa_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Uaa uaa;
	
	@Column(name = "asi_publicar")
	private String publicar;
	
	@Column(name = "asi_creditos")
	private int creditos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Caracter caracter;
	
	@Column(name = "asi_estado")
	private int estado;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nuc_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Nucleo nucleo;
	
	@Column(name = "asi_extramuro")
	private String extramuro;
	
	@Column(name = "asi_creditos_teoria")
	private int creditos_teoricos ;
	
	@Column(name = "asi_creditos_practica")
	private int creditos_practicos ;
	
	@Column(name = "asi_trabajo_presencial")
	private String trabajo_presencial;
	
	@Column(name = "asi_trabajo_independiente")
	private String trabajo_independiente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nbc_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Snies_nbc nbc;
	
	
	public Asignatura() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Asignatura(int codigo) {
		this.codigo = codigo;
	}



	public Asignatura(int codigo, String nombre, String nombreCorto, Uaa uaa, String publicar, int creditos,
			Caracter caracter, int estado, Nucleo nucleo, String extramuro) {
		this.codigo = codigo;
		this.nombre = nombre;
		this.nombreCorto = nombreCorto;
		this.uaa = uaa;
		this.publicar = publicar;
		this.creditos = creditos;
		this.caracter = caracter;
		this.estado = estado;
		this.nucleo = nucleo;
		this.extramuro = extramuro;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreCorto() {
		return nombreCorto;
	}

	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}

	public Uaa getUaa() {
		return uaa;
	}

	public void setUaa(Uaa uaa) {
		this.uaa = uaa;
	}

	public String getPublicar() {
		return publicar;
	}

	public void setPublicar(String publicar) {
		this.publicar = publicar;
	}

	public int getCreditos() {
		return creditos;
	}

	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}

	public Caracter getCaracter() {
		return caracter;
	}

	public void setCaracter(Caracter caracter) {
		this.caracter = caracter;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public Nucleo getNucleo() {
		return nucleo;
	}

	public void setNucleo(Nucleo nucleo) {
		this.nucleo = nucleo;
	}

	public String getExtramuro() {
		return extramuro;
	}

	public void setExtramuro(String extramuro) {
		this.extramuro = extramuro;
	}
	
	public int getCreditos_teoricos() {
		return creditos_teoricos;
	}

	public void setCreditos_teoricos(int creditos_teoricos) {
		this.creditos_teoricos = creditos_teoricos;
	}

	public int getCreditos_practicos() {
		return creditos_practicos;
	}

	public void setCreditos_practicos(int creditos_practicos) {
		this.creditos_practicos = creditos_practicos;
	}

	public String getTrabajo_presencial() {
		return trabajo_presencial;
	}

	public void setTrabajo_presencial(String trabajo_presencial) {
		this.trabajo_presencial = trabajo_presencial;
	}

	public String getTrabajo_independiente() {
		return trabajo_independiente;
	}

	public void setTrabajo_independiente(String trabajo_independiente) {
		this.trabajo_independiente = trabajo_independiente;
	}

	public Snies_nbc getNbc() {
		return nbc;
	}

	public void setNbc(Snies_nbc nbc) {
		this.nbc = nbc;
	}

	public String getNombreImpresion() {
		return nombreImpresion;
	}

	public void setNombreImpresion(String nombreImpresion) {
		this.nombreImpresion = nombreImpresion;
	}


	@Override
	public String toString() {
		return "Asignatura [codigo=" + codigo + ", nombre=" + nombre + ", nombreCorto=" + nombreCorto
				+ ", nombreImpresion=" + nombreImpresion + ", uaa=" + uaa + ", publicar=" + publicar + ", creditos="
				+ creditos + ", caracter=" + caracter + ", estado=" + estado + ", nucleo=" + nucleo + ", extramuro="
				+ extramuro + ", creditos_teoricos=" + creditos_teoricos + ", creditos_practicos=" + creditos_practicos
				+ ", trabajo_presencial=" + trabajo_presencial + ", trabajo_independiente=" + trabajo_independiente
				+ ", nbc=" + nbc + "]";
	}


	private static final long serialVersionUID = 1L;
	
}
