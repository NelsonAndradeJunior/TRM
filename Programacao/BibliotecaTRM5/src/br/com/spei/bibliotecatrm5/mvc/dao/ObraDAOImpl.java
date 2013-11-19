package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Obra;

public class ObraDAOImpl implements ObraDAO {

	@Override
	public List<Obra> listAll() throws SQLException {
		List<Obra> listaObra = new ArrayList<>();
		
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

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) O.ID_OBRA Código, O.DS_OBRA Nome, O.DT_ANO Ano, A.NM_AUTOR Autor, E.DS_EDITORA Editora, T.DS_TIPO_OBRA 'Tipo da Obra' FROM OBRA O " +
						   "INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						   "INNER JOIN EDITORA E ON E.ID_EDITORA = O.ID_EDITORA " +
						   "INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		ResultSetMetaData rsMetaData = rs.getMetaData();
		
		for (int i = 1; i < rsMetaData.getColumnCount() + 1; i++) {
			listaNomeColunas.add(rsMetaData.getColumnName(i));
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return listaNomeColunas;
	}

	@Override
	public Obra get(int codigo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, A.ID_AUTOR, A.NM_AUTOR, O.DT_ANO, " +
					   "E.ID_EDITORA, E.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA FROM OBRA O " +
					   "INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
					   "INNER JOIN EDITORA E ON E.ID_EDITORA = O.ID_EDITORA " +
					   "INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
					   "WHERE O.ID_OBRA = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, codigo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Obra obraRetorno = null;
		
		if(rs.next()) {
			obraRetorno = new Obra();
			int idObra = rs.getInt("ID_OBRA");
			obraRetorno.setIdObra(idObra);
			String nomeObra = rs.getString("DS_OBRA");
			obraRetorno.setNomeObra(nomeObra);
			int ano = rs.getInt("DT_ANO");
			obraRetorno.setAno(ano);
			int codAutor = rs.getInt("ID_AUTOR");
			obraRetorno.getAutor().setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			obraRetorno.getAutor().setNomeAutor(nomeAutor);
			int codEditora = rs.getInt("ID_EDITORA");
			obraRetorno.getEditora().setCodEditora(codEditora);
			String descricaoEditora = rs.getString("DS_EDITORA");
			obraRetorno.getEditora().setNomeEditora(descricaoEditora);
			int idTipoObra= rs.getInt("ID_TIPO_OBRA");
			obraRetorno.getTipoObra().setCodTipoObra(idTipoObra);
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			obraRetorno.getTipoObra().setDescricaoTipoObra(descricaoTipoObra);			
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return obraRetorno;
	}

	@Override
	public List<Obra> getByName(String nomeObra) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, A.ID_AUTOR, A.NM_AUTOR, O.DT_ANO, " +
					   "E.ID_EDITORA, E.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA FROM OBRA O " +
					   "INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
					   "INNER JOIN EDITORA E ON E.ID_EDITORA = O.ID_EDITORA " +
					   "INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
					   "WHERE O.DS_OBRA LIKE ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setString(1, "%" +nomeObra + "%");
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Obra> obrasRetorno = new ArrayList<>();
		
		while(rs.next()) {
			Obra obra = new Obra();
			
			int idObra = rs.getInt("ID_OBRA");
			obra.setIdObra(idObra);
			String obraNome = rs.getString("DS_OBRA");
			obra.setNomeObra(obraNome);
			int ano = rs.getInt("DT_ANO");
			obra.setAno(ano);
			int codAutor = rs.getInt("ID_AUTOR");
			obra.getAutor().setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			obra.getAutor().setNomeAutor(nomeAutor);
			int codEditora = rs.getInt("ID_EDITORA");
			obra.getEditora().setCodEditora(codEditora);
			String descricaoEditora = rs.getString("DS_EDITORA");
			obra.getEditora().setNomeEditora(descricaoEditora);
			int idTipoObra= rs.getInt("ID_TIPO_OBRA");
			obra.getTipoObra().setCodTipoObra(idTipoObra);
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			obra.getTipoObra().setDescricaoTipoObra(descricaoTipoObra);	
			
			obrasRetorno.add(obra);
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return obrasRetorno;
	}

}
