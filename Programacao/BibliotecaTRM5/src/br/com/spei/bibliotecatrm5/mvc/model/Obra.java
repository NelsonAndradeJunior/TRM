package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.Date;

public class Obra {
	
	private int idObra;
	private String autor;
	private Date dtFabricacao;
	private String editora;
	private String tipoObra;
	
	
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public Date getDtFabricacao() {
		return dtFabricacao;
	}
	public void setDtFabricacao(Date dtFabricacao) {
		this.dtFabricacao = dtFabricacao;
	}
	public String getEditora() {
		return editora;
	}
	public void setEditora(String editora) {
		this.editora = editora;
	}
	public String getTipoObra() {
		return tipoObra;
	}
	public void setTipoObra(String tipoObra) {
		this.tipoObra = tipoObra;
	}
	
		
	

}
