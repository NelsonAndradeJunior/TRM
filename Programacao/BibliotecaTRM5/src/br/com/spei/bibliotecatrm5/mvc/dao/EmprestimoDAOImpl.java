package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;

public class EmprestimoDAOImpl implements EmprestimoDAO {

	@Override
	public List<Emprestimo> listAll() {
		List<Emprestimo> listaEmprestimo = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM EMPRESTIMO";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Emprestimo emprestimo = new Emprestimo();
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String matUsuario = rs.getString("MAT_USUARIO");
				String nomeUsuario = rs.getString("NOME_USUARIO");
				String catUsuario = rs.getString("CAT_USUARIO");
				String dtEmprestimo = rs.getString("DT_EMPRESTIMO");
				
				
				
				
				listaEmprestimo.add(emprestimo);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaEmprestimo;
	}

	@Override
	public void insert(Emprestimo emprestimo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		conexao.setAutoCommit(false);
		
		String query = "INSERT INTO EMPRESTIMO (ID_USUARIO, DT_EMPRESTIMO) VALUES (?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		int codUsuario = emprestimo.getUsuario().getCodUsuario();
		
		pstmt.setInt(1, codUsuario); 
		
		java.sql.Date dtEmprestimoSQL = new java.sql.Date(emprestimo.getDataEmprestimo().getTime());
		
		pstmt.setDate(2, dtEmprestimoSQL);
		
		pstmt.executeUpdate();
		
		for (Exemplar exemplar : emprestimo.getExemplares()) {
			ItemEmprestimoDAO itemEmprestimoDAO = new ItemEmprestimoDAOImpl();
			int codEmprestimo = getUltimoIdGerado();
			emprestimo.setIdEmprestimo(codEmprestimo);
			itemEmprestimoDAO.insert(conexao, codEmprestimo, exemplar);
			
			exemplar.setEmprestado(true);
			
			ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
			exemplarDAO.update(exemplar);
		}
		
		conexao.commit();
		pstmt.close();
		conexao.close();
	}

	private int getUltimoIdGerado() throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		conexao.setAutoCommit(false);
		
		String query = "SELECT IDENT_CURRENT('EMPRESTIMO') 'PROXIMO'";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int retorno = 0;
		
		if(rs.next()) {
			retorno = rs.getInt("PROXIMO");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return retorno;
	}

}
