package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;

public interface ItemEmprestimoDAO {

	void insert(Connection conexao, int codEmprestimo, Exemplar exemplar) throws SQLException;;

}
