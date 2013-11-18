package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.PreparedStatement;
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
				String tipoObra = rs.getString("ID_TIPO_OBRA");
				
				// TODO implementar
				
//				obra.setIdObra(idObra);
//				obra.setAutor(autor);
//				obra.setDtFabricacao((java.sql.Date) dtFabricacao);
//				obra.setEditora(editora);
//				obra.setTipoObra(tipoObra);
				
				listaObra.add(obra);
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaObra;
	}

	@Override
	public void insert(Obra model) throws SQLException {
		String query = "INSERT INTO OBRA (DS_OBRA, ID_AUTOR, DT_ANO, ID_EDITORA, ID_TIPO_OBRA) VALUES (?, ?, ? , ?, ?)";
		
		String nomeObra = model.getNomeObra();
		int codAutor = model.getAutor().getCodAutor();
		int ano = model.getAno();
		int codEditora = model.getEditora().getCodEditora();
		int codTipoObra = model.getTipoObra().getCodTipoObra();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setString(1, nomeObra);
		pstmt.setInt(2, codAutor);
		pstmt.setInt(3, ano);
		pstmt.setInt(4, codEditora);
		pstmt.setInt(5, codTipoObra);
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conexao.close();
	}

}
