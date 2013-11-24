package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.ObraPesquisaTableModel;

public class FrameObraPesquisa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblPesquisar;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JTable tblDados;
	private JScrollPane spnDados;	
	
	public FrameObraPesquisa() throws SQLException {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() throws SQLException {
		this.setBounds(100, 100, 900, 400);
		this.setTitle("Pesquisa de Obra");
		this.setName("frmObraPesquisa");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		lblPesquisar = getLabelPesquisar();
		txtPesquisar = getTextPesquisar();
		btnPesquisar = getButtonPesquisar();
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

	private TableModel getTableModel() throws SQLException {
		return new ObraPesquisaTableModel();
	}

	private void ajustaColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setMinWidth(0);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(5);
		tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
		tabela.getColumnModel().getColumn(6).setPreferredWidth(20);
	}
	
	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	private JScrollPane getScrollPaneDados() {
		JScrollPane scrollPane = new JScrollPane(tblDados);
		
		return scrollPane;
	}

	private JButton getButtonPesquisar() {
		JButton botao = new JButton("Pesquisar");
		botao.setName("btnPesquisar");
		botao.setActionCommand("pesquisar");
		return botao;
	}

	private JTextField getTextPesquisar() {
		JTextField campoTexto = new JTextField(30);
		campoTexto.setName("txtPesquisa");
		return campoTexto;
	}

	private JLabel getLabelPesquisar() {
		JLabel label = new JLabel("Pesquisa:");
		label.setName("lblPesquisa");
		return label;
	}

	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnPesquisar.addActionListener(actionListener);
	}

	public void atualizaTabela(List<Obra> listaObra) {
		ObraPesquisaTableModel tableModel = (ObraPesquisaTableModel)tblDados.getModel();
		tableModel.setRowCount(listaObra.size());
		for (int i = 0; i < listaObra.size(); i++) {
			tableModel.setValueAt(listaObra.get(i).getIdObra(), i, 0);
			tableModel.setValueAt(listaObra.get(i).getNomeObra(), i, 1);
			tableModel.setValueAt(listaObra.get(i).getAno(), i, 2);
			tableModel.setValueAt(listaObra.get(i).getAutor().getNomeAutor(), i, 3);
			tableModel.setValueAt(listaObra.get(i).getEditora().getNomeEditora(), i, 4);
			tableModel.setValueAt(listaObra.get(i).getTipoObra().getDescricaoTipoObra(), i, 5);
			tableModel.setValueAt(listaObra.get(i).isClassico(), i, 6);
		}
	}

	public void mostraMensagemErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void mostraMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void limpaTela() {
		txtPesquisar.setText("");
	}

	public void configuraOuvinteMouse(MouseListener mouseListener) {
		tblDados.addMouseListener(mouseListener);
	}

	public String getTextoPesquisa() {
		return txtPesquisar.getText();
	}
}
