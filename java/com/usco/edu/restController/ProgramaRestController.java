package com.usco.edu.restController;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.usco.edu.entities.Convenio;
import com.usco.edu.entities.Estado;
import com.usco.edu.entities.Jornada;
import com.usco.edu.entities.Modalidad;
import com.usco.edu.entities.NivelAcademico;
import com.usco.edu.entities.Programa;
import com.usco.edu.entities.Sede;
import com.usco.edu.entities.Snies_nbc;
import com.usco.edu.service.IProgramaService;
import com.usco.edu.service.ISedeService;
import com.usco.edu.util.BaseDato;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

//@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
public class ProgramaRestController {

	@Autowired
	IProgramaService programaService;

	@Autowired
	ISedeService sedeService;

	@Autowired
	private DataSource dataSource;

	@GetMapping("/programas/sede/{codigo}/{userdb}")
	public List<Programa> programasBySede(@PathVariable("codigo") Long codigo, @PathVariable("userdb") String userdb) {

		List<Programa> programa = programaService.progromasBySede(codigo, userdb);
		for (Programa pro : programa) {
			pro.setNombre(pro.getUaa().getNombre());
		}
		return programa;
	}

	@GetMapping("/programas/all/{userdb}")
	public List<Programa> programasAll(@PathVariable("userdb") String userdb) {

		List<Programa> programa = programaService.progromasAll(userdb);
		for (Programa pro : programa) {
			pro.setNombre(pro.getUaa().getNombre());
		}
		return programa;
	}

	@GetMapping("/programas/modalidad/{userdb}/{modalidad}")
	public List<Programa> programasbymodalidad(@PathVariable("userdb") String userdb,
			@PathVariable("modalidad") int modalidad) {

		List<Programa> programa = programaService.progromasbyModalidad(userdb, modalidad);
		for (Programa pro : programa) {
			pro.setNombre(pro.getUaa().getNombre());
		}
		return programa;
	}

	@GetMapping("/programas/nivelAcademico/{userdb}/{nivel}")
	public List<Programa> programasbyNivelAcademico(@PathVariable("userdb") String userdb,
			@PathVariable("nivel") int nivelAcademico) {

		List<Programa> programa = programaService.progromasbyNivelAcademico(userdb, nivelAcademico);
		for (Programa pro : programa) {
			pro.setNombre(pro.getUaa().getNombre());
		}
		return programa;
	}

	@GetMapping("/programas/total/{userdb}")
	public int programasTotal(@PathVariable("userdb") String userdb) {
		return programaService.totalprogramas(userdb);
	}

	@GetMapping("/programa/uaa/{uaaCodigo}/{userdb}")
	public Programa programaByUaa(@PathVariable("uaaCodigo") Long uaaCodigo, @PathVariable("userdb") String userdb) {
		return programaService.programaByUaa(uaaCodigo, userdb);
	}

	@GetMapping("/programa/{proCodigo}/{userdb}")
	public Programa programaById(@PathVariable("proCodigo") Long pro_codigo, @PathVariable("userdb") String userdb) {
		return programaService.findById(pro_codigo, userdb);
	}

	@GetMapping("/programas/uaa/{uaaCodigo}/{userdb}")
	public List<Programa> programasByUaa(@PathVariable("uaaCodigo") Long uaaCodigo,
			@PathVariable("userdb") String userdb) {
		List<Programa> programas = new ArrayList<Programa>();
		programas.add(programaService.programaByUaa(uaaCodigo, userdb));
		return programas;
	}

	@GetMapping("/programas/snies/{sniesCodigo}/{userdb}")
	public List<Programa> programasBySnies(@PathVariable("sniesCodigo") String sniesCodigo,
			@PathVariable("userdb") String userdb) {
		return programaService.programabySnies(sniesCodigo, userdb);
	}

	@GetMapping("/api/sedes")
	public List<Sede> sedefindAll() {
		return sedeService.findAll();
	}

	@GetMapping("/api/modalidad/{userdb}")
	public List<Modalidad> modalidadfindAll(@PathVariable("userdb") String userdb) {
		return programaService.modalidadAll(userdb);
	}

	@GetMapping("/api/jornadas/{userdb}")
	public List<Jornada> jornadafindAll(@PathVariable("userdb") String userdb) {
		return programaService.jornadasAll(userdb);
	}

	@GetMapping("/api/nivelacademicos/{userdb}")
	public List<NivelAcademico> nivelAcademicofindAll(@PathVariable("userdb") String userdb) {
		return programaService.nivelAcademicoAll(userdb);
	}

	@GetMapping("/api/convenios/{userdb}")
	public List<Convenio> conveniofindAll(@PathVariable("userdb") String userdb) {
		return programaService.convenioAll(userdb);
	}

	@GetMapping("/api/programa/estados/{userdb}")
	public List<Estado> estadosPrograma(@PathVariable("userdb") String userdb) {
		return programaService.estadosPrograma(userdb);
	}

	@GetMapping("/api/programa/nbc/{userdb}")
	public List<Snies_nbc> snies_nbcfindAll(@PathVariable("userdb") String userdb) {
		return programaService.nbcAll(userdb);
	}

//	@GetMapping("/certificado/{proCodigo}")
//	public void certificadoMatriculan(HttpServletRequest request, HttpServletResponse response,@PathVariable("proCodigo") int proCodigo) {
//			System.out.println("Entramos");
//			
//			Map<String, Object> parameters = new HashMap<String, Object>();
//			System.out.println(""+ proCodigo);
//			parameters.put("pro_codigo", proCodigo);
//			parameters.put("CONTEXT", request.getServletContext().getRealPath("/WEB-INF/classes/static/reportes/") + File.separator);
//		
//			BaseDato database = new BaseDato();
//			Connection connection = null;
//			try {
//			   	connection = database.getConexion();
//				
//				File file = new File(request.getServletContext().getRealPath("/WEB-INF/classes/static/reportes/hoja_vida_programa.jasper") + File.separator);
//				JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
//				byte[] bytes = JasperRunManager.runReportToPdf(reporte, parameters, connection);
//		
//				response.setContentType("application/pdf");
//				response.setContentLength(bytes.length);
//				
//				String headerKey = "Content-Disposition";
//				String headerValue = String.format("inline; filename=\"%s\"", "Hoja_vida_programa.pdf");
//	            response.setHeader(headerKey, headerValue);
//				
//	        	ServletOutputStream ouputStream = response.getOutputStream();
//				ouputStream.write(bytes, 0, bytes.length);
//				response.getOutputStream().flush();
//				response.getOutputStream().close();
//			} catch (Exception e) {
//				e.printStackTrace();
//				System.out.println("Error: " + e.toString());
//			} finally {
//				database.cerrarConexion(connection);
//			}
//		
//		
//	}

	@GetMapping("/certificado/{proCodigo}")
	public void reporteAsunto(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("proCodigo") Integer codigo) {

		Map<String, Object> parameters = new HashMap<String, Object>();

		parameters.put("pro_codigo", codigo);
		parameters.put("CONTEXT",
				request.getServletContext().getRealPath("/WEB-INF/classes/static/reportes") + File.separator);
		Connection connection = null;
		// HttpHeaders cabecera = new HttpHeaders();
		byte[] bytes = null;
		try {
			connection = dataSource.getConnection();
			File file = ResourceUtils
					.getFile(request.getServletContext().getRealPath("/WEB-INF/classes/static/reportes")
							+ File.separator + "hoja_vida_programa.jasper");
			JasperReport reporte = (JasperReport) JRLoader.loadObject(file);

			bytes = JasperRunManager.runReportToPdf(reporte, parameters, connection);
			System.out.println(bytes.toString());

			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);

			String headerKey = "Content-Disposition";
			String headerValue = String.format("inline; filename=\"%s\"", "hoja_vida_programa.pdf");
			response.setHeader(headerKey, headerValue);
			try {
				ServletOutputStream ouputStream = response.getOutputStream();
				ouputStream.write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.getOutputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Error: " + e.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		} finally {

		}

		// System.out.println(cabecera);
		// return new ResponseEntity<Resource>(cabecera, HttpStatus.OK);

	}

	@GetMapping("/certificadoexcel")
	public void certificadoExcel(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("Entramos");

		Map<String, Object> parameters = new HashMap<String, Object>();
		// System.out.println(""+ proCodigo);
		parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);

		BaseDato database = new BaseDato();
		Connection connection = null;
		try {
			connection = database.getConexion();

			File file = new File(request.getServletContext().getRealPath("/WEB-INF/classes/static/reportes")
					+ File.separator + "excel.jasper");
			JasperReport reporte = (JasperReport) JRLoader.loadObject(file);
			ByteArrayOutputStream xlsReport = new ByteArrayOutputStream();
			JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parameters, connection);
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(xlsReport));

			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(true);
			configuration.setDetectCellType(true);
			exporter.setConfiguration(configuration);
			exporter.exportReport();

			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(xlsReport.size());
			response.setHeader("Content-Disposition", "attachment; filename=grilla_programas.xlsx");
			OutputStream outputStream = response.getOutputStream();
			xlsReport.writeTo(outputStream);
			outputStream.flush();
			outputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error: " + e.toString());
		} finally {
			database.cerrarConexion(connection);
		}

	}

	@PutMapping("/programa/update/{username}")
	public ResponseEntity<?> updatePrograma(@Valid @RequestBody Programa programa, BindingResult result,
			@PathVariable("username") String username) {
		System.out.println(programa);
		Programa programaActual = programaService.findById(programa.getCodigo(), username);
		System.out.println("programa by id : " + programaActual);
		Map<String, Object> response = new HashMap<>();

		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("error", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		if (programaActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el programa ID: "
					.concat(programa.getCodigo() + (" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			programaActual.setCalendario(programa.getCalendario());
			programaActual.setConvenio(programa.getConvenio());
			programaActual.setEstado(programa.getEstado());
			programaActual.setExtension(programa.getExtension());
			programaActual.setHorario(programa.getHorario());
			programaActual.setJornada(programa.getJornada());
			programaActual.setModalidad(programa.getModalidad());
			programaActual.setNbc(programa.getNbc());
			programaActual.setNivelAcademico(programa.getNivelAcademico());
			programaActual.setNombre(programa.getNombre());
			programaActual.setProPropio(programa.getProPropio());
			programaActual.setRegistroIcfes(programa.getRegistroIcfes());
			programaActual.setRegistroSnies(programa.getRegistroSnies());
			programaActual.setResolucion(programa.getResolucion());
			programaActual.setSede(programa.getSede());
			programaActual.setUaa(programa.getUaa());
			programaActual.setProUnificado(programa.getProUnificado());
			programaActual.setCampoDetallado(programa.getCampoDetallado());

			programaService.updatePrograma(programaActual, username);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar El programa en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Programa se ha actualizado con éxito.");

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PostMapping("/programa/create/{username}")
	public ResponseEntity<?> create(@Valid @RequestBody Programa programa, BindingResult result,
			@PathVariable("username") String username) {
		Programa programaNew = new Programa();
		Map<String, Object> response = new HashMap<>();
		System.out.println("programa res :" + programa);

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		try {

			programaNew = programaService.newPrograma(programa, username);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El Programa ha sido creado con éxito.");
		response.put("programa", programaNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
