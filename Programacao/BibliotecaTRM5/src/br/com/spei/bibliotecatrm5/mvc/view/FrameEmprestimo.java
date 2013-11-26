package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpringLayout;
import javax.swing.event.InternalFrameListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import br.com.spei.bibliotecatrm5.mvc.control.UsuarioPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.ExemplarPesquisaTableModel;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObraPesquisaTableModel;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class FrameEmprestimo extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JButton btnConfirmar;
	private JButton btnCancelar;
	private JButton btnPesquisarUsuario;
	private BufferedImage picLupa;
	private JTable tblExemplaresDisponiveis;
	private JScrollPane spnExemplaresDisponiveis;
	private JTable tblItensEmprestimo;
	private JScrollPane spnItensEmprestimo;
	private FrameUsuarioPesquisa frameUsuarioPesquisa;
	private boolean listenersAdicionados;
	private Emprestimo model;
	
	public FrameEmprestimo() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 600, 400);
		this.setTitle("Empréstimo de Obra");
		this.setName("frmEmprestimo");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
		
		lblUsuario = getLabelUsuario();
		txtUsuario = getTextUsuario();
		btnConfirmar = getButtonConfirmar();
		btnCancelar = getButtonCancelar();
		btnPesquisarUsuario = getButtonPesquisa("btnPesquisarUsuario", "PesquisarUsuario");
		
		try {
			tblExemplaresDisponiveis = getTableExemplaresDisponiveis();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao tentar carregar o formulário", "Erro", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		spnExemplaresDisponiveis = getScrollPane(tblExemplaresDisponiveis);
		
		try {
			tblItensEmprestimo = getTableItensEmprestimo();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Ocorreu um problema ao tentar carregar o formulário", "Erro", JOptionPane.ERROR_MESSAGE);
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		
		spnItensEmprestimo = getScrollPane(tblItensEmprestimo);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.WEST, lblUsuario, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtUsuario, 0, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtUsuario, 10, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.WEST, spnExemplaresDisponiveis, 0, SpringLayout.WEST, lblUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, spnExemplaresDisponiveis, 10, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, spnExemplaresDisponiveis, -10, SpringLayout.EAST, getContentPane());
		layoutManager.putConstraint(SpringLayout.SOUTH, spnExemplaresDisponiveis, 120, SpringLayout.NORTH, spnExemplaresDisponiveis);
		
		layoutManager.putConstraint(SpringLayout.WEST, spnItensEmprestimo, 0, SpringLayout.WEST, spnExemplaresDisponiveis);
		layoutManager.putConstraint(SpringLayout.NORTH, spnItensEmprestimo, 10, SpringLayout.SOUTH, spnExemplaresDisponiveis);
		layoutManager.putConstraint(SpringLayout.EAST, spnItensEmprestimo, -10, SpringLayout.EAST, getContentPane());
		layoutManager.putConstraint(SpringLayout.SOUTH, spnItensEmprestimo, -10, SpringLayout.NORTH, btnConfirmar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnConfirmar, -10, SpringLayout.SOUTH, getContentPane());
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
		this.getContentPane().add(spnExemplaresDisponiveis);
		this.getContentPane().add(spnItensEmprestimo);
		this.getContentPane().add(btnPesquisarUsuario);
	}
	
	private JTable getTableItensEmprestimo() throws SQLException {
		TableModel model = getTableModel();
		
		if(model == null)
			return null;
		
		JTable tabela = new JTable(model);
		
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabela.setName("tblItensEmprestimo");
		ajustaColunas(tabela);
		return tabela;
	}

	private JScrollPane getScrollPane(JTable tabela) {
		return new JScrollPane(tabela);
	}

	private JTable getTableExemplaresDisponiveis() throws SQLException {
		TableModel model = getTableModel();
		
		if(model == null)
			return null;
		
		JTable tabela = new JTable(model);
		
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		tabela.setName("tblExemplaresDisponiveis");
		ajustaColunas(tabela);
		return tabela;
	}

	private void ajustaColunas(JTable tabela) {
		tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
		tabela.getColumnModel().getColumn(1).setPreferredWidth(220);
		tabela.getColumnModel().getColumn(2).setPreferredWidth(15);
	}

	private TableModel getTableModel() throws SQLException {
		return new ExemplarPesquisaTableModel();
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}
	
	public void atualizaTabela(List<Exemplar> listaExemplares, JTable tabela) {
		ExemplarPesquisaTableModel tableModel = (ExemplarPesquisaTableModel)tabela.getModel();
		tableModel.setRowCount(listaExemplares.size());
		for (int i = 0; i < listaExemplares.size(); i++) {
			tableModel.setValueAt(listaExemplares.get(i).getCodExemplar(), i, 0);
			tableModel.setValueAt(listaExemplares.get(i).getObra().getNomeObra(), i, 1);
			tableModel.setValueAt(listaExemplares.get(i).getNumeroExemplar(), i, 2);
		}
	}
	
	private JButton getButtonPesquisa(String nomeBotao, String actionCommand) {
		picLupa = getPictureLupa();
		JButton botao = new JButton(new ImageIcon(picLupa.getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH)));
		botao.setName(nomeBotao);
		botao.setActionCommand(actionCommand);
		return botao;
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
	
	public void mostraErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}
	
	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
		btnConfirmar.addActionListener(actionListener);
		btnPesquisarUsuario.addActionListener(actionListener);
	}
	
	public void configuraOuvinteFrame(InternalFrameListener listener) {
		this.addInternalFrameListener(listener);
	}
	
	public void setListenersAdicionados(boolean listenersAdicionados) {
		this.listenersAdicionados = listenersAdicionados;
	}

	public int obtemCodigoItemSelecionado() {
		ExemplarPesquisaTableModel tableModel = (ExemplarPesquisaTableModel)tblExemplaresDisponiveis.getModel();
		
		int retorno = Integer.parseInt(tableModel.getValueAt(tblExemplaresDisponiveis.getSelectedRow(), 0).toString());
		
		return retorno;		
	}

	public void setMouseListener(MouseListener listener) {
		this.tblExemplaresDisponiveis.addMouseListener(listener);
		this.tblItensEmprestimo.addMouseListener(listener);
	}

	public void atualizaTabelaItensEmprestimo(List<Exemplar> exemplares) {
		atualizaTabela(exemplares, tblItensEmprestimo);
	}

	public void atualizaTabelaExemplaresDisponiveis(
			List<Exemplar> exemplares) {
		atualizaTabela(exemplares, tblExemplaresDisponiveis);
	}

	public boolean validaEmprestimo() {
		return validaUsuario() && validaItensEmprestimo();
	}
	
	private boolean validaItensEmprestimo() {
		if(tblItensEmprestimo.getRowCount() <= 0) {
			mostraMensagem("Não há nenhum item selecionado para empréstimo.");
			return false;
		}
		
		return true;			
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	private boolean validaUsuario() {
		if(txtUsuario.getText().isEmpty()) {
			mostraMensagem("O usuário não foi selecionado.");
			return false;
		}
	
		return true;
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
	
	private FrameUsuarioPesquisa getFrameUsuarioPesquisa() throws SQLException {
		return new FrameUsuarioPesquisa();
	}

	public void setUsuarioModel(Usuario usuario) {
		if(this.model == null)
			this.model = new Emprestimo();
		
		this.model.setUsuario(usuario);
	}

	public void preencheCampoUsuario() {
		this.txtUsuario.setText(this.model.getUsuario().getNomeUsuario() + " " + this.model.getUsuario().getSobrenomeUsuario());
	}

	public Emprestimo getModel() {
		return this.model;
	}

	public void limpaTela(List<Exemplar> exemplaresDisponiveis) {
		this.txtUsuario.setText("");
		this.atualizaTabela(exemplaresDisponiveis, tblExemplaresDisponiveis);
		this.atualizaTabela(new ArrayList<Exemplar>(), tblItensEmprestimo);
	}
}
