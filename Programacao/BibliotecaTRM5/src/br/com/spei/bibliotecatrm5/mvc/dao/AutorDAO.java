package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;

public interface AutorDAO {

	List<String> getColumnNames() throws SQLException;

	List<Autor> listAll() throws SQLException;

	List<Autor> getByName(String textoPesquisa) throws SQLException;

	Autor get(int codigo) throws SQLException;
	
}
