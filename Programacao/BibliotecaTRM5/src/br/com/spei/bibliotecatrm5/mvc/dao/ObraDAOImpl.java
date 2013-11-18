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
			
			String query = "SELECT O.ID_OBRA, O.DS_OBRA, A.ID_AUTOR, A.NM_AUTOR, O.DT_ANO, " +
						   "E.ID_EDITORA, E.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA FROM OBRA O " +
						   "INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						   "INNER JOIN EDITORA E ON E.ID_EDITORA = O.ID_EDITORA " +
						   "INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				Obra obra = new Obra();
				
				int idObra = rs.getInt("ID_OBRA");
				String nomeObra = rs.getString("DS_OBRA");
				int idAutor = rs.getInt("ID_AUTOR");
				String nomeAutor = rs.getString("NM_AUTOR");
				int ano = rs.getInt("DT_ANO");
				int idEditora = rs.getInt("ID_EDITORA");
				String descricaoEditora = rs.getString("DS_EDITORA");
				int idTipoObra= rs.getInt("ID_TIPO_OBRA");
				String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
				
				obra.setIdObra(idObra);
				obra.setNomeObra(nomeObra);
				obra.getAutor().setCodAutor(idAutor);
				obra.getAutor().setNomeAutor(nomeAutor);
				obra.setAno(ano);
				obra.getEditora().setCodEditora(idEditora);
				obra.getEditora().setNomeEditora(descricaoEditora);
				obra.getTipoObra().setCodTipoObra(idTipoObra);
				obra.getTipoObra().setDescricaoTipoObra(descricaoTipoObra);
				
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
