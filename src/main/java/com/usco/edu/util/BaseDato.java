package com.usco.edu.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class BaseDato {

    Logger log = Logger.getLogger(getClass().getName());

    // MÉTODO PARA OBTENER UNA CONEXIÓN A LA BASE DE DATOS
    public Connection getConexion() {
        Connection conexion = null;
        try {
            // INICIALIZAR EL CONTEXTO DE JNDI
            Context ctx = new InitialContext();

            DataSource dataSource = null;

            // DESCOMENTAR LA LÍNEA 31 PARA EJECUTAR EN LOCAL Y COMENTAR LA LÍNEA 34
            dataSource = (DataSource) ctx.lookup("jboss/datasources/ConsultaDS");

            // DESCOMENTAR LA LÍNEA 34 PARA COMPILAR PARA PRODUCCIÓN Y COMENTAR LA LÍNEA 31
            // dataSource = (DataSource) ctx.lookup("java:jboss/datasources/DatasourceConsultaCreadoPorDBA");

            // OBTENER LA CONEXIÓN DESDE EL DATASOURCE
            conexion = dataSource.getConnection();
        } catch (Exception e) {
            // CAPTURAR CUALQUIER EXCEPCIÓN Y REGISTRARLA
            StringWriter stack = new StringWriter();
            e.printStackTrace(new PrintWriter(stack));
            log.error("getConexion() - ERROR: " + stack.toString());
        }
        return conexion;
    }

    // MÉTODO PARA CERRAR UNA CONEXIÓN A LA BASE DE DATOS
    public void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                // CAPTURAR CUALQUIER EXCEPCIÓN AL CERRAR LA CONEXIÓN Y REGISTRARLA
                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                log.error("cerrarConexion(Connection) - ERROR: " + stack.toString());
            }
        }
    }

    // MÉTODO PARA CERRAR UN PREPAREDSTATEMENT
    public void cerrarConexion(PreparedStatement sentencia) {
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                // CAPTURAR CUALQUIER EXCEPCIÓN AL CERRAR EL PREPAREDSTATEMENT Y REGISTRARLA
                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                log.error("cerrarConexion(PreparedStatement) - ERROR: " + stack.toString());
            }
        }
    }

    // MÉTODO PARA CERRAR UN STATEMENT
    public void cerrarConexion(Statement sentencia) {
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                // CAPTURAR CUALQUIER EXCEPCIÓN AL CERRAR EL STATEMENT Y REGISTRARLA
                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                log.error("cerrarConexion(Statement) - ERROR: " + stack.toString());
            }
        }
    }

    // MÉTODO PARA CERRAR UN RESULTSET
    public void cerrarConexion(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // CAPTURAR CUALQUIER EXCEPCIÓN AL CERRAR EL RESULTSET Y REGISTRARLA
                StringWriter stack = new StringWriter();
                e.printStackTrace(new PrintWriter(stack));
                log.error("cerrarConexion(ResultSet) - ERROR: " + stack.toString());
            }
        }
    }
}
