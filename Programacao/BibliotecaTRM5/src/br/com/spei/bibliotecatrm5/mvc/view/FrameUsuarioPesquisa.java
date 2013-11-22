package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;
import br.com.spei.bibliotecatrm5.mvc.model.UsuarioPesquisaTableModel;

public class FrameUsuarioPesquisa extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel lblPesquisar;
	private JTextField txtPesquisar;
	private JButton btnPesquisar;
	private JTable tblDados;
	private JScrollPane spnDados;
	
	public FrameUsuarioPesquisa() throws SQLException {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() throws SQLException {
		this.setBounds(100, 100, 400, 200);
		this.setTitle("Pesquisa de Usuário");
		this.setName("frmUsuarioPesquisa");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		lblPesquisar = getLabelPesquisar();
		txtPesquisar = getTextPesquisar();
		btnPesquisar = getBotaoPesquisar();
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
		return new UsuarioPesquisaTableModel();
	}
	
	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	private JScrollPane getScrollPaneDados() {
		return new JScrollPane(tblDados);
	}
	
	private JButton getBotaoPesquisar() {
		JButton botao = new JButton("Pesquisar");
		botao.setActionCommand("Pesquisar");
		return botao;
	}

	private JTextField getTextPesquisar() {
		return new JTextField(10);
	}

	private JLabel getLabelPesquisar() {
		return new JLabel("Pesquisar:");
	}

	public void configuraOuvinteAcao(
			ActionListener listener) {
		this.btnPesquisar.addActionListener(listener);
	}

	public void configuraOuvinteFrame(
			InternalFrameListener listener) {
		this.addInternalFrameListener(listener);
	}

	public void atualizaTabela(List<Usuario> listaUsuarios) {
		UsuarioPesquisaTableModel tableModel = (UsuarioPesquisaTableModel)tblDados.getModel();
		tableModel.setRowCount(listaUsuarios.size());
		for (int i = 0; i < listaUsuarios.size(); i++) {
			tableModel.setValueAt(listaUsuarios.get(i).getCodUsuario(), i, 0);
			tableModel.setValueAt(listaUsuarios.get(i).getNomeUsuario() + " " + listaUsuarios.get(i).getSobrenomeUsuario(), i, 1);
		}
	}

	public void mostraMensagemErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public String getTextoPesquisa() {
		return this.txtPesquisar.getText();
	}

	public void limpaTela() {
		this.txtPesquisar.setText("");
	}

	public void mostraMensagemErro(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void configuraMouseListener(
			MouseListener listener) {
		this.tblDados.addMouseListener(listener);
	}
}
