package br.com.spei.bibliotecatrm5.mvc.model;

public class Emprestimo {
	
	private int idUsuario;
	private String matUsuario;
	private String nomeUsuario;
	private String catUsuario;
	private String dtEmprestimo;
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getMatUsuario() {
		return matUsuario;
	}
	public void setMatUsuario(String matUsuario) {
		this.matUsuario = matUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getCatUsuario() {
		return catUsuario;
	}
	public void setCatUsuario(String catUsuario) {
		this.catUsuario = catUsuario;
	}
	public String getDtEmprestimo() {
		return dtEmprestimo;
	}
	public void setDtEmprestimo(String dtEmprestimo) {
		this.dtEmprestimo = dtEmprestimo;
	}
	
	
	

}
