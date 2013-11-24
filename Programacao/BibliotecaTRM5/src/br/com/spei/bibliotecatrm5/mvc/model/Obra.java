package br.com.spei.bibliotecatrm5.mvc.model;

public class Obra {
	private int idObra;
	private String nomeObra;
	private Autor autor;
	private int ano;
	private Editora editora;
	private TipoObra tipoObra;
	private boolean classico;
	
	public Obra() {
		autor = new Autor();
		editora = new Editora();
		tipoObra = new TipoObra();
	}
	
	public int getIdObra() {
		return idObra;
	}
	public void setIdObra(int idObra) {
		this.idObra = idObra;
	}
	public Autor getAutor() {
		return autor;
	}
	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public Editora getEditora() {
		return editora;
	}
	public void setEditora(Editora editora) {
		this.editora = editora;
	}
	public TipoObra getTipoObra() {
		return tipoObra;
	}
	public void setTipoObra(TipoObra tipoObra) {
		this.tipoObra = tipoObra;
	}
	public String getNomeObra() {
		return nomeObra;
	}
	public void setNomeObra(String nomeObra) {
		this.nomeObra = nomeObra;
	}
	public boolean isClassico() {
		return classico;
	}
	public void setClassico(boolean classico) {
		this.classico = classico;
	}
}
