package com.usco.edu.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Conexion {

	public static boolean validarConexion(String driver, String url, String clave, String usuario) throws SQLException {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, usuario, clave);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if (con != null) {
				con.close();
			}
		}
	}
	

	
	public static void cerrarConexion(Connection conexion) {
		if (conexion != null) {
			try {
				conexion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void cerrarConexion(PreparedStatement sentencia) {
		if (sentencia != null) {
			try {
				sentencia.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
