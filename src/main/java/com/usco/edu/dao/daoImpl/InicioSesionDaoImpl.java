package com.usco.edu.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.usco.edu.dao.IInicioSesionDao;

@Repository
public class InicioSesionDaoImpl implements IInicioSesionDao {

    @Autowired
    @Qualifier("JDBCTemplateConsulta")
    public JdbcTemplate jdbcTemplateConsulta;

    @Autowired
    @Qualifier("JDBCTemplateLogin")
    public JdbcTemplate jdbcTemplateLogin;

    @Override
    public String obtenerTokenInicioSesion(String atributos, String userdb) {
        // OBTENER EL TOKEN DE INICIO DE SESIÓN SEGÚN LOS ATRIBUTOS Y EL USUARIO
        String token = null;
        try {
            token = jdbcTemplateConsulta.queryForObject("SELECT token.getTokenInicioSesion(?)", new Object[] { atributos }, String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public String urlTokenPeticion(String userdb) {
        // OBTENER LA URL DEL TOKEN DE PETICIÓN
        String url = null;
        try {
            url = jdbcTemplateConsulta.queryForObject("SELECT wep_valor FROM dbo.web_parametro wp where wp.wep_codigo = 377", String.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;
    }

    @Override
    public String obtenerSegundaClaveReal(String segundaClave) {
        // OBTENER LA SEGUNDA CLAVE REAL ENCRIPTADA
        String sql = "SELECT dbo.encriptarClaveReal(?) as clave";
        String claveReal = jdbcTemplateLogin.queryForObject(sql, new Object[] { segundaClave }, new RowMapper<String>() {

            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("clave");
            }
        });
        return claveReal;
    }
}
