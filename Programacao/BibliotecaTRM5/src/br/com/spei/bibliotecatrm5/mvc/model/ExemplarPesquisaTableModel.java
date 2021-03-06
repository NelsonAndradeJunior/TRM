package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAOImpl;

public class ExemplarPesquisaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Object[][] conteudo; 
	String[] nomeColunas;
	
	public ExemplarPesquisaTableModel() throws SQLException {
		super();
		getConteudoTabela();
	}
	
	private void getConteudoTabela() throws SQLException {
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		List<String> listaNomeColunas = exemplarDAO.getColumnNames();
		
		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);
		
		List<Exemplar> listaExemplares = exemplarDAO.listAll();
		
		conteudo = new Object[listaExemplares.size()][];
		conteudo[0] = new Object[listaNomeColunas.size()];
		
		List<Object> linhas = new ArrayList<>();
		
		for (Exemplar exemplar : listaExemplares) {
			Object[] linha = new Object[listaNomeColunas.size()];
			linha[0] = exemplar.getCodExemplar();
			linha[1] = exemplar.getObra().getNomeObra();
			linha[2] = exemplar.getNumeroExemplar();
			
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
		
		int columnCount = rowCount > 0 ? nomeColunas.length : getColumnCount();
		
		Object[][] copiaConteudo = new Object[rowCount][columnCount];
		
		for (int i = 0; i < copiaConteudo.length; i++) {
			if(i >= conteudo.length)
				break;
			copiaConteudo[i] = conteudo[i];
		}
		
		conteudo = copiaConteudo;
		
		fireTableDataChanged();
	}
}
