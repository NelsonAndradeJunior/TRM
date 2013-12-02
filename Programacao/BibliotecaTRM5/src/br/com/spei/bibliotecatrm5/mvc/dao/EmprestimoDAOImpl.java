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
import br.com.spei.bibliotecatrm5.mvc.model.Editora;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class EmprestimoDAOImpl implements EmprestimoDAO {

	@Override
	public List<Emprestimo> listAll() {
		List<Emprestimo> listaEmprestimo = new ArrayList<>();
		
		try {
			Connection conexao = Conexao.getInstance().getConnection();
			
			String query = "SELECT E.ID_EMPRESTIMO, E.DT_EMPRESTIMO, U.ID_USUARIO, U.NM_USUARIO, " +
						"U.NM_SOBRENOME_USUARIO, EX.ID_EXEMPLAR, EX.NR_EXEMPLAR, EX.SN_EMPRESTADO, EX.SN_RESERVADO, " +
						"O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
						"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO, ED.ID_EDITORA, ED.DS_EDITORA, " +
						"A.ID_AUTOR, A.NM_AUTOR FROM EMPRESTIMO E INNER JOIN USUARIO U ON U.ID_USUARIO = E.ID_USUARIO " +
						"INNER JOIN ITEM_EMPRESTIMO IE ON IE.ID_EMPRESTIMO = E.ID_EMPRESTIMO " +
						"INNER JOIN EXEMPLAR EX ON EX.ID_EXEMPLAR = IE.ID_EXEMPLAR " +
						"INNER JOIN OBRA O ON O.ID_OBRA = EX.ID_OBRA INNER JOIN TIPO_OBRA T " +
						"ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
						"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR";
			
			Statement stmt = conexao.createStatement();
			
			ResultSet resultSet = stmt.executeQuery(query);
			
			int codAnterior = 0;
			Emprestimo emprestimo = null;
			
			while (resultSet.next()) {
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
				
				if(codAnterior != resultSet.getInt("ID_EMPRESTIMO")) {
					emprestimo = new Emprestimo();
					listaEmprestimo.add(emprestimo);
				}
				
				emprestimo.getExemplares().add(exemplar);
				emprestimo.setIdEmprestimo(resultSet.getInt("ID_EMPRESTIMO"));
				emprestimo.setDataEmprestimo(resultSet.getDate("DT_EMPRESTIMO"));
				
				codAnterior = emprestimo.getIdEmprestimo();
			}
			
		} catch (SQLException e) {
			// TODO Tratar
			e.printStackTrace();
			return null;
		};
		
		return listaEmprestimo;
	}

	@Override
	public void insert(Emprestimo emprestimo) throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		conexao.setAutoCommit(false);
		
		String query = "INSERT INTO EMPRESTIMO (ID_USUARIO, DT_EMPRESTIMO) VALUES (?, ?)";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		int codUsuario = emprestimo.getUsuario().getCodUsuario();
		
		pstmt.setInt(1, codUsuario); 
		
		java.sql.Date dtEmprestimoSQL = new java.sql.Date(emprestimo.getDataEmprestimo().getTime());
		
		pstmt.setDate(2, dtEmprestimoSQL);
		
		pstmt.executeUpdate();
		
		for (Exemplar exemplar : emprestimo.getExemplares()) {
			ItemEmprestimoDAO itemEmprestimoDAO = new ItemEmprestimoDAOImpl();
			int codEmprestimo = getUltimoIdGerado();
			emprestimo.setIdEmprestimo(codEmprestimo);
			itemEmprestimoDAO.insert(conexao, codEmprestimo, exemplar);
			
			exemplar.setEmprestado(true);
			exemplar.setReservado(false);
			
			ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
			exemplarDAO.update(exemplar);
		}
		
		conexao.commit();
		pstmt.close();
		conexao.close();
	}

	private int getUltimoIdGerado() throws SQLException {
		Connection conexao = Conexao.getInstance().getConnection();
		conexao.setAutoCommit(false);
		
		String query = "SELECT IDENT_CURRENT('EMPRESTIMO') 'PROXIMO'";
		
		Statement stmt = conexao.createStatement();
		
		ResultSet rs = stmt.executeQuery(query);
		
		int retorno = 0;
		
		if(rs.next()) {
			retorno = rs.getInt("PROXIMO");
		}
		
		rs.close();
		stmt.close();
		conexao.close();
		
		return retorno;
	}

	@Override
	public List<String> getColumnNames() throws SQLException {
		List<String> listaNomeColunas = new ArrayList<String>();

		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT TOP(1) EX.ID_EXEMPLAR 'Código Exemplar', " +
						"O.DS_OBRA 'Obra', E.DT_EMPRESTIMO 'Data Empréstimo' " +
						"FROM EMPRESTIMO E INNER JOIN ITEM_EMPRESTIMO IE " +
						"ON IE.ID_EMPRESTIMO = E.ID_EMPRESTIMO " +
						"INNER JOIN EXEMPLAR EX ON EX.ID_EXEMPLAR = IE.ID_EXEMPLAR " +
						"INNER JOIN OBRA O ON O.ID_OBRA = EX.ID_OBRA";
		
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
	public List<Emprestimo> getByUsuario(Usuario usuario) throws SQLException {
		
		List<Emprestimo> listaEmprestimo = new ArrayList<>();
		
		Connection conexao = Conexao.getInstance().getConnection();
		
		String query = "SELECT E.ID_EMPRESTIMO, E.DT_EMPRESTIMO, U.ID_USUARIO, U.NM_USUARIO, " +
					"U.NM_SOBRENOME_USUARIO, EX.ID_EXEMPLAR, EX.NR_EXEMPLAR, EX.SN_EMPRESTADO, EX.SN_RESERVADO, " +
					"O.ID_OBRA, O.DS_OBRA, O.DT_ANO, O.SN_CLASSICO, T.ID_TIPO_OBRA, T.DS_TIPO_OBRA, " +
					"T.SN_DICIONARIO, T.SN_ENCICLOPEDIA, T.SN_PERIODICO, ED.ID_EDITORA, ED.DS_EDITORA, " +
					"A.ID_AUTOR, A.NM_AUTOR FROM EMPRESTIMO E INNER JOIN USUARIO U ON U.ID_USUARIO = E.ID_USUARIO " +
					"INNER JOIN ITEM_EMPRESTIMO IE ON IE.ID_EMPRESTIMO = E.ID_EMPRESTIMO " +
					"INNER JOIN EXEMPLAR EX ON EX.ID_EXEMPLAR = IE.ID_EXEMPLAR " +
					"INNER JOIN OBRA O ON O.ID_OBRA = EX.ID_OBRA INNER JOIN TIPO_OBRA T " +
					"ON T.ID_TIPO_OBRA = O.ID_TIPO_OBRA INNER JOIN EDITORA ED ON ED.ID_EDITORA = O.ID_EDITORA " +
					"INNER JOIN AUTOR A ON A.ID_AUTOR = O.ID_AUTOR WHERE EX.SN_EMPRESTADO = 1 AND U.ID_USUARIO = ?";
		
		PreparedStatement pstmt = conexao.prepareStatement(query);
		
		pstmt.setInt(1, usuario.getCodUsuario());
		
		ResultSet resultSet = pstmt.executeQuery();
		
		int codAnterior = 0;
		Emprestimo emprestimo = null;
		
		while (resultSet.next()) {
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
			
			if(codAnterior != resultSet.getInt("ID_EMPRESTIMO")) {
				emprestimo = new Emprestimo();
				listaEmprestimo.add(emprestimo);
			}
			
			emprestimo.getExemplares().add(exemplar);
			emprestimo.setIdEmprestimo(resultSet.getInt("ID_EMPRESTIMO"));
			emprestimo.setDataEmprestimo(resultSet.getDate("DT_EMPRESTIMO"));
			
			codAnterior = emprestimo.getIdEmprestimo();
		}
		
		return listaEmprestimo;
	}

}
