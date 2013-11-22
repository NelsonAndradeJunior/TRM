package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Obra;

public interface ObraDAO {

	public List<Obra> listAll() throws SQLException;

	public void insert(Obra model) throws SQLException;

	public List<String> getColumnNames() throws SQLException;
	public Obra get(int codigo) throws SQLException;

	public List<Obra> getByName(String nomeObra) throws SQLException;

	public void update(Obra model) throws SQLException;
}
