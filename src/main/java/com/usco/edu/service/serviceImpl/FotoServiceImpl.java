package com.usco.edu.service.serviceImpl;

import java.io.ByteArrayInputStream;
import java.util.Base64;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IFotoDao;
import com.usco.edu.dto.Foto;
import com.usco.edu.dto.RespuestaVerFoto;
import com.usco.edu.feing.EnviarFotoClient;
import com.usco.edu.service.IFotoService;


@Service
public class FotoServiceImpl implements IFotoService  {
	
	@Autowired
	private EnviarFotoClient enviarFotoClient;
	
	@Autowired
	private IFotoDao fotoCarnetDao;
	
	@Override
	public ByteArrayInputStream mirarFoto(String perCodigo, HttpServletResponse response) {
		
		byte[] array = {1, 2, 3, 4};

		String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo);

		RespuestaVerFoto respuesta = new RespuestaVerFoto();

		try {
			respuesta = enviarFotoClient.mostrarFoto(perCodigo, Key);

			byte[] fotoBytes = Base64.getDecoder().decode(respuesta.getBase64().split(",")[1]);
			return new ByteArrayInputStream(fotoBytes);

		} catch (Exception e) {
			System.out.println(e);
		}

		return new ByteArrayInputStream(array);
	}
	
	@Override
	public Foto mirarFotoAntigua(String perCodigo) {
		String aux = "";
		String Key = fotoCarnetDao.obtenerTokenFoto(perCodigo);
		aux = enviarFotoClient.mostrarFotoAntigua(perCodigo, Key).toString();
		Foto foto = new Foto();
		foto.setUrl(aux);
		return foto;
	}

	@Override
	public String obtenerPercodigoEstudiante(String codigo) {
		return fotoCarnetDao.obtenerPercodigoEstudiante(codigo);
	}

	@Override
	public String obtenerPercodigoDocente(String codigo) {
		return fotoCarnetDao.obtenerPercodigoDocente(codigo);
	}

}
