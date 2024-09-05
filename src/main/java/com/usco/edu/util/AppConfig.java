package com.usco.edu.util;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
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
	
	
	@Bean(name = "JDBCTemplateConsulta")
	public JdbcTemplate jdbcTemplateConsultasjdbc() throws Exception {
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	
	@Bean(name = "JDBCTemplateLogin")
	public JdbcTemplate jdbcTemplateLogin() throws Exception {

		DataSource dataSource = null;

		if (perfilSeleccionado.equals("local")) {

			dataSource = (DataSource) new JndiTemplate().lookup("jboss/datasources/LoginDS");

		} else if (perfilSeleccionado.equals("test") || perfilSeleccionado.equals("produccion")) {

			dataSource = (DataSource) new JndiTemplate().lookup("java:jboss/datasources/DatasourceLogueoCreadoPorDBA");

		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}
	
	@Bean(name = "JDBCTemplateEjecucion")
	public JdbcTemplate jdbcTemplateEjecucion() throws Exception {

		DataSource dataSource = null;

		if (perfilSeleccionado.equals("local")) {

			dataSource = (DataSource) new JndiTemplate().lookup("jboss/datasources/EjecucionDS");

		} else if (perfilSeleccionado.equals("test") || perfilSeleccionado.equals("produccion")) {

			dataSource = (DataSource) new JndiTemplate().lookup("java:jboss/datasources/DatasourceEjecucionCreadoPorDBA");

		}
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);

		return jdbcTemplate;
	}

}
