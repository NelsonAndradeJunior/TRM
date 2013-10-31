package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Obra;

public class ObraDAOImpl implements ObraDAO {

	@Override
	public List<Obra> listAll() {
		List<Obra> listaObra = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT * FROM OBRA";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Obra obra = new Obra();
				
				int idObra = rs.getInt("ID_OBRA");
				String autor = rs.getString("AUTOR");
				Date dtFabricacao = rs.getDate("DT_FABRICACAO");
				String editora = rs.getString("EDITORA");
				
				obra.setIdObra(idObra);
				obra.setAutor(autor);
				obra.setDtFabricacao((java.sql.Date) dtFabricacao);
				obra.setEditora(editora);
				
				listaObra.add(obra);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaObra;
	}

}
