package br.com.spei.bibliotecatrm5.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationFile {

	private Properties properties;
	
	public ConfigurationFile() {
		properties = new Properties();
	}
	
	public void geraArquivoDeConfiguracao() {
		try {
			properties.setProperty("SERVER", "PC\\SQLEXPRESS");
			properties.setProperty("DB", "BIBLIOTECATRM");
			properties.setProperty("USER", "THIAGO");
			properties.setProperty("PWD", "123456");
			
			properties.store(new FileOutputStream("dbConfig.properties"), null);
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public Properties obtemPropriedadesArquivoConfiguracao() {
		try {
			properties.load(new FileInputStream("dbConfig.properties"));
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		
		return properties;
	}
}
