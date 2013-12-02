package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public interface EmprestimoDAO {

	public List<Emprestimo> listAll() throws SQLException;

	void insert(Emprestimo emprestimo) throws SQLException;

	public List<String> getColumnNames() throws SQLException;

	public List<Emprestimo> getByUsuario(Usuario usuario) throws SQLException;
}
