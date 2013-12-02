package br.com.spei.bibliotecatrm5.mvc.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAOImpl;

public class EmprestimoTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Object[][] conteudo;
	String[] nomeColunas;

	public EmprestimoTableModel() throws SQLException {
		super();
		getConteudoTabela();
	}

	private void getConteudoTabela() throws SQLException {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAOImpl();
		List<String> listaNomeColunas = getColumnNames();

		nomeColunas = new String[listaNomeColunas.size()];
		listaNomeColunas.toArray(nomeColunas);

		List<Emprestimo> listaEmprestimos = emprestimoDAO.listAll();

		int totalExemplares = 0;
		
		for (Emprestimo emprestimo : listaEmprestimos) {
			totalExemplares += emprestimo.getExemplares().size();
		}	
		
		if(totalExemplares == 0)
			totalExemplares = 1;
		
		conteudo = new Object[totalExemplares][];		
		conteudo[0] = new Object[listaNomeColunas.size()];

		List<Object> linhas = new ArrayList<>();
		
		if(listaEmprestimos.size() <= 0) {
			Object[] linha = new Object[listaNomeColunas.size()];
			
			linha[0] = new Object();
			linha[1] = new Object();
			linha[2] = new Object();
			
			linhas.add(linha);
		} else {
			for (Emprestimo emprestimo : listaEmprestimos ) {
				List<Exemplar> listaExemplares = emprestimo.getExemplares();
				for (Exemplar exemplar : listaExemplares) {
					Object[] linha = new Object[listaNomeColunas.size()];
					linha[0] = exemplar.getCodExemplar();
					linha[1] = exemplar.getObra().getNomeObra();
					linha[2] = emprestimo.getDataEmprestimo();
					
					linhas.add(linha);
				}
			}
		}
		
		linhas.toArray(conteudo);
	}

	private List<String> getColumnNames() {
		List<String> listaNomes = new ArrayList<>();
		listaNomes.add("Código Exemplar");
		listaNomes.add("Obra");
		listaNomes.add("Data");
		
		return listaNomes;
	}

	@Override
	public int getColumnCount() {
		if (conteudo.length == 0)
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

//	@Override
//	public Class<? extends Object> getColumnClass(int column) {
//		return getValueAt(0, column).getClass();
//	}
	
	@Override
	public String getColumnName(int index) {
		return nomeColunas[index];
	}

	public void setRowCount(int rowCount) {

		int columnCount = rowCount > 0 ? nomeColunas.length : getColumnCount();

		Object[][] copiaConteudo = new Object[rowCount][columnCount];

		for (int i = 0; i < copiaConteudo.length; i++) {
			if (i >= conteudo.length)
				break;
			copiaConteudo[i] = conteudo[i];
		}

		conteudo = copiaConteudo;

		fireTableDataChanged();
	}
}
