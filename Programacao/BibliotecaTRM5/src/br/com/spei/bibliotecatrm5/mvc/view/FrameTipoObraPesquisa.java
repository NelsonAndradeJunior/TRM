package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
	
	public FrameTipoObraPesquisa() {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() {
		this.setBounds(100, 100, 320, 120);
		this.setTitle("Pesquisa de Tipo de Obra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		lblPesquisar = getLabelPesquisar();
		txtPesquisar = getTextPesquisar();
		btnPesquisar = getButtonPesquisar();
		tblDados = getTableDados();
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
		
		getContentPane().add(lblPesquisar);
		getContentPane().add(txtPesquisar);
		getContentPane().add(btnPesquisar);
		getContentPane().add(spnDados);		
	}
	
	private JScrollPane getScrollPaneDados() {
		JScrollPane scrollPane = new JScrollPane(tblDados);
		
		return scrollPane;
	}

	private JTable getTableDados() {
		DefaultTableModel model = getTabelModel();
		JTable tabela = new JTable(model);
		tabela.setName("tblDados");
		
		// TODO Definir tamanhos
		model.addColumn("Código");
		model.addColumn("Descrição");
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(20);
		tabela.getColumnModel().getColumn(0).setWidth(20);
		tabela.getColumnModel().getColumn(1).setWidth(20);
		
		return tabela;
	}

	private DefaultTableModel getTabelModel() {
		return new DefaultTableModel();
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
}
