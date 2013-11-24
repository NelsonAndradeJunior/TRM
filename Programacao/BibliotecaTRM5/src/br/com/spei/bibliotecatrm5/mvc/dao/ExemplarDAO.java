package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;

public interface ExemplarDAO {

	List<String> getColumnNames() throws SQLException;

	List<Exemplar> listAll() throws SQLException;

	List<Exemplar> getByName(String textoPesquisa) throws SQLException;

	Exemplar get(int codigo) throws SQLException;

}