package br.com.spei.bibliotecatrm5.mvc.model;

import java.util.ArrayList;
import java.util.List;

public class Devolucao {
	
	private int codDevolucao;
	private Usuario usuario;
	private List<Exemplar> exemplares;
	
	public Devolucao() {
		this.exemplares = new ArrayList<>();
	}
	
	public int getCodDevolucao() {
		return codDevolucao;
	}
	public void setCodDevolucao(int codDevolucao) {
		this.codDevolucao = codDevolucao;
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
}
