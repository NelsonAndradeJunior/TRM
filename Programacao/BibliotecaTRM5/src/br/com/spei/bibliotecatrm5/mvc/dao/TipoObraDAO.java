package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public interface TipoObraDAO {

	public List<TipoObra> listAll() throws SQLException;
	public List<String> getColumnNames() throws SQLException;
	public List<TipoObra> getByName(String textoPesquisa) throws SQLException;
	public void insert(TipoObra model) throws SQLException;
	public void update(TipoObra model) throws SQLException;
}
