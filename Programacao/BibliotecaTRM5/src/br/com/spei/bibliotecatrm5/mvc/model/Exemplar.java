package br.com.spei.bibliotecatrm5.mvc.model;

public class Exemplar {
	
	private int codExemplar;
	private Obra obra;
	private int numeroExemplar;
	private boolean reservado;
	private boolean emprestado;
	
	public int getCodExemplar() {
		return codExemplar;
	}
	public void setCodExemplar(int codExemplar) {
		this.codExemplar = codExemplar;
	}
	public Obra getObra() {
		return obra;
	}
	public void setObra(Obra obra) {
		this.obra = obra;
	}
	public int getNumeroExemplar() {
		return numeroExemplar;
	}
	public void setNumeroExemplar(int numeroExemplar) {
		this.numeroExemplar = numeroExemplar;
	}
	public boolean isReservado() {
		return reservado;
	}
	public void setReservado(boolean reservado) {
		this.reservado = reservado;
	}
	public boolean isEmprestado() {
		return emprestado;
	}
	public void setEmprestado(boolean emprestado) {
		this.emprestado = emprestado;
	}
	
}
