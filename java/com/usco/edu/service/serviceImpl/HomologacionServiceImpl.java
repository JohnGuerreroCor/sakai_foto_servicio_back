package com.usco.edu.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usco.edu.dao.IHomologacionDao;
import com.usco.edu.dao.IPlanDao;
import com.usco.edu.entities.Homologacion;
import com.usco.edu.entities.HomologacionTipo;
import com.usco.edu.entities.Plan;
import com.usco.edu.entities.SimulacionHomo;
import com.usco.edu.entities.Traslado;
import com.usco.edu.service.IHomologacionService;

@Service
public class HomologacionServiceImpl implements IHomologacionService{
	
	@Autowired
	private IHomologacionDao homologacionDao;
	
	@Autowired
	IPlanDao planDao;

	@Override
	public List<SimulacionHomo> SimularHomologaciones(String userdb, String est_codigo, int pla_old, int pla_new) {
		List<SimulacionHomo> simulacion = homologacionDao.SimularHomologaciones(userdb, est_codigo, pla_old, pla_new);
		//List<SimulacionHomo> simulacionveri = simulacion;
	       for(int i=0; i < simulacion.size() ; i++) {
	     	  if (i!= 0) {
	         	  if(simulacion.get(i-1).getCodigoPaaold() == simulacion.get(i).getCodigoPaaold()) {
	         		  for(int j=0 ; j< i-1; j++) {
	         				  if(simulacion.get(i-1).getCodigoPaanew() == simulacion.get(j).getCodigoPaanew()) {
	         					 simulacion.remove(i-1);
	         					 break;    					 
		         			  }
	         			  
	         		  }
	         		  
	         		 
	         	  }

	     	  }
	     	   
	        }
	       
	       for(int i=0; i < simulacion.size() ; i++) {
	      	  if (i!= 0) {
	          	  if(simulacion.get(i-1).getCodigoPaaold() == simulacion.get(i).getCodigoPaaold()) {         		      		  
	          		simulacion.remove(i);
	          	  }

	      	  }
	      	   
	         }
	       
	       for(int i=0; i < simulacion.size() ; i++) {
		      	  if (i!= 0) {
		      		  for(int  j =0 ; j< i ; j++) {
		      			 if (j!= 0) {
			      			  if(simulacion.get(i).getCodigoPaanew() == simulacion.get(j-1).getCodigoPaanew()) {         		      		  
			  	          		simulacion.remove(i);
			  	          	  }
		      			 }
		      		  }

		      	  }
		      	   
		         }
	       
	       
	       
		

		return simulacion;
	}

	@Override
	public List<HomologacionTipo> Homotipoall(String userdb) {
		return homologacionDao.Homotipoall(userdb);
	}

	@Override
	public boolean realizarHomologaciones(String userdb, String est_codigo, Homologacion homo) {
		List<SimulacionHomo> simulacion = homologacionDao.SimularHomologaciones(userdb, est_codigo, homo.getPlanold(), homo.getPlannew());
	       
	       if(!simulacion.isEmpty()) {
	    	  Plan plan = planDao.findByCodigo(homo.getPlannew(), userdb);
	    	  
	    	  if(plan.getPrograma().getCodigo() != null) {
	    		  
	    	      for(int i=0; i < simulacion.size() ; i++) {
	    	     	  if (i!= 0) {
	    	         	  if(simulacion.get(i-1).getCodigoPaaold() == simulacion.get(i).getCodigoPaaold()) {
	    	         		  for(int j=0 ; j< i-1; j++) {
	    	         				  if(simulacion.get(i-1).getCodigoPaanew() == simulacion.get(j).getCodigoPaanew()) {
	    	         					 simulacion.remove(i-1);
	    	         					 break;    					 
	    		         			  }
	    	         			  
	    	         		  }
	    	         		 
	    	         	  }

	    	     	  }
	    	     	   
	    	        }
	    	       
	    	       for(int i=0; i < simulacion.size() ; i++) {
	    	      	  if (i!= 0) {
	    	          	  if(simulacion.get(i-1).getCodigoPaaold() == simulacion.get(i).getCodigoPaaold()) {         		      		  
	    	          		simulacion.remove(i);
	    	          	  }

	    	      	  }
	    	      	   
	    	         }
	    	       
	    	       for(int i=0; i < simulacion.size() ; i++) {
	 		      	  if (i!= 0) {
	 		      		  for(int  j =0 ; j< i ; j++) {
	 		      			 if (j!= 0) {
	 			      			  if(simulacion.get(i).getCodigoPaanew() == simulacion.get(j-1).getCodigoPaanew()) {         		      		  
	 			  	          		simulacion.remove(i);
	 			  	          	  }
	 		      			 }
	 		      		  }

	 		      	  }
	 		      	   
	 		         }
	    	       
	    		   if(homologacionDao.insertPlanEstudiante(userdb, est_codigo,  homo.getPlannew(),  homo.getObservacion())) {
	    			  if( homologacionDao.desactivarPlanEstudiante(userdb, est_codigo, homo.getPlanold())) {
	    				  if(homologacionDao.actualizarProgramaenEstudiante(userdb, est_codigo, plan.getPrograma().getCodigo() )) {
	    					  for(int i=0; i < simulacion.size() ; i++) {
	    						  	if(!(homologacionDao.validarPaaRepetida(userdb, simulacion.get(i) ))) {
	    						  		homologacionDao.realizarHomologacionesEnMatriculaCurso(userdb, homo.getObservacion(), simulacion.get(i));
	    						  	}
					    	   }
					    	   
					    	   return true;
	    				  }
	    			  }
			    	
	    		   }
		    	
	    		  
	    	  }
	    
	    	   
	       }
	       
	       
		
		return false;
	}

	@Override
	public boolean crearHomologacion(String userdb, Homologacion homologacion) {
			//SE VALIDA SI LA HOMOLOGACION YA EXISTE
			if(homologacionDao.validarHomologacion(userdb, homologacion)) {
				return homologacionDao.activarHomologacion(userdb, homologacion);
			}else {
				String grupomax = homologacionDao.obtenergrupoHomo(userdb, homologacion);
				if(grupomax == null) {
					homologacion.setGrupo(1);
				}else {
					homologacion.setGrupo(Integer.parseInt(grupomax) + 1);
				}
				
				return homologacionDao.crearHomologacion(userdb, homologacion);
			}
	}

	@Override
	public boolean borrarHomologacion(String userdb, Homologacion homologacion) {
		return homologacionDao.borrarHomologacion(userdb, homologacion);
	}

	@Override
	public List<SimulacionHomo> SimularHomologacionesSinEst_codigo(String userdb, Homologacion homologacion) {
		return homologacionDao.SimularHomologacionesSinEst_codigo(userdb, homologacion);
	}

	@Override
	public boolean validarHomologacion(String userdb, Homologacion homologacion) {
		return homologacionDao.validarHomologacion(userdb, homologacion);
	}

	@Override
	public List<SimulacionHomo> SimularTrasladoAsignaturas(String userdb, String est_codigo, int pla_old, int pla_new) {
		return homologacionDao.SimularTrasladoAsignaturas(userdb, est_codigo, pla_old, pla_new);
	}

	@Override
	public boolean realizarTraslados(String userdb, String est_codigo, Homologacion homo) {
		List<SimulacionHomo> simulacion = homologacionDao.SimularTrasladoAsignaturas(userdb, est_codigo, homo.getPlanold(), homo.getPlannew());
		  Plan plan = planDao.findByCodigo(homo.getPlannew(), userdb);
		  
	       if(!simulacion.isEmpty()) {
	    	   if(plan.getPrograma().getCodigo() != null) {
				   if(homologacionDao.insertPlanEstudiante(userdb, est_codigo,  homo.getPlannew(),  homo.getObservacion())) {
					  if( homologacionDao.desactivarPlanEstudiante(userdb, est_codigo, homo.getPlanold())) {
						  if(homologacionDao.actualizarProgramaenEstudiante(userdb, est_codigo, plan.getPrograma().getCodigo() )) {
							  for(int i=0; i < simulacion.size() ; i++) {
								  	if(!(homologacionDao.validarPaaRepetida(userdb, simulacion.get(i) ))) {
								  		homologacionDao.realizarHomologacionesEnMatriculaCurso(userdb, homo.getObservacion(), simulacion.get(i));
								  	}
					    	   }
					    	   
					    	   return true;
						  }
					  }
			    	
				   }
				   
	    	   }
	       }
		return false;
	}

	@Override
	public List<Traslado> AsignaturasforTraslado(String userdb, Homologacion homologacion) {
		return homologacionDao.AsignaturasforTraslado(userdb, homologacion);
	}

	@Override
	public boolean crearTraslado(String userdb, Homologacion homologacion) {
		
		List<Traslado> traslado = homologacionDao.AsignaturasforTraslado(userdb, homologacion);
		
		for(Traslado tras:traslado) {
			
			if(tras.getHot_nombre() == null && tras.getAplica().equals("si")) {
				System.out.println("entramos");
				tras.setPlannew(""+homologacion.getPlannew());
				tras.setPlanold(""+homologacion.getPlanold());
				
				if(!homologacionDao.validarTraslado(userdb, tras)) {
					String grupomax = homologacionDao.obtenergrupoTraslado(userdb, tras);
					if(grupomax == null) {
						tras.setGrupo(1);
					}else {
						tras.setGrupo(Integer.parseInt(grupomax) + 1);
					}
					
					try {
						tras.setHot_codigo(homologacion.getHomologacionTipo().getCodigo());
					 homologacionDao.crearTraslado(userdb, tras);
					}catch(Exception e) {
						return false;
					}
				}
			}
			
		}
		
		return true;

	}

}
