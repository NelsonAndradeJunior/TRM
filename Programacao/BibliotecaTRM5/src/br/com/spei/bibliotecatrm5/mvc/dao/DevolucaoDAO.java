package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;

public interface DevolucaoDAO {
	
	public List<Devolucao> listAll();

	void insert(Devolucao devolucao) throws SQLException;
	

}
