package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@Configuration
@EnableTransactionManagement
@Component
public class AppConfig {
	
	@Value("${spring.profiles.active}")
	private String perfilSeleccionado;

	@Value("${spring.datasource.url}")
	private String datasourceLocal;
	@Autowired
	private DataSource dataSource;
	
	
	@Bean(name = "JDBCTemplatePlanesConsulta")
	public JdbcTemplate jdbcTemplateConsultasjdbc() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	
	@Bean(name = "JDBCTemplatePlanesLogin")
	public JdbcTemplate jdbcTemplateLogin() throws Exception {

		DataSource dataSource = null;

		if (perfilSeleccionado.equals("local")) {

//			DriverManagerDataSource dataSourceM = new DriverManagerDataSource();
//			dataSourceM.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			dataSourceM.setUrl(datasourceLocal);
////
//			dataSource = dataSourceM;

			dataSource = (DataSource) new JndiTemplate().lookup("jboss/datasources/academia3000_jankarlos");

		} else if (perfilSeleccionado.equals("test") || perfilSeleccionado.equals("produccion")) {

			dataSource = (DataSource) new JndiTemplate().lookup("java:jboss/datasources/encuestas_admon_loginDS");

		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	@Bean(name = "NamedJDBCTemplateEncuestasConsulta")
	public NamedParameterJdbcTemplate jdbcTemplateConsulta() throws Exception {

		return new NamedParameterJdbcTemplate(dataSource);
	}
	

}
