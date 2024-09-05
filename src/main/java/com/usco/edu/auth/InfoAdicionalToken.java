package com.usco.edu.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import com.usco.edu.entities.Usuario;
import com.usco.edu.service.serviceImpl.UsuarioServiceImpl;

@Component
public class InfoAdicionalToken implements TokenEnhancer {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        // OBTENER INFORMACIÓN ADICIONAL DEL USUARIO Y AGREGARLA AL TOKEN
        Usuario usuario = usuarioService.buscarUsuario(authentication.getName());
        Map<String, Object> info = new HashMap<>();
        info.put("Informacion Token Inicio Sesion", "Campos Necesarios ".concat(authentication.getName()));
        info.put("personaCodigo", usuario.getPersona().getCodigo());
        info.put("personaNombre", usuario.getPersona().getNombre());
        info.put("personaApellido", usuario.getPersona().getApellido());
        info.put("horaInicioSesion", usuario.getHoraInicioSesion());
        
		//LAS VARIABLES ANTERIORES SE CARGAN EN EL TOKEN DE INICIO DE SESIÓN PARA IMPLEMENTAR DENTRO DEL APLICATIVO
		//AGREGAR LAS NECESARIAS SEGÚN LA FUNCIONALIDAD A IMPLEMENTAR, TENER EN CUENTA MODIFICAR LA ENTIDAD Y ROWMAPPER PARA ELLO

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);

        return accessToken;
    }
}
