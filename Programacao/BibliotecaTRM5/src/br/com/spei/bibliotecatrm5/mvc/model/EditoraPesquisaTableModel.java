package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.EditoraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EditoraDAOImpl;

public class EditoraPesquisaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Object[][] conteudo; 
	String[] nomeColunas;
	
	public EditoraPesquisaTableModel() throws SQLException {
		super();
		getConteudoTabela();
	}
	
	private void getConteudoTabela() throws SQLException {
		EditoraDAO editoraDAO = new EditoraDAOImpl();
		List<String> listaNomeColunas = editoraDAO.getColumnNames();
		
		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);
		
		List<Editora> listaEditoras = editoraDAO.listAll();
		
		conteudo = new Object[listaEditoras.size()][];
		conteudo[0] = new Object[listaNomeColunas.size()];
		
		List<Object> linhas = new ArrayList<>();
		
		for (Editora editor : listaEditoras) {
			Object[] linha = new Object[listaNomeColunas.size()];
			linha[0] = editor.getCodEditora();
			linha[1] = editor.getNomeEditora();
			
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
