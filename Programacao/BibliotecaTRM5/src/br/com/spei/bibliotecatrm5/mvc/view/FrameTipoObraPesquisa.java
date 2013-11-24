package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;


import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObraPesquisaTableModel;

public class FrameTipoObraPesquisa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblPesquisar;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JTable tblDados;
	private JScrollPane spnDados;
	
	public FrameTipoObraPesquisa() throws SQLException {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() throws SQLException {
		this.setBounds(100, 100, 560, 200);
		this.setTitle("Pesquisa de Tipo de Obra");
		this.setName("frmTipoObraPesquisa");
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
	
	private JScrollPane getScrollPaneDados() {
		JScrollPane scrollPane = new JScrollPane(tblDados);
		
		return scrollPane;
	}

	private JTable getTableDados()  throws SQLException{
		
		TableModel model = getTableModel();
		
		if(model == null)
			return null;
		
		JTable tabela = new JTable(model);
		tabela.setName("tblDados");
		ajustaColunas(tabela);
		return tabela;
	}

	private void ajustaColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(60);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
	}

	private TableModel getTableModel() throws SQLException {
		return new TipoObraPesquisaTableModel();
	}

	private JTextField getTextPesquisar() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtPesquisa");
		return campoTexto;
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}
	
	private JLabel getLabelPesquisar() {
		JLabel label = new JLabel("Pesquisa:");
		label.setName("lblPesquisa");
		return label;
	}
	
	private JButton getButtonPesquisar() {
		JButton botao = new JButton("Pesquisar");
		botao.setName("btnPesquisar");
		botao.setActionCommand("pesquisar");
		return botao;
	}
	
	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnPesquisar.addActionListener(actionListener);
	}
	
	public void configuraOuvinteMouse(MouseListener mouseListener) {
		tblDados.addMouseListener(mouseListener);
	}

	public String getTextoPesquisa() {
		return txtPesquisar.getText();
	}

	public void disparaExcecaoSQL(SQLException excecao) {
		// TODO Melhorar a mensagem
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar fazer a operação.");
	}

	public void atualizaTabela(List<TipoObra> listaTipoObra) {
		TipoObraPesquisaTableModel tableModel = (TipoObraPesquisaTableModel)tblDados.getModel();
		tableModel.setRowCount(listaTipoObra.size());
		for (int i = 0; i < listaTipoObra.size(); i++) {
			tableModel.setValueAt(listaTipoObra.get(i).getCodTipoObra(), i, 0);
			tableModel.setValueAt(listaTipoObra.get(i).getDescricaoTipoObra(), i, 1);
			tableModel.setValueAt(listaTipoObra.get(i).isDicionario(), i, 2);
			tableModel.setValueAt(listaTipoObra.get(i).isEnciclopedia(), i, 3);
			tableModel.setValueAt(listaTipoObra.get(i).isPeriodico(), i, 4);
		}
	}

	public void configuraOuvinteFrame(InternalFrameListener frameListener) {
		this.addInternalFrameListener(frameListener);
	}

	public void disparaExcecao(Exception e) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar repassar o valor.");
	}

	public void limpaTexto() {
		this.txtPesquisar.setText("");
	}
}
