package br.com.spei.bibliotecatrm5.mvc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class TipoObraDAOImpl implements TipoObraDAO {

	@Override
	public List<TipoObra> listAll() throws SQLException {
		List<TipoObra> listaTipoObra = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM TIPO_OBRA";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			TipoObra tipoObra = new TipoObra();
			
			int codigoObra = rs.getInt("ID_TIPO_OBRA");
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			boolean isDicionario = rs.getBoolean("SN_DICIONARIO");
			boolean isEnciclopedia = rs.getBoolean("SN_ENCICLOPEDIA");
			boolean isPeriodico = rs.getBoolean("SN_PERIODICO");
			
			tipoObra.setCodTipoObra(codigoObra);
			tipoObra.setDescricaoTipoObra(descricaoTipoObra);
			tipoObra.setDicionario(isDicionario);
			tipoObra.setEnciclopedia(isEnciclopedia);
			tipoObra.setPeriodico(isPeriodico);
			
			listaTipoObra.add(tipoObra);
		}
		
		rs.close();
		stmt.close();
		conexao.close();
			
		return listaTipoObra;
	}

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) ID_TIPO_OBRA Código, DS_TIPO_OBRA Descrição, " +
						"SN_DICIONARIO 'Dicionário', SN_ENCICLOPEDIA 'Enciclopédia', " +
						"SN_PERIODICO 'Periódico' FROM TIPO_OBRA ";
		
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
	public List<TipoObra> getByName(String textoPesquisa) throws SQLException {
		List<TipoObra> listaTipoObra = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT * FROM TIPO_OBRA WHERE DS_TIPO_OBRA LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			TipoObra tipoObra = new TipoObra();
			
			int codigoObra = rs.getInt("ID_TIPO_OBRA");
			String descricaoTipoObra = rs.getString("DS_TIPO_OBRA");
			boolean isDicionario = rs.getBoolean("SN_DICIONARIO");
			boolean isEnciclopedia = rs.getBoolean("SN_ENCICLOPEDIA");
			boolean isPeriodico = rs.getBoolean("SN_PERIODICO");
			
			tipoObra.setCodTipoObra(codigoObra);
			tipoObra.setDescricaoTipoObra(descricaoTipoObra);
			tipoObra.setDicionario(isDicionario);
			tipoObra.setEnciclopedia(isEnciclopedia);
			tipoObra.setPeriodico(isPeriodico);
			
			listaTipoObra.add(tipoObra);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaTipoObra;
	}

	@Override
	public void insert(TipoObra model) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "INSERT INTO TIPO_OBRA (DS_TIPO_OBRA, SN_PERIODICO, SN_DICIONARIO, SN_ENCICLOPEDIA) VALUES (?, ?, ?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		pstmt.setString(1, model.getDescricaoTipoObra());
		pstmt.setBoolean(2, model.isPeriodico());
		pstmt.setBoolean(3, model.isDicionario());
		pstmt.setBoolean(4, model.isEnciclopedia());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conexao.close();
	}

	@Override
	public void update(TipoObra model) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "UPDATE TIPO_OBRA SET DS_TIPO_OBRA = ?, SN_DICIONARIO = ?, SN_ENCICLOPEDIA = ?, SN_PERIODICO = ? WHERE ID_TIPO_OBRA = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		pstmt.setString(1, model.getDescricaoTipoObra());
		pstmt.setBoolean(2, model.isDicionario());
		pstmt.setBoolean(3, model.isEnciclopedia());
		pstmt.setBoolean(4, model.isPeriodico());
		pstmt.setInt(5, model.getCodTipoObra());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conexao.close();
	}
}
