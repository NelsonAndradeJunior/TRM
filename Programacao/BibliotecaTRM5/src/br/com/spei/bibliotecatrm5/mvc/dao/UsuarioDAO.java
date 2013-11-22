package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public interface UsuarioDAO {

	List<String> getColumnNames() throws SQLException;

	List<Usuario> listAll() throws SQLException;

	List<Usuario> getByName(String textoPesquisa) throws SQLException;

	Usuario get(int codigo) throws SQLException;

}
