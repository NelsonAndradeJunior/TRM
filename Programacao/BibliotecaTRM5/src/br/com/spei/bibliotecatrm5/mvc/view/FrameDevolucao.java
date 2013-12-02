package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.peer.FramePeer;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;

import br.com.spei.bibliotecatrm5.mvc.control.DevolucaoControl;
import br.com.spei.bibliotecatrm5.mvc.control.EmprestimoControl;
import br.com.spei.bibliotecatrm5.mvc.control.UsuarioPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.EmprestimoTableModel;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.ExemplarPesquisaTableModel;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class FrameDevolucao extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnPesquisarUsuario;
	private BufferedImage picLupa;
	private JTable tblExemplares;
	private JScrollPane spnExemplares;
	private boolean listenersAdicionados;
	private FrameUsuarioPesquisa frameUsuarioPesquisa;
	private Devolucao model;

	
	public FrameDevolucao() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 600, 310);
		this.setTitle("Realizar Devolução");
		this.setName("frmDevolucao");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
	
		lblUsuario = getLabelUsuario();
		txtUsuario = getTextUsuario();
		btnConfirmar = getButtonConfirmar();
		btnCancelar = getButtonCancelar();
		btnPesquisarUsuario = getButtonPesquisa("btnPesquisarUsuario", "PesquisarUsuario");
		
		try {
			tblExemplares = getTableExemplares();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao tentar carregar o formulário", "Erro", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		spnExemplares = getScrollPane(tblExemplares);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.WEST, lblUsuario, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtUsuario, 0, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtUsuario, 10, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.WEST, spnExemplares, 0, SpringLayout.WEST, lblUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, spnExemplares, 10, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, spnExemplares, -10, SpringLayout.EAST, getContentPane());
		layoutManager.putConstraint(SpringLayout.SOUTH, spnExemplares, 200, SpringLayout.NORTH, spnExemplares);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnConfirmar, 10, SpringLayout.SOUTH, spnExemplares);
		layoutManager.putConstraint(SpringLayout.WEST, btnConfirmar, 0, SpringLayout.WEST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnConfirmar);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnConfirmar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarUsuario, 0, SpringLayout.SOUTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarUsuario, 0, SpringLayout.NORTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarUsuario, 5, SpringLayout.EAST, txtUsuario);
		
		this.setLayout(layoutManager);

		this.getContentPane().add(lblUsuario);
		this.getContentPane().add(txtUsuario);
		this.getContentPane().add(btnConfirmar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisarUsuario);
		this.getContentPane().add(spnExemplares);
	}
	
	@Override
	public void setVisible(boolean b) {
		if(!b)
			this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSED);
		super.setVisible(b);
	}
	
	private JTable getTableExemplares() throws SQLException {
		TableModel model = getTableModel();
		
		if(model == null)
			return null;
		
		JTable tabela = new JTable(model);
		
		tabela.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		tabela.setName("tblExemplares");
		ajustaColunas(tabela);
		
		return tabela;
	}

	private void ajustaColunas(JTable tabela) {
		
		if(tabela.getColumnCount() <= 0)
			return;
		
		tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
	}

	private TableModel getTableModel() throws SQLException {
		return new EmprestimoTableModel();
	}

	private JScrollPane getScrollPane(JTable tabela) {
		return new JScrollPane(tabela);
		}

	private JButton getButtonPesquisa(String nomeBotao, String actionCommand) {
		picLupa = getPictureLupa();
		JButton botao = new JButton(new ImageIcon(picLupa.getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH)));
		botao.setName(nomeBotao);
		botao.setActionCommand(actionCommand);
		return botao;
	}

	private JButton getButtonConfirmar() {
		JButton botao = new JButton("Confirmar");
		botao.setName("btnConfirmar");
		botao.setActionCommand("Confirmar");
		return botao;
	}

	private JTextField getTextUsuario() {
		JTextField campoTexto = new JTextField(30);
		campoTexto.setName("txtUsuario");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelUsuario() {
		JLabel label = new JLabel("Usuário:");
		label.setName("lblUsuario");
		return label;
	}
	
	private BufferedImage getPictureLupa() {
		BufferedImage imagem = null;
		
		try {
			imagem = ImageIO.read(new File("recursos\\Magnifier-icon.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return imagem;
	}
	
	private JButton getButtonCancelar() {
		JButton botao = new JButton("Cancelar");
		botao.setName("btnCancelar");
		botao.setActionCommand("Cancelar");
		return botao;
	}	
	
	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	public boolean adicionouListeners() {
		return listenersAdicionados;
	}

	public void adicionaActionListeners(ActionListener listener) {
		this.btnConfirmar.addActionListener(listener);
		this.btnCancelar.addActionListener(listener);
		this.btnPesquisarUsuario.addActionListener(listener);
	}

	public void adicionaMouseListeners(MouseListener listener) {
		this.tblExemplares.addMouseListener(listener);
	}

	public void adicionaInternalFrameListeners(InternalFrameListener listener) {
		this.addInternalFrameListener(listener);
	}

	public void limpaTela(ArrayList<Emprestimo> listaEmprestimos) {
		this.txtUsuario.setText("");
		atualizaTabela(listaEmprestimos, tblExemplares);
	}
	
	public void atualizaTabela(List<Emprestimo> listaEmprestimo, JTable tabela) {		
		int totalLinhas = 0;
		
		for (Emprestimo emprestimo : listaEmprestimo) {
			totalLinhas += emprestimo.getExemplares().size();
		}
		
		EmprestimoTableModel tableModel = (EmprestimoTableModel)tabela.getModel();
		
		tableModel.setRowCount(totalLinhas);
		
		for (Emprestimo emprestimo : listaEmprestimo) {
			List<Exemplar> exemplares = emprestimo.getExemplares(); 
			for (int i = 0; i < exemplares.size(); i++) {
				tableModel.setValueAt(exemplares.get(i).getCodExemplar(), i, 0);
				tableModel.setValueAt(exemplares.get(i).getObra().getNomeObra(), i, 1);
				tableModel.setValueAt(emprestimo.getDataEmprestimo(), i, 2);
			}
		}
	}

	private FrameUsuarioPesquisa getFrameUsuarioPesquisa() throws SQLException {
		return new FrameUsuarioPesquisa();
	}
	
	public void mostraFramePesquisaUsuario() {
		boolean adicionaListeners = frameUsuarioPesquisa == null;
		
		if(frameUsuarioPesquisa == null) {
			try {
				frameUsuarioPesquisa = getFrameUsuarioPesquisa();
				((JDesktopPane)this.getParent()).add(frameUsuarioPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		UsuarioPesquisaControl controladorUsuarioPesquisa = new UsuarioPesquisaControl(frameUsuarioPesquisa, this.getName());
		controladorUsuarioPesquisa.inicia(adicionaListeners);
		
		controladorUsuarioPesquisa.carregaInformacoes();
	}

	public void setUsuarioModel(Usuario usuario) {
		if(this.model == null)
			this.model = new Devolucao();
		
		this.model.setUsuario(usuario);
	}

	public void preencheCampoUsuario() {
		this.txtUsuario.setText(this.model.getUsuario().getNomeUsuario() + " " + this.model.getUsuario().getSobrenomeUsuario());
	}

	public void listaItensTabelaEmprestimos() {
		DevolucaoControl controladorDevolucao = new DevolucaoControl(this, this.model);
		List<Emprestimo> emprestimos = null;
		try {
			emprestimos = controladorDevolucao.listAllEmprestimosUsuario(model.getUsuario());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao executar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		this.atualizaTabela(emprestimos, tblExemplares);
	}

	public void atualizaTabelaEmprestimos(ArrayList<Emprestimo> listaEmprestimos) {
		atualizaTabela(listaEmprestimos, tblExemplares);
	}

	public boolean validaDevolucao() {
		return validaUsuario() && validaItensDevolucao();
	}

	private boolean validaItensDevolucao() {
		if(tblExemplares.getSelectedRowCount() <= 0) {
			mostraMensagem("Não há nenhum item selecionado para devolução.");
			return false;
		}
		
		return true;			
	}

	private boolean validaUsuario() {
		if(txtUsuario.getText().isEmpty()) {
			mostraMensagem("O usuário não foi selecionado.");
			return false;
		}
	
		return true;
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public Devolucao getModel() {
		return this.model;
	}

	public List<Integer> getCodigoExemplaresSelecionados() {
		List<Integer> listaCodExemplaresSelecionados = new ArrayList<>();
		
		for (int i = 0; i < tblExemplares.getSelectedRows().length; i++) {
			int codigo = (int)tblExemplares.getValueAt(tblExemplares.getSelectedRows()[i], 0);
			listaCodExemplaresSelecionados.add(codigo);
		}
		
		return listaCodExemplaresSelecionados;
	}

	public void mostraErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void mostraMensagemAlerta(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem, "Atenção", JOptionPane.WARNING_MESSAGE);
	}
}
