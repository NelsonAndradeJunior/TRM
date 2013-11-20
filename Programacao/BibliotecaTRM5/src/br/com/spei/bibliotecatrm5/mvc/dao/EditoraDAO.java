package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Editora;

public interface EditoraDAO {

	List<String> getColumnNames() throws SQLException;

	List<Editora> listAll() throws SQLException;

	Editora get(int codigo) throws SQLException;

	List<Editora> getByName(String textoPesquisa) throws SQLException;

}
