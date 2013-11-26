package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;

public interface EmprestimoDAO {

	public List<Emprestimo> listAll() throws SQLException;

	void insert(Emprestimo emprestimo) throws SQLException;
}
