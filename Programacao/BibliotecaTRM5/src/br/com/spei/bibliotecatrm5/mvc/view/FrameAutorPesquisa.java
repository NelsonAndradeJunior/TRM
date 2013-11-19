package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.AutorPesquisaTableModel;

public class FrameAutorPesquisa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblPesquisar;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JTable tblDados;
	private JScrollPane spnDados;
	
	public FrameAutorPesquisa() throws SQLException {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() throws SQLException {
		this.setBounds(100, 100, 400, 200);
		this.setTitle("Pesquisa de Autor");
		this.setName("frmAutorPesquisa");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		lblPesquisar = getLabelPesquisar();
		txtPesquisar = getTextPesquisar();
		btnPesquisar = getBotaPesquisar();
		tblDados = getTableDados();
		
		if(tblDados == null)
			return;
		
		spnDados = getScrollPaneDados();
		
		SpringLayout layoutManager = getLayoutManager();
		
		this.setLayout(layoutManager);
		
		layoutManager.putConstraint(SpringLayout.WEST, lblPesquisar, 10, SpringLayout.WEST, getContentPane());
		layoutManager.putConstraint(SpringLayout.NORTH, lblPesquisar, 20, SpringLayout.NORTH, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.WEST, txtPesquisar, 10, SpringLayout.EAST, lblPesquisar);
		layoutManager.putConstraint(SpringLayout.SOUTH, txtPesquisar, 0, SpringLayout.SOUTH, lblPesquisar);
		
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 10, SpringLayout.EAST, txtPesquisar);
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtPesquisar);
		
		layoutManager.putConstraint(SpringLayout.WEST, tblDados, 0, SpringLayout.WEST, lblPesquisar);
		layoutManager.putConstraint(SpringLayout.NORTH, tblDados, 10, SpringLayout.SOUTH, lblPesquisar);
		
		layoutManager.putConstraint(SpringLayout.WEST, spnDados, 0, SpringLayout.WEST, tblDados);
		layoutManager.putConstraint(SpringLayout.NORTH, spnDados, 0, SpringLayout.NORTH, tblDados);
		layoutManager.putConstraint(SpringLayout.EAST, spnDados, -10, SpringLayout.EAST, getContentPane());
		layoutManager.putConstraint(SpringLayout.SOUTH, spnDados, -10, SpringLayout.SOUTH, getContentPane());
		
		getContentPane().add(lblPesquisar);
		getContentPane().add(txtPesquisar);
		getContentPane().add(btnPesquisar);
		getContentPane().add(spnDados);
	}

	private JTable getTableDados() throws SQLException {
		TableModel model = getTableModel();
		
		if(model == null)
			return null;
		
		JTable tabela = new JTable(model);
		tabela.setName("tblDados");
		ajustaColunas(tabela);
		return tabela;
	}

	private void ajustaColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
	}

	private TableModel getTableModel() throws SQLException {
		return new AutorPesquisaTableModel();
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	private JScrollPane getScrollPaneDados() {
		return new JScrollPane(tblDados);
	}

	private JButton getBotaPesquisar() {
		JButton botao = new JButton("Pesquisar");
		botao.setActionCommand("pesquisar");
		return botao;
	}

	private JTextField getTextPesquisar() {
		return new JTextField(10);
	}

	private JLabel getLabelPesquisar() {
		return new JLabel("Pesquisar:");
	}

	public void configuraActionListener(ActionListener listener) {
		btnPesquisar.addActionListener(listener);
	}

	public void configuraMouseListener(MouseListener listener) {
		tblDados.addMouseListener(listener);
	}

	public String getTextoPesquisa() {
		return txtPesquisar.getText();
	}

	public void atualizaTabela(List<Autor> listaAutor) {
		AutorPesquisaTableModel tableModel = (AutorPesquisaTableModel)tblDados.getModel();
		tableModel.setRowCount(listaAutor.size());
		for (int i = 0; i < listaAutor.size(); i++) {
			tableModel.setValueAt(listaAutor.get(i).getCodAutor(), i, 0);
			tableModel.setValueAt(listaAutor.get(i).getNomeAutor(), i, 1);
		}
	}

	public void mostraMensagemErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void limpaTela() {
		txtPesquisar.setText("");
	}

	public void mostraMensagemErro(String mensagem) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, mensagem);
	}
}
