package com.usco.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.usco.edu.entities.NormaAcreditacion;

public interface INormaAcreditacionService {

	List<NormaAcreditacion> find(String userdb);

	List<NormaAcreditacion> findbyId(int codigo, String userdb);

	void create(NormaAcreditacion na, String userdb);

	void update(NormaAcreditacion na, String userdb);

	String  subirPdf(MultipartFile file, Long perCodigo, int uaa, String user, HttpServletRequest request);

}
