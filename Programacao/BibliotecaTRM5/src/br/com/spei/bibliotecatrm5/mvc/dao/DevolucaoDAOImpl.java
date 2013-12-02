package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class DevolucaoDAOImpl implements DevolucaoDAO{
	
	public List<Devolucao> listAll() {
		List<Devolucao> listaDevolucao = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM DEVOLUCAO";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Devolucao devolucao = new Devolucao();
				
				int idUsuario = rs.getInt("ID_USUARIO");
				String matUsuario = rs.getString("MAT_USUARIO");
				String catUsuario = rs.getString("CAT_USUARIO");
				String dtDevolucao = rs.getString("DT_DEVOLUCAO");
				
				listaDevolucao.add(devolucao);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaDevolucao;
	}

	@Override
	public void insert(Devolucao devolucao) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		conexao.setAutoCommit(false);
		
		String query = "INSERT INTO DEVOLUCAO (ID_USUARIO, DT_DEVOLUCAO) VALUES (?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		Usuario usuario = devolucao.getUsuario();
		int codigoUsuario = usuario.getCodUsuario();
		
		pstmt.setInt(1, codigoUsuario);
		
		java.sql.Date dataDevolucao = new Date(new java.util.Date().getTime());
		
		pstmt.setDate(2, dataDevolucao);
		
		int codigoDevolucao = getNextId(conexao);
		
		devolucao.setCodDevolucao(codigoDevolucao);
		
		List<Exemplar> exemplares = devolucao.getExemplares();
		
		pstmt.executeUpdate();
		
		for (Exemplar exemplar : exemplares) {
			ItemDevolucaoDAO itemDevolucaoDAO = new ItemDevolucaoDAOImpl(conexao);
	
			int codigoExemplar = exemplar.getCodExemplar();
			
			itemDevolucaoDAO.insert(codigoDevolucao, codigoExemplar);		
		
			ExemplarDAO exemmplarDAO = new ExemplarDAOImpl();
			exemplar.setEmprestado(false);
			exemmplarDAO.update(exemplar);
		}
		
		conexao.commit();
		
		pstmt.close();
		conexao.close();
	}

	private int getNextId(Connection conexao) throws SQLException {		
		String query = "SELECT IDENT_CURRENT('DEVOLUCAO') 'PROX'";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int proxCod = 0;
		
		if(rs.next()) {
			proxCod = rs.getInt("PROX");
		}
		
		return proxCod;
	}


}

