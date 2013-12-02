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
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

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
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
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
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
				"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
				"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
				"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
				"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
				"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
				"WHERE O.DS_OBRA LIKE ?";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);	
			
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
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
				"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
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
			exemplarRetorno = obtemExemplarDeResultSet(rs);			
		}
		
		rs.close();
		pstmt.close();
		conexao.close();
		
		return exemplarRetorno;
	}

	@Override
	public List<Exemplar> getLocaveisByName(String textoPesquisa)
			throws SQLException {
		List<Exemplar> listaExemplares = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
				"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
				"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
				"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
				"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
				"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
				"WHERE O.DS_OBRA LIKE ? AND T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0" +
				"E.SN_EMPRESTADO = 0";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
			listaExemplares.add(exemplar);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaExemplares;
	}

	@Override
	public List<Exemplar> listAllLocaveis() throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_EMPRESTADO = 0";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);	
			
			listaExemplar.add(exemplar);
		}
		
		stmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	@Override
	public void update(Exemplar exemplar) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "UPDATE EXEMPLAR SET NR_EXEMPLAR = ?, ID_OBRA = ?, SN_RESERVADO = ?, SN_EMPRESTADO = ? WHERE ID_EXEMPLAR = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		pstmt.setInt(1, exemplar.getNumeroExemplar());
		pstmt.setInt(2, exemplar.getObra().getIdObra());
		pstmt.setBoolean(3, exemplar.isReservado());
		pstmt.setBoolean(4, exemplar.isEmprestado());
		pstmt.setInt(5, exemplar.getCodExemplar());
		
		pstmt.executeUpdate();
		
		pstmt.close();
		conexao.close();
	}

	@Override
	public List<Exemplar> getReservaveisByName(String textoPesquisa)
			throws SQLException {
		List<Exemplar> listaExemplares = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
				"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
				"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
				"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
				"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
				"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
				"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
				"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
				"WHERE O.DS_OBRA LIKE ? AND T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0" +
				"AND E.SN_RESERVADO = 0 AND E.SN_EMPRESTADO = 1";
		
		PreparedStatement preparedStatement = conexao.prepareStatement(query);
		
		preparedStatement.setString(1, "%" + textoPesquisa  + "%");
		
		ResultSet rs = preparedStatement.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);	
			
			listaExemplares.add(exemplar);
		}
		
		rs.close();
		preparedStatement.close();
		conexao.close();
			
		return listaExemplares;
	}

	@Override
	public List<Exemplar> listAllReservaveis() throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_RESERVADO = 0 AND E.SN_EMPRESTADO = 1";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);		
			
			listaExemplar.add(exemplar);
		}
		
		stmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	@Override
	public List<Exemplar> getExceptId(List<Integer> listaCodigosFiltro)
			throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_EMPRESTADO = 0";
		
		PreparedStatement pstmt = null; 
		
		if(listaCodigosFiltro.size() > 0) {
			query += " AND E.ID_EXEMPLAR NOT IN (";
			
			for (int i = 0; i < listaCodigosFiltro.size(); i++) {
				if(i == 0)
					query += "?";
				else
					query += ", ?";
			}
			
			query = query + ")";
			
			pstmt = conexao.prepareStatement(query);
			
			for (int i = 0; i < listaCodigosFiltro.size(); i++) {
				pstmt.setInt(i + 1, listaCodigosFiltro.get(i));
			}
		} else {
			pstmt = conexao.prepareStatement(query);
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
			listaExemplar.add(exemplar);
		}
		
		pstmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	@Override
	public List<Exemplar> getEmprestadosById(
			List<Integer> listaCodigosFiltro)
			throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		if(listaCodigosFiltro.size() <= 0)
			return listaExemplar;
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_EMPRESTADO = 1"; 
		
		query += " AND E.ID_EXEMPLAR IN (";
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			if(i == 0)
				query += "?";
			else
				query += ", ?";
		}
		
		query = query + ")";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			pstmt.setInt(i + 1, listaCodigosFiltro.get(i));
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
			listaExemplar.add(exemplar);
		}
		
		pstmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}
	
	@Override
	public List<Exemplar> getByIds(
			List<Integer> listaCodigosFiltro)
			throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		if(listaCodigosFiltro.size() <= 0)
			return listaExemplar;
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_EMPRESTADO = 0"; 
		
		query += " AND E.ID_EXEMPLAR IN (";
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			if(i == 0)
				query += "?";
			else
				query += ", ?";
		}
		
		query = query + ")";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			pstmt.setInt(i + 1, listaCodigosFiltro.get(i));
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
			listaExemplar.add(exemplar);
		}
		
		pstmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	private Exemplar obtemExemplarDeResultSet(ResultSet resultSet) throws SQLException {
		Exemplar exemplar = new Exemplar();
		
		int codExemplar = resultSet.getInt("ID_EXEMPLAR");
		exemplar.setCodExemplar(codExemplar);
		int nrExemplar = resultSet.getInt("NR_EXEMPLAR");
		exemplar.setNumeroExemplar(nrExemplar);
		boolean isReservado = resultSet.getBoolean("SN_RESERVADO");
		exemplar.setReservado(isReservado);
		boolean isEmprestado = resultSet.getBoolean("SN_EMPRESTADO");
		exemplar.setEmprestado(isEmprestado);
		
		Obra obra = new Obra();
		int idObra = resultSet.getInt("ID_OBRA");
		obra.setIdObra(idObra);
		String descricaoObra = resultSet.getString("DS_OBRA");
		obra.setNomeObra(descricaoObra);
		int ano = resultSet.getInt("DT_ANO");
		obra.setAno(ano);
		boolean isClassico = resultSet.getBoolean("SN_CLASSICO");
		obra.setClassico(isClassico);
		
		Autor autor = new Autor();
		int codAutor = resultSet.getInt("ID_AUTOR");
		autor.setCodAutor(codAutor);
		String nomeAutor = resultSet.getString("NM_AUTOR");
		autor.setNomeAutor(nomeAutor);
		
		Editora editora = new Editora();
		int codEditora = resultSet.getInt("ID_EDITORA");
		editora.setCodEditora(codEditora);
		String nomeEditora = resultSet.getString("DS_EDITORA");
		editora.setNomeEditora(nomeEditora);
		
		TipoObra tipoObra = new TipoObra();
		int codTipoObra = resultSet.getInt("ID_TIPO_OBRA");
		tipoObra.setCodTipoObra(codTipoObra);
		String descricaoTipoObra = resultSet.getString("DS_TIPO_OBRA");
		tipoObra.setDescricaoTipoObra(descricaoTipoObra);
		boolean isDicionario = resultSet.getBoolean("SN_DICIONARIO");
		tipoObra.setDicionario(isDicionario);
		boolean isEnciclopedia = resultSet.getBoolean("SN_ENCICLOPEDIA");
		tipoObra.setEnciclopedia(isEnciclopedia);
		boolean isPeriodico = resultSet.getBoolean("SN_PERIODICO");
		tipoObra.setPeriodico(isPeriodico);
		
		obra.setAutor(autor);
		obra.setEditora(editora);
		obra.setTipoObra(tipoObra);
		exemplar.setObra(obra);
		
		return exemplar;
	}

	@Override
	public List<Exemplar> getLocaveisByIds(
			List<Integer> listaCodigosFiltro, Usuario usuario)
			throws SQLException {
		List<Exemplar> listaExemplar = new ArrayList<>();
		
		if(listaCodigosFiltro.size() <= 0)
			return listaExemplar;
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		// TODO Terminar de implementar
		
		String query = "SELECT O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, A.ID_AUTOR, A.NM_AUTOR, " +
						"ED.ID_EDITORA, ED.DS_EDITORA, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"E.ID_EXEMPLAR, E.NR_EXEMPLAR, E.SN_RESERVADO, E.SN_EMPRESTADO, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO FROM EXEMPLAR E " +
						"INNER JOIN OBRA O ON O.ID_OBRA = E.ID_OBRA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR " +
						"INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN TIPO_OBRA T ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA " +
						"WHERE T.SN_DICIONARIO = 0 AND T.SN_ENCICLOPEDIA = 0 AND T.SN_PERIODICO = 0 " +
						"AND E.SN_EMPRESTADO = 0 AND E.ID_EXEMPLAR IN (" +
						"SELECT E.ID_EXEMPLAR FROM EXEMPLAR E INNER JOIN RESERVA R ON R.ID_EXEMPLAR = E.ID_EXEMPLAR " +
						"INNER JOIN USUARIO U ON U.ID_USUARIO = R.ID_USUARIO WHERE R.DT_RESERVA > ) "; 
		
		query += " AND E.ID_EXEMPLAR IN (";
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			if(i == 0)
				query += "?";
			else
				query += ", ?";
		}
		
		query = query + ")";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		for (int i = 0; i < listaCodigosFiltro.size(); i++) {
			pstmt.setInt(i + 1, listaCodigosFiltro.get(i));
		}
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			Exemplar exemplar = obtemExemplarDeResultSet(rs);			
			
			listaExemplar.add(exemplar);
		}
		
		pstmt.close();
		rs.close();
		conexao.close();
		
		return listaExemplar;
	}

	@Override
	public List<Integer> getCodigoExemplaresDisponivelReservadoPara(Usuario usuario)
			throws SQLException {
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT E.ID_EXEMPLAR FROM EXEMPLAR E INNER JOIN RESERVA R " +
						"ON E.ID_EXEMPLAR = R.ID_EXEMPLAR INNER JOIN USUARIO U " +
						"ON U.ID_USUARIO = R.ID_USUARIO WHERE U.ID_USUARIO = ? " +
						"AND E.SN_RESERVADO = 1 AND E.SN_EMPRESTADO = 0";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		int codUsuario = usuario.getCodUsuario();
		
		pstmt.setInt(1, codUsuario);
		
		ResultSet rs = pstmt.executeQuery();
		
		List<Integer> listaCodigoExemplares = new ArrayList<>();
		
		while(rs.next()) {
			int codExemplar = rs.getInt("ID_EXEMPLAR");
			listaCodigoExemplares.add(codExemplar);
		}
		
		return listaCodigoExemplares;
	}

	@Override
	public List<Integer> getCodigoExemplaresDisponiveis() throws SQLException {
	
	Connection conexao = Conexao.getInstance().getConnection();
	
	String query = "SELECT E.ID_EXEMPLAR FROM EXEMPLAR E INNER JOIN RESERVA R " +
					"ON E.ID_EXEMPLAR = R.ID_EXEMPLAR INNER JOIN USUARIO U " +
					"ON U.ID_USUARIO = R.ID_USUARIO WHERE E.SN_EMPRESTADO = 0";
	
	Statement stmt = conexao.createStatement();
	
	ResultSet rs = stmt.executeQuery(query);
	
	List<Integer> listaCodigoExemplares = new ArrayList<>();
	
	while(rs.next()) {
		int codExemplar = rs.getInt("ID_EXEMPLAR");
		listaCodigoExemplares.add(codExemplar);
	}
	
	return listaCodigoExemplares;}
}
