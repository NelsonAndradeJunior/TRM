package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.control.TipoObraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class FrameTipoObra extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// TODO Ajustar para não repassar o model
	
	private JLabel lblCodigoTipoObra;
	private JTextField txtCodigoTipoObra;
	private JLabel lblDescricaoTipoObra;
	private JTextField txtDescricaoTipoObra;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private JLabel lblTiposEspeciais;
	private JComboBox<String> cmbTiposEspeciais;
	private BufferedImage picLupa;
	private FrameTipoObraPesquisa frameTipoObraPesquisa;
	private boolean modoAtualizacao;
	private TipoObra model;

	private boolean listenersAdicionados;
	
	public FrameTipoObra() {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() {
		this.setBounds(50, 50, 310, 155);
		this.setTitle("Cadastro de Tipos de Obra");
		this.setName("frmTipoObra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
			
		lblCodigoTipoObra = getLabelCodTipoObra();
		txtCodigoTipoObra = getTextCodigoObra();
		lblDescricaoTipoObra = getLabelDescTipoObra();
		txtDescricaoTipoObra = getTextDescTipoObra();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisar = getButtonPesquisar();
		cmbTiposEspeciais = getComboTiposEspeciais();
		lblTiposEspeciais = getLabel("Tipos Especiais:", "lblTiposEspeciais");
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblCodigoTipoObra, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.EAST, lblCodigoTipoObra, 0, SpringLayout.EAST, lblTiposEspeciais);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtCodigoTipoObra, 0, SpringLayout.SOUTH, lblCodigoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtCodigoTipoObra, 10, SpringLayout.EAST, lblCodigoTipoObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblDescricaoTipoObra, 10, SpringLayout.SOUTH, lblCodigoTipoObra);
		layoutManager.putConstraint(SpringLayout.EAST, lblDescricaoTipoObra, 0, SpringLayout.EAST, lblTiposEspeciais);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtDescricaoTipoObra, 0, SpringLayout.SOUTH, lblDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtDescricaoTipoObra, 10, SpringLayout.EAST, lblDescricaoTipoObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblTiposEspeciais, 10, SpringLayout.SOUTH, lblDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, lblTiposEspeciais, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, cmbTiposEspeciais, 4, SpringLayout.SOUTH, lblTiposEspeciais);
		layoutManager.putConstraint(SpringLayout.WEST, cmbTiposEspeciais, 10, SpringLayout.EAST, lblTiposEspeciais);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 10, SpringLayout.SOUTH, lblTiposEspeciais);
		layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0, SpringLayout.WEST, lblTiposEspeciais);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnCancelar, 0, SpringLayout.SOUTH, btnGravar);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnGravar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtCodigoTipoObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisar, 0, SpringLayout.NORTH, txtCodigoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 5, SpringLayout.EAST, txtCodigoTipoObra);		
		
		this.setLayout(layoutManager);
		
		this.getContentPane().add(lblCodigoTipoObra);
		this.getContentPane().add(txtCodigoTipoObra);
		this.getContentPane().add(lblDescricaoTipoObra);
		this.getContentPane().add(txtDescricaoTipoObra);
		this.getContentPane().add(btnGravar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisar);
		this.getContentPane().add(lblTiposEspeciais);
		this.getContentPane().add(cmbTiposEspeciais);
	}

	private JLabel getLabel(String texto, String name) {
		JLabel label = new JLabel(texto);
		label.setName(name);
		return label;
	}

	private JComboBox<String> getComboTiposEspeciais() {
		String[] conteudo = new String[] { "Selecione", "Dicionário", "Enciclopédia", "Periódico", "Nenhum" };
		JComboBox<String> comboBox = new JComboBox<String>(conteudo);
		comboBox.setName("cmbTiposEspeciais");
		return comboBox;
	}

	private JTextField getTextCodigoObra() {
		JTextField campoTexto = new JTextField(3);
		campoTexto.setName("txtCodigoObra");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelCodTipoObra() {
		JLabel label = new JLabel("Código:");
		label.setName("lblCodigo");
		return label;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSED);
	}
	
	private FrameTipoObraPesquisa getFramePesquisa() throws SQLException {
		frameTipoObraPesquisa = new FrameTipoObraPesquisa();
		
		return frameTipoObraPesquisa;
	}

	private JButton getButtonPesquisar() {
		picLupa = getPictureLupa();
		JButton botao = new JButton(new ImageIcon(picLupa.getScaledInstance(8, 8, BufferedImage.SCALE_SMOOTH)));
		botao.setName("btnPesquisar");
		botao.setActionCommand("pesquisar");
		return botao;
	}

	private BufferedImage getPictureLupa() {
		BufferedImage imagem = null;
		
		try {
			imagem = ImageIO.read(new File("recursos\\Magnifier-icon.png"));
		} catch (IOException e) {
			// TODO Tratar
			e.printStackTrace();
		}
		
		return imagem;
	}
	
	private JButton getButtonCancelar() {
		JButton botao = new JButton("Cancelar");
		botao.setName("btnCancelar");
		botao.setActionCommand("cancelar");
		return botao;
	}

	private JButton getButtonGravar() {
		JButton botao = new JButton("Gravar");
		botao.setName("btnGravar");
		botao.setActionCommand("gravar");
		return botao;
	}

	private JTextField getTextDescTipoObra() {
		JTextField campoTexto = new JTextField(16);
		campoTexto.setName("txtDescTipoObra");
		return campoTexto;
	}

	private JLabel getLabelDescTipoObra() {
		JLabel label = new JLabel("Descrição:");
		label.setName("lblDescTipoObra");
		return label;
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
		btnGravar.addActionListener(actionListener);
		btnPesquisar.addActionListener(actionListener);
	}

	public void mostraFormTipoObraPesquisa() {
		boolean adicionaListeners = frameTipoObraPesquisa == null;
		
		if(frameTipoObraPesquisa == null) {
			try {
				frameTipoObraPesquisa = getFramePesquisa();
				((JDesktopPane)this.getParent()).add(frameTipoObraPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
		TipoObraPesquisaControl controladorObraPesquisa = new TipoObraPesquisaControl(frameTipoObraPesquisa, this.getName());
		controladorObraPesquisa.inicia(adicionaListeners);
		
		controladorObraPesquisa.carregaInformacoes();
	}

	public void configuraOuvinteInternalFrame(InternalFrameListener listener) {
		this.addInternalFrameListener(listener);
	}

	public TipoObra getModel() {
		return this.model;
	}

	public void disparaExcecaoSQL(SQLException e1) {
		// TODO melhorar mensagem
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar fazer a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void limpaTela() {
		this.txtCodigoTipoObra.setText("");
		this.txtDescricaoTipoObra.setText("");
		this.cmbTiposEspeciais.setSelectedIndex(0);
	}

	public void setModoAtualizacao(boolean modoAtualizacao) {
		this.modoAtualizacao = modoAtualizacao;
	}
	
	public boolean getModoAtualizacao() {
		return modoAtualizacao;
	}

	public void mostraMensagem(String mensagem) {
		// TODO Melhorar - Tipos de mensagem
		JOptionPane.showMessageDialog(null, mensagem);		
	}

	public int confirmaAtualizacao() {
		int retorno = JOptionPane.showConfirmDialog(null, "Deseja modificar a descrição do tipo da obra?", "Confirmar Atualização", JOptionPane.YES_NO_CANCEL_OPTION);
		return retorno;
	}

	public void setModel(TipoObra model) {
		this.model = model;
	}

	public String getDescricaoTipoObra() {
		return txtDescricaoTipoObra.getText();
	}

	public boolean adicionouListeners() {
		return this.listenersAdicionados;
	}
	
	public void setListenersAdicionados(boolean listenersAdicionados) {
		this.listenersAdicionados = listenersAdicionados;
	}

	public boolean ehDicionario() {
		return cmbTiposEspeciais.getSelectedIndex() == 1;
	}

	public boolean ehEnciclopedia() {
		return cmbTiposEspeciais.getSelectedIndex() == 2;
	}

	public boolean ehPeriodico() {
		return cmbTiposEspeciais.getSelectedIndex() == 3;
	}

	public boolean ValidaCamposPreenchidos() {
		return !txtDescricaoTipoObra.getText().isEmpty() && cmbTiposEspeciais.getSelectedIndex() > 0;
	}

	public void preencheComboTiposEspeciais(TipoObra model) {
		if(model.isDicionario())
			cmbTiposEspeciais.setSelectedIndex(1);
		else if(model.isEnciclopedia())
			cmbTiposEspeciais.setSelectedIndex(2);
		else if(model.isPeriodico())
			cmbTiposEspeciais.setSelectedIndex(3);
		else
			cmbTiposEspeciais.setSelectedIndex(4);
	}

	public void preencheInformacoes(TipoObra model) {
		this.txtCodigoTipoObra.setText(((Integer)model.getCodTipoObra()).toString());
		this.txtDescricaoTipoObra.setText(model.getDescricaoTipoObra());
		preencheComboTiposEspeciais(model);
	}
}
