package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAOImpl;

public class AutorPesquisaTableModel extends AbstractTableModel implements
		TableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Object[][] conteudo; 
	String[] nomeColunas;
	
	public AutorPesquisaTableModel() throws SQLException {
		super();
		getConteudoTabela();
	}
	
	private void getConteudoTabela() throws SQLException {
		AutorDAO autorDAO = new AutorDAOImpl();
		List<String> listaNomeColunas = autorDAO.getColumnNames();
		
		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);
		
		List<Autor> listaAutores = autorDAO.listAll();
		
		conteudo = new Object[listaAutores.size()][];
		conteudo[0] = new Object[listaNomeColunas.size()];
		
		List<Object> linhas = new ArrayList<>();
		
		for (Autor autor : listaAutores) {
			Object[] linha = new Object[listaNomeColunas.size()];
			linha[0] = autor.getCodAutor();
			linha[1] = autor.getNomeAutor();
			
			linhas.add(linha);
		}
		
		linhas.toArray(conteudo);
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
	public void setValueAt(Object value, int row, int col) {
		conteudo[row][col] = value;
		fireTableCellUpdated(row, col);
	}
	
	@Override
	public String getColumnName(int index) {
		return nomeColunas[index];
	}
	
	public void setRowCount(int rowCount) {
		Object[][] copiaConteudo = new Object[rowCount][this.getColumnCount()];
		
		for (int i = 0; i < copiaConteudo.length; i++) {
			if(i >= conteudo.length)
				break;
			copiaConteudo[i] = conteudo[i];
		}
		
		conteudo = copiaConteudo;
		fireTableDataChanged();
	}
}
