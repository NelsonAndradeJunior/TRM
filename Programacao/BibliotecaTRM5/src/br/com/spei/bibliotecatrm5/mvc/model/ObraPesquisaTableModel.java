package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAOImpl;

public class ObraPesquisaTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Object[][] conteudo; 
	String[] nomeColunas;
	
	public ObraPesquisaTableModel() throws SQLException {
		super();
		getConteudoTabela();
	}
	
	private void getConteudoTabela() throws SQLException {
		ObraDAO obraDAO = new ObraDAOImpl();
		List<String> listaNomeColunas = obraDAO.getColumnNames();
		
		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);
		
		List<Obra> listaObras = obraDAO.listAll();
		
		conteudo = new Object[listaObras.size()][];
		conteudo[0] = new Object[listaNomeColunas.size()];
		
		List<Object> linhas = new ArrayList<>();
		
		for (Obra obra : listaObras) {
			Object[] linha = new Object[listaNomeColunas.size()];
			linha[0] = obra.getIdObra();
			linha[1] = obra.getNomeObra();
			linha[2] = obra.getAno();
			linha[3] = obra.getAutor().getNomeAutor();
			linha[4] = obra.getEditora().getNomeEditora();
			linha[5] = obra.getTipoObra().getDescricaoTipoObra();
			linha[6] = obra.isClassico();
			
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
	
	@Override
	public Class<? extends Object> getColumnClass(int column) {
		return getValueAt(0, column).getClass();
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
