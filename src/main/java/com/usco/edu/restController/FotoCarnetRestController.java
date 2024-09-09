package com.usco.edu.restController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.dto.Foto;
import com.usco.edu.service.IFotoService;

@RestController
@RequestMapping(path = "foto")
public class FotoCarnetRestController {
	
	@Autowired
	private IFotoService service;
	
	@GetMapping("/{codigo}")
	public void obtenerFoto(@PathVariable String codigo, HttpServletResponse response) throws Exception {
	    if (codigo == null) {
	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	    } else {
	        // Determina el tipo de código
	        if (codigo.startsWith("u")) {
	            // Es un código estudiantil
	        	String valor = codigo.replaceAll("[^0-9]", "");
	            procesarCodigoEstudiantil(service.obtenerPercodigoEstudiante(valor), response);
	        } else if (codigo.matches("\\d+")) {
	            procesarCedulaDocente(service.obtenerPercodigoDocente(codigo), response);
	        } else {
	            // Código inválido
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Código inválido");
	        }
	    }
	}

	private static final byte[] DEFAULT_BYTES = {1, 2, 3, 4};

	private void procesarCodigoEstudiantil(String codigoEstudiante, HttpServletResponse response) throws IOException {
	    try {
	    	
	        ByteArrayInputStream stream = service.mirarFoto(codigoEstudiante, response);
	        
	        if (isDefaultStream(stream)) {
	        	System.out.println(isDefaultStream(stream));
	            response.setContentType("image/png; image/jpg");
	            try {
	                CloseableHttpClient httpclient = HttpClients.createDefault();
	                HttpGet get = new HttpGet("https://gaitana.usco.edu.co/porteria_movil/Show?image=" + codigoEstudiante);
	                get.setHeader("Authorization", "Basic MktqaDlDNGNzbV4/a1ZaQlM0JVM6SEh3UkZjNXU2Q21TMHdtP0c2Qns=");

	                CloseableHttpResponse respo = httpclient.execute(get);
	                System.out.println("PASA POR PRIMER TRY");
	                try {
	                    HttpEntity entity = respo.getEntity();

	                    BufferedImage bi = ImageIO.read(entity.getContent());
	                    OutputStream out = response.getOutputStream();

	                    ImageIO.write(bi, "png", out);
	                    out.close();

	                    EntityUtils.consume(entity);
	                    System.out.println("PASA POR SEGUNDO TRY");
	                } finally {
	                	System.out.println("PASA POR FINALLY");
	                    respo.close();
	                }
	            } catch (IOException e) {
	            	System.out.println("PASA POR CATCH");
	                e.printStackTrace();
	            }
	        } else {
	        		System.out.println("PASA POR FOTO NUEVA"+isDefaultStream(stream));
	        	 	response.setContentType("image/png");
	        	    OutputStream out = response.getOutputStream();
	        	    BufferedImage bi = ImageIO.read(service.mirarFoto(codigoEstudiante, response));
	        	    ImageIO.write(bi, "png", out);
	        	    out.close();
	        }
	    } catch (Exception e) {
	    	ByteArrayInputStream stream = service.mirarFoto(codigoEstudiante, response);
    	 	response.setContentType("image/png");
    	    OutputStream out = response.getOutputStream();
    	    BufferedImage bi = ImageIO.read(stream);
    	    ImageIO.write(bi, "png", out);
    	    out.close();
	        // En caso de error, llama a la función alternativa
	    }
	}

	private boolean isDefaultStream(ByteArrayInputStream stream) {
	    try {
	        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	        int nRead;
	        byte[] data = new byte[1024]; // Un buffer temporal para leer

	        // Lee el contenido del stream y lo escribe en el buffer
	        while ((nRead = stream.read(data, 0, data.length)) != -1) {
	            buffer.write(data, 0, nRead);
	        }

	        buffer.flush(); // Asegúrate de que todos los datos se han escrito al buffer

	        // Compara los bytes leídos con los valores por defecto
	        return Arrays.equals(buffer.toByteArray(), DEFAULT_BYTES);
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	private void procesarCedulaDocente(String cedulaDocente, HttpServletResponse response) throws IOException {
		try {
	        response.setContentType("image/png");
	        ByteArrayInputStream stream = service.mirarFoto(cedulaDocente, response);
	        
	        if (isDefaultStream(stream)) {
	        	response.setContentType("image/png; image/jpg");
				try {
					CloseableHttpClient httpclient = HttpClients.createDefault();
					HttpGet get = new HttpGet("https://gaitana.usco.edu.co/porteria_movil/Show?image=" + cedulaDocente);
					get.setHeader("Authorization", "Basic MktqaDlDNGNzbV4/a1ZaQlM0JVM6SEh3UkZjNXU2Q21TMHdtP0c2Qns=");

					CloseableHttpResponse respo = httpclient.execute(get);
					try {
						HttpEntity entity = respo.getEntity();

						BufferedImage bi = ImageIO.read(entity.getContent());
						OutputStream out = response.getOutputStream();

						ImageIO.write(bi, "png", out);
						out.close();

						EntityUtils.consume(entity);
					} finally {
						respo.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
	        } else {
	            OutputStream out = response.getOutputStream();
	            BufferedImage bi = ImageIO.read(service.mirarFoto(cedulaDocente, response));
	            ImageIO.write(bi, "png", out);
	            out.close();
	        }
	    } catch (Exception e) {
	    	ByteArrayInputStream stream = service.mirarFoto(cedulaDocente, response);
	    	OutputStream out = response.getOutputStream();
            BufferedImage bi = ImageIO.read(stream);
            ImageIO.write(bi, "png", out);
            out.close();
	        
	    }
	}
	
	@GetMapping(path = "obtener-foto-antigua/{codigo}")
	public Foto fotoAntigua(@PathVariable String codigo) throws Exception{
		return service.mirarFotoAntigua(codigo);
	}
	
}
