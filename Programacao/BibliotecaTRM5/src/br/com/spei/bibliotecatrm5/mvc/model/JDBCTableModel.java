package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.Conexao;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;

public class JDBCTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Object[][] conteudo; 
	String[] nomeColunas;
	
	// TODO Verificar a melhor maneira de tratar o erro
	public JDBCTableModel(String nomeTabela) {
		super();
		try {
			getConteudoTabela(nomeTabela);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// TODO Verificar a melhor maneira de tratar o erro
	private void getConteudoTabela(String nomeTabela) throws SQLException {
		TipoObraDAO tipoObraDAO = new TipoObraDAOImpl();
		List<String> listaNomeColunas = tipoObraDAO.getColumnNames();
		
		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);
		
		List<TipoObra> listaTipoObra = tipoObraDAO.listAll();
		
		conteudo = new Object[listaTipoObra.size()][];
		conteudo[0] = new Object[listaNomeColunas.size()];
	}

	@Override
	public int getColumnCount() {
		if(conteudo.length == 0)
			return 0;
		else
			return conteudo[0].length;
	}

	@Override
	public int getRowCount() {
		return conteudo.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return conteudo[row][col];
	}

	@Override
	public String getColumnName(int index) {
		return nomeColunas[index];
	}
}
