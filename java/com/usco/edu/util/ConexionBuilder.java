package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
@PropertySource("classpath:application.properties")
public class ConexionBuilder {
	
	@Value("${spring.datasource.url}")
	private String urlProperties;
	
	@Value("${spring.datasource.driver-class-name}")
	private String driverProperties;
	
	private DataSource dataSource;
	
	public DataSource construir(String usuario , String clave) {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName(driverProperties);
		dataSourceBuilder.url(buildUrl(usuario,clave));
		
		dataSource = dataSourceBuilder.build();
		return dataSource;
		/* return DataSourceBuilder.create()
	              .driverClassName(driverProperties)
	              .url(urlProperties)
	              .username(usuario)
	              .password(clave)
	              .build();*/
	}
	
	private String buildUrl(String usuario , String clave) {
		String urlDivision[] = urlProperties.split(";");
		StringBuilder urlBuilder = new StringBuilder();
		
		for(int i = 0 ; i < urlDivision.length ; i++) {
			String cadena = urlDivision[i];
			if(cadena.contains("user=")) {
				cadena ="user= "+ usuario ;
			}else if(cadena.contains("password=")) {
				cadena = "password=" + clave;
			}
			
			urlBuilder.append(cadena).append(";");
		}
		urlBuilder.deleteCharAt(urlBuilder.length() -1);
		System.out.println(urlBuilder.toString());
		return urlBuilder.toString();
	}
}
