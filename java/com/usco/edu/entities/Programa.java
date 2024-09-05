package com.usco.edu.entities;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class Programa implements Serializable {

	private Long codigo;

	private Uaa uaa;

	private NivelAcademico nivelAcademico;

	private String registroIcfes;

	private String registroSnies;

	private String proPropio;

	private String extension;

	private String proUnificado;

	private Snies_nbc nbc;

	private Modalidad modalidad;

	private Jornada jornada;

	private Resolucion resolucion;

	private Sede sede;

	private Convenio convenio;

	private Estado estado;

	private String nombre;

	private String calendario;

	private String horario;

	private String fecha_creacion;

	private CampoDetallado campoDetallado;

	public Programa(Long codigo, Uaa uaa, NivelAcademico nivelAcademico, String registroIcfes, String registroSnies,
			String proPropio, String extension, String proUnificado, Snies_nbc nbc, Modalidad modalidad,
			Jornada jornada, Resolucion resolucion, Sede sede, Convenio convenio, Estado estado, String nombre,
			String calendario, String horario, String fecha_creacion) {
		this.codigo = codigo;
		this.uaa = uaa;
		this.nivelAcademico = nivelAcademico;
		this.registroIcfes = registroIcfes;
		this.registroSnies = registroSnies;
		this.proPropio = proPropio;
		this.extension = extension;
		this.proUnificado = proUnificado;
		this.nbc = nbc;
		this.modalidad = modalidad;
		this.jornada = jornada;
		this.resolucion = resolucion;
		this.sede = sede;
		this.convenio = convenio;
		this.estado = estado;
		this.nombre = nombre;
		this.calendario = calendario;
		this.horario = horario;
		this.fecha_creacion = fecha_creacion;
	}

	@Override
	public String toString() {
		return "Programa [codigo=" + codigo + ", uaa=" + uaa + ", nivelAcademico=" + nivelAcademico + ", registroIcfes="
				+ registroIcfes + ", registroSnies=" + registroSnies + ", proPropio=" + proPropio + ", extension="
				+ extension + ", proUnificado=" + proUnificado + ", nbc=" + nbc + ", modalidad=" + modalidad
				+ ", jornada=" + jornada + ", resolucion=" + resolucion + ", sede=" + sede + ", convenio=" + convenio
				+ ", estado=" + estado + ", nombre=" + nombre + ", calendario=" + calendario + ", horario=" + horario
				+ ", fecha_creacion=" + fecha_creacion + "]";
	}

	private static final long serialVersionUID = 1L;
}
