package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Obra;

public interface ObraDAO {

	public List<Obra> listAll();

	public void insert(Obra model) throws SQLException;
}
