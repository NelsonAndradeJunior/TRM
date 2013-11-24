package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Editora;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class ExemplarDAOImpl implements ExemplarDAO {

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) E.ID_EXEMPLAR 'Código', O.DS_OBRA 'Nome', E.NR_EXEMPLAR 'Exemplar' FROM EXEMPLAR E INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA";
		
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
	public List<Exemplar> listAll() throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Exemplar exemplar = new Exemplar();
			
			int codExemplar = rs.getInt("ID_EXEMPLAR");
			exemplar.setCodExemplar(codExemplar);
			int nrExemplar = rs.getInt("NR_EXEMPLAR");
			exemplar.setNumeroExemplar(nrExemplar);
			
			Obra obra = new Obra();
			int idObra = rs.getInt("ID_OBRA");
			obra.setIdObra(idObra);
			String descricaoObra = rs.getString("DS_OBRA");
			obra.setNomeObra(descricaoObra);
			int ano = rs.getInt("DT_ANO");
			obra.setAno(ano);
			
			Autor autor = new Autor();
			int codAutor = rs.getInt("ID_AUTOR");
			autor.setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			autor.setNomeAutor(nomeAutor);
			
			Editora editora = new Editora();
			int codEditora = rs.getInt("ID_EDITORA");
			editora.setCodEditora(codEditora);
			String nomeEditora = rs.getString("DS_EDITORA");
			editora.setNomeEditora(nomeEditora);
			
			TipoObra tipoObra = new TipoObra();
			int codTipoObra = rs.getInt("ID_TIPO_OBRA");
			tipoObra.setCodTipoObra(codTipoObra);
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			tipoObra.setDescricaoTipoObra(descricaoTipoObra);
			
			obra.setAutor(autor);
			obra.setEditora(editora);
			obra.setTipoObra(tipoObra);
			exemplar.setObra(obra);			
			
			listaExemplar.add(exemplar);
		}
		
		stmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	@Override
	public List<Exemplar> getByName(String textoPesquisa) throws SQLException {
		List<Exemplar> listaExemplares = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR FROM EXEMPLAR E " +
				"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
				"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
				"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
				"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
				"WHERE O.DS_OBRA LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = new Exemplar();
			
			int codExemplar = rs.getInt("ID_EXEMPLAR");
			exemplar.setCodExemplar(codExemplar);
			int nrExemplar = rs.getInt("NR_EXEMPLAR");
			exemplar.setNumeroExemplar(nrExemplar);
			
			Obra obra = new Obra();
			int idObra = rs.getInt("ID_OBRA");
			obra.setIdObra(idObra);
			String descricaoObra = rs.getString("DS_OBRA");
			obra.setNomeObra(descricaoObra);
			int ano = rs.getInt("DT_ANO");
			obra.setAno(ano);
			
			Autor autor = new Autor();
			int codAutor = rs.getInt("ID_AUTOR");
			autor.setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			autor.setNomeAutor(nomeAutor);
			
			Editora editora = new Editora();
			int codEditora = rs.getInt("ID_EDITORA");
			editora.setCodEditora(codEditora);
			String nomeEditora = rs.getString("DS_EDITORA");
			editora.setNomeEditora(nomeEditora);
			
			TipoObra tipoObra = new TipoObra();
			int codTipoObra = rs.getInt("ID_TIPO_OBRA");
			tipoObra.setCodTipoObra(codTipoObra);
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			tipoObra.setDescricaoTipoObra(descricaoTipoObra);
			
			obra.setAutor(autor);
			obra.setEditora(editora);
			obra.setTipoObra(tipoObra);
			exemplar.setObra(obra);			
			
			listaExemplares.add(exemplar);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaExemplares;
	}

	@Override
	public Exemplar get(int codigo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR FROM EXEMPLAR E " +
				"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
				"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
				"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
				"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
				"WHERE E.ID_EXEMPLAR = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, codigo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Exemplar exemplarRetorno = null;
		
		if (rs.next()) {
			exemplarRetorno = new Exemplar();
			
			int codExemplar = rs.getInt("ID_EXEMPLAR");
			exemplarRetorno.setCodExemplar(codExemplar);
			int nrExemplar = rs.getInt("NR_EXEMPLAR");
			exemplarRetorno.setNumeroExemplar(nrExemplar);
			
			Obra obra = new Obra();
			int idObra = rs.getInt("ID_OBRA");
			obra.setIdObra(idObra);
			String descricaoObra = rs.getString("DS_OBRA");
			obra.setNomeObra(descricaoObra);
			int ano = rs.getInt("DT_ANO");
			obra.setAno(ano);
			
			Autor autor = new Autor();
			int codAutor = rs.getInt("ID_AUTOR");
			autor.setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			autor.setNomeAutor(nomeAutor);
			
			Editora editora = new Editora();
			int codEditora = rs.getInt("ID_EDITORA");
			editora.setCodEditora(codEditora);
			String nomeEditora = rs.getString("DS_EDITORA");
			editora.setNomeEditora(nomeEditora);
			
			TipoObra tipoObra = new TipoObra();
			int codTipoObra = rs.getInt("ID_TIPO_OBRA");
			tipoObra.setCodTipoObra(codTipoObra);
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			tipoObra.setDescricaoTipoObra(descricaoTipoObra);
			
			obra.setAutor(autor);
			obra.setEditora(editora);
			obra.setTipoObra(tipoObra);
			exemplarRetorno.setObra(obra);			
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return exemplarRetorno;
	}

}
