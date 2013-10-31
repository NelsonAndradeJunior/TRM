package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	// TODO Configurar externamente
	private String connection = "jdbc:sqlserver://ZEUS;database=BANCO_CLIENTE;user=AulaJava;password=123";
	private static Conexao instance; 
	
	private Conexao() {
		
	}
	
	public static Conexao getInstance() {
		if(instance == null)
			instance = new Conexao();
		
		return instance;
	}
	
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(connection);
	}
}
