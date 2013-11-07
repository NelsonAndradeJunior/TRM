package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;

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
				
				
				devolucao.setIdUsuario(idUsuario);
				devolucao.setMatUsuario(matUsuario);
				devolucao.setCatUsuario(catUsuario);
				devolucao.setDtDevolucao(dtDevolucao);
				
				listaDevolucao.add(devolucao);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaDevolucao;
	}


}
