package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import br.com.spei.bibliotecatrm5.util.ConfigurationFile;

public class Conexao {
	// TODO Configurar externamente
	private String connection = "jdbc:sqlserver://{SERVER};database={DATABASE};user={USER};password={PWD}";
	private static Conexao instance; 
	
	private Conexao() {
		
	}
	
	public static Conexao getInstance() {
		if(instance == null)
		{
			instance = new Conexao();
			instance.configuraStringConexao();
		}
		
		return instance;
	}
	
	private void configuraStringConexao() {
		ConfigurationFile configurador = new ConfigurationFile();
		Properties propriedades = configurador.obtemPropriedadesArquivoConfiguracao();
		String servidor = propriedades.getProperty("SERVER");
		String banco = propriedades.getProperty("DB");
		String usuario = propriedades.getProperty("USER");
		// TODO Descriptografar senha
		String senha = propriedades.getProperty("PWD");
		
		instance.connection = instance.connection.
								replace("{SERVER}", servidor).
								replace("{DATABASE}", banco).
								replace("{USER}", usuario).
								replace("{PWD}", senha);
	}

	public Connection getConnection() throws SQLException {		
		return DriverManager.getConnection(connection);
	}
}
