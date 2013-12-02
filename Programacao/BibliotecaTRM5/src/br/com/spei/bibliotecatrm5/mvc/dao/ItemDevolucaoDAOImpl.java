package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;

public class ItemDevolucaoDAOImpl implements ItemDevolucaoDAO {

	Connection conexao = null;
	
	public ItemDevolucaoDAOImpl(Connection conexao) {
		this.conexao = conexao;
	}

	@Override
	public void insert(int codigoDevolucao, int codigoExemplar) throws SQLException {
		String query = "INSERT INTO ITEM_DEVOLUCAO (ID_DEVOLUCAO, ID_EXEMPLAR) VALUES (?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		pstmt.setInt(1, codigoDevolucao);
		pstmt.setInt(2, codigoExemplar);
		
		pstmt.executeUpdate();
	}

}
