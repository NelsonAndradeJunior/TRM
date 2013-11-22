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
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class UsuarioDAOImpl implements UsuarioDAO {

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) ID_USUARIO 'Código', NM_USUARIO 'Nome' FROM USUARIO";
		
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
	public List<Usuario> listAll() throws SQLException {
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM USUARIO";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Usuario usuario = new Usuario();
			
			int idUsuario = rs.getInt("ID_USUARIO");
			String nomeUsuario = rs.getString("NM_USUARIO");
			String sobreNomeUsuario = rs.getString("NM_SOBRENOME_USUARIO");

			usuario.setCodUsuario(idUsuario);
			usuario.setNomeUsuario(nomeUsuario);
			usuario.setSobrenomeUsuario(sobreNomeUsuario);
			
			listaUsuarios.add(usuario);
		}
		
		return listaUsuarios;
	}

	@Override
	public List<Usuario> getByName(String textoPesquisa) throws SQLException {
		List<Usuario> listaUsuarios = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM USUARIO WHERE NM_USUARIO LIKE ? OR NM_SOBRENOME_USUARIO LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		preparedStatement.setString(2, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Usuario usuario = new Usuario();
			
			int codigoUsuario = rs.getInt("ID_USUARIO");
			String nomeUsuario = rs.getString("NM_USUARIO");
			String sobrenomeUsuario = rs.getString("NM_SOBRENOME_USUARIO");
			
			usuario.setCodUsuario(codigoUsuario);
			usuario.setNomeUsuario(nomeUsuario);
			usuario.setSobrenomeUsuario(sobrenomeUsuario);
			
			listaUsuarios.add(usuario);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaUsuarios;
	}

	@Override
	public Usuario get(int codigo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM USUARIO WHERE ID_USUARIO = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, codigo);
		
		ResultSet rs = pstmt.executeQuery();
		
		Usuario usuarioRetorno = null;
		
		if(rs.next()) {
			usuarioRetorno = new Usuario();
			int codUsuario = rs.getInt("ID_USUARIO");
			usuarioRetorno.setCodUsuario(codUsuario);
			String nomeUsuario = rs.getString("NM_USUARIO");
			usuarioRetorno.setNomeUsuario(nomeUsuario);
			String sobrenomeUsuario = rs.getString("NM_SOBRENOME_USUARIO");
			usuarioRetorno.setSobrenomeUsuario(sobrenomeUsuario);
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return usuarioRetorno ;
	}

}
