package br.com.spei.bibliotecatrm5.mvc.model;

public class TipoObra {

	private int codTipoObra;
	private String descricaoTipoObra;
	private boolean dicionario;
	private boolean enciclopedia;
	private boolean periodico;
	
	public int getCodTipoObra() {
		return codTipoObra;
	}
	public void setCodTipoObra(int codTipoObra) {
		this.codTipoObra = codTipoObra;
	}
	public String getDescricaoTipoObra() {
		return descricaoTipoObra;
	}
	public void setDescricaoTipoObra(String descricaoTipoObra) {
		this.descricaoTipoObra = descricaoTipoObra;
	}
	public boolean isDicionario() {
		return dicionario;
	}
	public void setDicionario(boolean dicionario) {
		this.dicionario = dicionario;
	}
	public boolean isEnciclopedia() {
		return enciclopedia;
	}
	public void setEnciclopedia(boolean enciclopedia) {
		this.enciclopedia = enciclopedia;
	}
	public boolean isPeriodico() {
		return periodico;
	}
	public void setPeriodico(boolean periodico) {
		this.periodico = periodico;
	}
	
}
