package br.com.spei.bibliotecatrm5.mvc.model;

public class Reserva {
	
	private Usuario usuario;
	private Obra obra;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	
}
