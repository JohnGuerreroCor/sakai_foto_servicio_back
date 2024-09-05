package com.usco.edu.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = "dbo.usuario_matricula_posgrados_admin_login")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "uid")
	private int id;

	@Column(name = "us",unique = true)
	private String username;

	@Column(name="uwd2")
	private String password;
	
	@Column(name="sys")
	private String userdb;
	
	@Column(name = "state")
	private boolean state;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "uaa_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Uaa uaa;
	
	@OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "per_codigo")
	@NotFound(action = NotFoundAction.IGNORE)
	private Persona persona;

	@Column(name="gru_id")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public Uaa getUaa() {
		return uaa;
	}

	public void setUaa(Uaa uaa) {
		this.uaa = uaa;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public String getUserdb() {
		return userdb;
	}

	public void setUserdb(String userdb) {
		this.userdb = userdb;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", username=" + username + ", password=" + password + ", userdb=" + userdb
				+ ", state=" + state + ", uaa=" + uaa + ", persona=" + persona + ", role=" + role + "]";
	}


	private static final long serialVersionUID = 1L;

}
