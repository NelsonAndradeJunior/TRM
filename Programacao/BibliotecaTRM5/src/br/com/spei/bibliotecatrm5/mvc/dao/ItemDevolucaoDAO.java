package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.SQLException;

import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;

public interface ItemDevolucaoDAO {
	void insert(int codigoDevolucao, int codigoExemplar) throws SQLException;

}
