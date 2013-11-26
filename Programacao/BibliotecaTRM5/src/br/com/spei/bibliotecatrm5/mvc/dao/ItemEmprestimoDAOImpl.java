package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;

public class ItemEmprestimoDAOImpl implements ItemEmprestimoDAO {

	@Override
	public void insert(Connection conexao, int codEmprestimo, Exemplar exemplar) throws SQLException {
		
		conexao.setAutoCommit(false);
		
		String query = "INSERT INTO ITEM_EMPRESTIMO (ID_EMPRESTIMO, ID_EXEMPLAR) VALUES (?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		int codExemplar = exemplar.getCodExemplar();
		
		pstmt.setInt(1, codEmprestimo);
		pstmt.setInt(2, codExemplar);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		
		// Não fechar a conexão pois este método é um complemento do cadastro de empréstimo
	}

}
