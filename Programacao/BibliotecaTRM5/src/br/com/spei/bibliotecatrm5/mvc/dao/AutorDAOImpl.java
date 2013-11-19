package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class AutorDAOImpl implements AutorDAO {

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) ID_AUTOR 'Código', NM_AUTOR 'Nome' FROM AUTOR";
		
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
	public List<Autor> listAll() throws SQLException {
		List<Autor> listaObra = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM AUTOR";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Autor autor = new Autor();
			
			int idAutor = rs.getInt("ID_AUTOR");
			String nomeAutor = rs.getString("NM_AUTOR");

			autor.setCodAutor(idAutor);
			autor.setNomeAutor(nomeAutor);
			
			listaObra.add(autor);
		}
		
		return listaObra;
	}

	@Override
	public List<Autor> getByName(String textoPesquisa) throws SQLException {
		List<Autor> listaAutor = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM AUTOR WHERE NM_AUTOR LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Autor autor = new Autor();
			
			int codigoAutor = rs.getInt("ID_AUTOR");
			String nomeAutor = rs.getString("NM_AUTOR");
			
			autor.setCodAutor(codigoAutor);
			autor.setNomeAutor(nomeAutor);
			
			listaAutor.add(autor);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaAutor;
	}

	@Override
	public Autor get(int codigo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM AUTOR WHERE ID_AUTOR = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, codigo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Autor autorRetorno = null;
		
		if(rs.next()) {
			autorRetorno = new Autor();
			int codAutor = rs.getInt("ID_AUTOR");
			autorRetorno.setCodAutor(codAutor);
			String nomeAutor = rs.getString("NM_AUTOR");
			autorRetorno.setNomeAutor(nomeAutor);			
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return autorRetorno;
	}

}
