package com.usco.edu.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class SegundaClave {
	
	private Map<String,String> mapClave = new HashMap<>();
	
	private Map<String,String> mapUser = new HashMap<>();
	
	public void setClave(String usuario , String clave) {
		mapClave.put(usuario, clave);
	}
	
	public String getClave(String usuario) {
		String clave = mapClave.get(usuario);
		if(clave == null)throw new NullPointerException("No tiene segunda clave en sesion");
		return clave;
	}
	
	public void setUser(String usuario , String user) {
		mapUser.put(usuario, user);
	}
	
	public String getUser(String usuario) {
		String user = mapUser.get(usuario);
		if(user == null)throw new NullPointerException("No tiene usuario en sesion");
		return user;
	}
}
