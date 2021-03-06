package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public interface ExemplarDAO {

	List<String> getColumnNames() throws SQLException;

	List<Exemplar> listAll() throws SQLException;

	List<Exemplar> getByName(String textoPesquisa) throws SQLException;

	Exemplar get(int codigo) throws SQLException;

	List<Exemplar> getLocaveisByName(String textoPesquisa) throws SQLException;

	List<Exemplar> listAllLocaveis() throws SQLException;

	void update(Exemplar exemplar)  throws SQLException;

	List<Exemplar> getReservaveisByName(String textoPesquisa) throws SQLException;

	List<Exemplar> listAllReservaveis() throws SQLException;

	List<Exemplar> getExceptId(List<Integer> listaCodigosFiltro) throws SQLException;

	List<Exemplar> getByIds(List<Integer> listaCodigosFiltro) throws SQLException;

	List<Exemplar> getLocaveisByIds(List<Integer> listaCodigosFiltro,
			Usuario usuario) throws SQLException;

	List<Integer> getCodigoExemplaresDisponivelReservadoPara(Usuario usuario) throws SQLException;

	List<Integer> getCodigoExemplaresDisponiveis() throws SQLException;

	List<Exemplar> getEmprestadosById(List<Integer> listaCodigosFiltro)
			throws SQLException;

}
