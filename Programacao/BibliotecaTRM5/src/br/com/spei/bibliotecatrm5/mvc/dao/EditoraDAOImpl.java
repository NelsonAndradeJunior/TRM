package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.*;
import java.util.*;

import br.com.spei.bibliotecatrm5.mvc.model.Editora;

public class EditoraDAOImpl implements EditoraDAO {

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) ID_EDITORA 'Código', DS_EDITORA 'Nome' FROM EDITORA";
		
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
	public List<Editora> listAll() throws SQLException {
		List<Editora> listaEditora = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM EDITORA";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Editora editora = new Editora();
			
			int idAutor = rs.getInt("ID_EDITORA");
			String nomeAutor = rs.getString("DS_EDITORA");

			editora.setCodEditora(idAutor);
			editora.setNomeEditora(nomeAutor);
			
			listaEditora.add(editora);
		}
		
		return listaEditora;
	}

	@Override
	public Editora get(int codigo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM EDITORA WHERE ID_EDITORA = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, codigo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Editora editoraRetorno = null;
		
		if(rs.next()) {
			editoraRetorno = new Editora();
			int codEditora = rs.getInt("ID_EDITORA");
			editoraRetorno.setCodEditora(codEditora);
			String nomeEditora = rs.getString("DS_EDITORA");
			editoraRetorno.setNomeEditora(nomeEditora);			
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return editoraRetorno;
	}

	@Override
	public List<Editora> getByName(String textoPesquisa) throws SQLException {
		List<Editora> listaEditora = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM EDITORA WHERE DS_EDITORA LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Editora editora = new Editora();
			
			int codigoAutor = rs.getInt("ID_EDITORA");
			String nomeAutor = rs.getString("DS_EDITORA");
			
			editora.setCodEditora(codigoAutor);
			editora.setNomeEditora(nomeAutor);
			
			listaEditora.add(editora);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaEditora;
	}

}
