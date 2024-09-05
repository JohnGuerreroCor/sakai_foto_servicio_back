package com.usco.edu.restController;


import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/obtenerFoto")
public class FotoRestController {
	
	@RequestMapping(value = "{codigo}", method = RequestMethod.GET)
	public @ResponseBody void obtenerFoto(HttpServletResponse response, @PathVariable("codigo") String codigo)
			throws IOException {
		if (codigo == null) {
			response.sendError(401);
		} else {
			response.setContentType("image/png; image/jpg");
			try {
				CloseableHttpClient httpclient = HttpClients.createDefault();
				HttpGet get = new HttpGet("https://gaitana.usco.edu.co/porteria_movil/Show?image=" + codigo);
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
		}
	}

}
