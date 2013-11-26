package br.com.spei.bibliotecatrm5.mvc.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Emprestimo {
	
	private int idEmprestimo;
	private Usuario usuario;
	private List<Exemplar> exemplares;
	private Date dataEmprestimo;

	public Emprestimo() {
		exemplares = new ArrayList<>();
	}
	
	public int getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(int idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Exemplar> getExemplares() {
		return exemplares;
	}
	public void setExemplares(List<Exemplar> exemplares) {
		this.exemplares = exemplares;
	}
	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}
}
