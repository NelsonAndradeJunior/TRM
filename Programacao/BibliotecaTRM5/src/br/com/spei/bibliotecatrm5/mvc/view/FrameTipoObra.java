package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.control.TipoObraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class FrameTipoObra extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblDescricaoTipoObra;
	private JTextField txtDescricaoTipoObra;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private BufferedImage picLupa;
	private FrameTipoObraPesquisa frameTipoObraPesquisa;
	private boolean modoAtualizacao;
	private TipoObra model;
	
	public FrameTipoObra() {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() {
		this.setBounds(50, 50, 260, 120);
		this.setTitle("Cadastro de Tipos de Obra");
		this.setName("frmTipoObraPesquisa");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
				
		lblDescricaoTipoObra = getLabelDescTipoObra();
		txtDescricaoTipoObra = getTextDescTipoObra();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisar = getButtonPesquisar();
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblDescricaoTipoObra, 20, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.WEST, lblDescricaoTipoObra, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtDescricaoTipoObra, 0, SpringLayout.SOUTH, lblDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtDescricaoTipoObra, 10, SpringLayout.EAST, lblDescricaoTipoObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, txtDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.EAST, btnCancelar, 0, SpringLayout.EAST, txtDescricaoTipoObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 0, SpringLayout.NORTH, btnCancelar);
		layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0, SpringLayout.WEST, lblDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.EAST, btnGravar, -10, SpringLayout.WEST, btnCancelar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisar, 0, SpringLayout.NORTH, txtDescricaoTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 5, SpringLayout.EAST, txtDescricaoTipoObra);		
		
		this.setLayout(layoutManager);
		
		this.getContentPane().add(lblDescricaoTipoObra);
		this.getContentPane().add(txtDescricaoTipoObra);
		this.getContentPane().add(btnGravar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisar);
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
		JTextField campoTexto = new JTextField(10);
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
		
		TipoObraPesquisaControl controladorObraPesquisa = new TipoObraPesquisaControl(frameTipoObraPesquisa);
		controladorObraPesquisa.inicia(adicionaListeners);
		
		controladorObraPesquisa.carregaInformacoes();
	}

	public void preencheCampoTexto() {
		this.txtDescricaoTipoObra.setText(this.model.getDescricaoTipoObra());
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

	public void limpaTexto() {
		txtDescricaoTipoObra.setText("");
	}

	public void setModoAtualizacao(boolean modoAtualizacao) {
		this.modoAtualizacao = modoAtualizacao;
	}
	
	public boolean getModoAtualizacao() {
		return modoAtualizacao;
	}

	public void mostraMensagem(String mensagem) {
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
}
