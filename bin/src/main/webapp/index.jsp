<%@page import="com.usco.edu.util.BaseDato"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.io.File"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import ="org.springframework.web.servlet.ModelAndView"%>


<!DOCTYPE html>
<html lang="es">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Reporte Hoja de vida - Programa</title>
	</head>
	<body>
		<%
		/*
		int programa = (int) request.getSession().getAttribute("programa");
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		System.out.println(""+ programa);
		parameters.put("pro_codigo", programa);
		//parameters.put("SUBREPORT_DIR", this.getServletContext().getRealPath("/") + "reportes/programa/detalle/Competencias/");
		//parameters.put("CONTEXT", this.getServletContext().getRealPath("/") + "reportes/");
		
		BaseDato database = new BaseDato();
		Connection connection = null;
		try {
		   	connection = database.getConexion();
			
			File file = new File(getServletContext().getRealPath("hoja_vida_programa.jasper"));
			JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
			byte[] bytes = JasperRunManager.runReportToPdf(reporte, parameters, connection);
	
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			
			String headerKey = "Content-Disposition";
			String headerValue = String.format("inline; filename=\"%s\"", "Hoja_vida_programa.pdf");
            response.setHeader(headerKey, headerValue);
			
        	ServletOutputStream ouputStream = response.getOutputStream();
			ouputStream.write(bytes, 0, bytes.length);
			response.getOutputStream().flush();
			response.getOutputStream().close();
            out.clear(); // where out is a JspWriter
            out = pageContext.pushBody();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		} finally {
			database.cerrarConexion(connection);
		}*/
		%>
	</body>
</html>
