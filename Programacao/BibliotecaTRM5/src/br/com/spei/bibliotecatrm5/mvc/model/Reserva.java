package br.com.spei.bibliotecatrm5.mvc.model;

public class Reserva {
	
	private int idReserva;
	private String catUsuario;
	private String nomeUsuario;
	private String dtReserva;
	
	
	public int getIdReserva() {
		return idReserva;
	}
	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
	}
	public String getCatUsuario() {
		return catUsuario;
	}
	public void setCatUsuario(String catUsuario) {
		this.catUsuario = catUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getDtReserva() {
		return dtReserva;
	}
	public void setDtReserva(String dtReserva) {
		this.dtReserva = dtReserva;
	}

	
	
}
