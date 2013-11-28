package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Reserva;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public interface ReservaDAO {

	public List<Reserva> listAll() throws SQLException;

	public void insert(Reserva model) throws SQLException;

	public int getCodUsuarioReservaParaExemplar(Exemplar exemplar) throws SQLException;
}
