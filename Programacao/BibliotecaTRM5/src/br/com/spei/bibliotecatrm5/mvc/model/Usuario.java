package br.com.spei.bibliotecatrm5.mvc.model;

public class Usuario {

	private int codUsuario;
	private String nomeUsuario;
	private String sobrenomeUsuario;
	
	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSobrenomeUsuario() {
		return sobrenomeUsuario;
	}

	public void setSobrenomeUsuario(String sobrenomeUsuario) {
		this.sobrenomeUsuario = sobrenomeUsuario;
	}

	public int getCodUsuario() {
		return codUsuario;
	}
	
	public void setCodUsuario(int codUsuario) {
		this.codUsuario = codUsuario;
	}

}
