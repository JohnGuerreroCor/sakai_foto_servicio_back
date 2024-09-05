package com.usco.edu.service.serviceImpl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usco.edu.dao.IDocumentoDao;
import com.usco.edu.dao.INormaAcreditacionDao;
import com.usco.edu.dto.RespuestaSubirArchivo;
import com.usco.edu.entities.Documento;
import com.usco.edu.entities.NormaAcreditacion;
import com.usco.edu.feing.EnviarArchivoClient;
import com.usco.edu.service.INormaAcreditacionService;

@Service
public class NormaAcreditacionServiceImpl implements INormaAcreditacionService {

	@Autowired
	private INormaAcreditacionDao dao;

	@Autowired
	private IDocumentoDao docDao;

	@Autowired
	private EnviarArchivoClient enviarArchivo;

	@Override
	public List<NormaAcreditacion> find(String userdb) {
		// TODO Auto-generated method stub
		return dao.find(userdb);
	}

	@Override
	public List<NormaAcreditacion> findbyId(int codigo, String userdb) {
		// TODO Auto-generated method stub
		return dao.findbyId(codigo, userdb);
	}

	@Override
	public void create(NormaAcreditacion na, String userdb) {

		dao.create(na, userdb);

	}

	@Override
	public void update(NormaAcreditacion na, String userdb) {

		dao.update(na, userdb);

	}

	@Override
	public String subirPdf(MultipartFile file, Long perCodigo, int uaa, String user, HttpServletRequest request) {

		if (!file.isEmpty()) {

			if (isValido(file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".")))) {

				Documento documento = new Documento();
				documento.setDocNombreArchivo(file.getOriginalFilename());
				documento.setPerCodigo("" + perCodigo);
				documento.setDocCliente("CMS_VERSION:1.0");
				documento.setDocContenido("pdf_Noticia");
				documento.setDocExtension("pdf");
				documento.setDocIp(request.getRemoteAddr());
				documento.setDocSesion(request.getSession().getId());
				documento.setModCodigo(54);
				documento.setTdocCodigo(38);
				documento.setUaaCodigo(uaa);

				String Key = docDao.getKeyDocumento(
						documento.getModCodigo().toString() + documento.getUaaCodigo() + documento.getPerCodigo(),
						user);
				ObjectMapper objectMapper = new ObjectMapper();
				RespuestaSubirArchivo respuesta = new RespuestaSubirArchivo();
				try {
					respuesta = enviarArchivo.subirArchivo(file, Key, objectMapper.writeValueAsString(documento));
					System.out.println(respuesta.getMensaje());
					System.out.println(respuesta.getIdDocumento());

					if (!respuesta.isEstado()) {

						System.out.println("ocurrio un error");
					}

				} catch (Exception e) {

					System.out.println("ocurrio un error: " + e);
					return null;
				}
				System.out.println("Creado");
				return respuesta.getMensaje();
				
				
			} else {

				System.out.println("ocurrio un error");
				return null;
			}

		} else {
			System.out.println("ocurrio un error");
			return null;
		}

	}

	public boolean isValido(String nombre) {
		String expresion = "^([[a-zA-Z][0-9]_]{2,150})$";
		try {
			Pattern p = Pattern.compile(expresion);
			Matcher matcher = p.matcher(nombre);
			return matcher.matches();
		} catch (Exception e) {
			e.printStackTrace();
			return true;
		}

	}
}
