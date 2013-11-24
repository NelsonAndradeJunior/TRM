package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.ExemplarPesquisaTableModel;

public class FrameExemplarPesquisa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblPesquisar;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JTable tblDados;
	private JScrollPane spnDados;	
	
	public FrameExemplarPesquisa() throws SQLException {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() throws SQLException {
		this.setBounds(100, 100, 400, 200);
		this.setTitle("Pesquisa de Exemplar");
		this.setName("frmObraExemplar");
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
		return new ExemplarPesquisaTableModel();
	}

	private void ajustaColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
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

	public void atualizaTabela(List<Exemplar> listaExemplares) {
		ExemplarPesquisaTableModel tableModel = (ExemplarPesquisaTableModel)tblDados.getModel();
		tableModel.setRowCount(listaExemplares.size());
		for (int i = 0; i < listaExemplares.size(); i++) {
			tableModel.setValueAt(listaExemplares.get(i).getCodExemplar(), i, 0);
			tableModel.setValueAt(listaExemplares.get(i).getObra().getNomeObra(), i, 1);
			tableModel.setValueAt(listaExemplares.get(i).getNumeroExemplar(), i, 2);
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

	public void configuraActionListener(
			ActionListener listener) {
		this.btnPesquisar.addActionListener(listener);
	}

	public void configuraMouseListener(
			MouseListener listener) {
		this.tblDados.addMouseListener(listener);
	}

}
