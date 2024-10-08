package com.usco.edu.restController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class RutasRestController {
	
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView mainController(HttpServletRequest request) {
			ModelAndView model = new ModelAndView();
			model.setViewName("redirect:/login");
		return model;
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView loginControllerc(HttpServletRequest request,
			@RequestParam(value = "error", required = false) String error) {
			ModelAndView model = new ModelAndView();
			model.setViewName("index.html");
		return model;
	}
	
	
	@RequestMapping(value = "/token", method = RequestMethod.GET)
	@PreAuthorize("permitAll")
	public @ResponseBody ModelAndView tokenController(HttpServletRequest request) {
			ModelAndView model = new ModelAndView();
			model.setViewName("index.html");
		return model;
	}
	
	
	@GetMapping("/logo")
	public ResponseEntity<byte[]> getImage(HttpServletRequest request) throws IOException{
		String ubicacionImg = request.getServletContext().getRealPath("/WEB-INF/classes/static/assets")
				+ File.separator + "USCO.png";
		File img = new File(ubicacionImg);
	    return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
	}
	


}
