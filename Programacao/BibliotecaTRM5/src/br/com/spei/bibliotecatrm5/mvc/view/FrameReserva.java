package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.InternalFrameEvent;

import br.com.spei.bibliotecatrm5.mvc.control.ExemplarPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.control.ObraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.control.UsuarioPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.Reserva;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;

public class FrameReserva extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblObra;
	private JTextField txtObra;
	private JTextField txtExemplar;
	private JButton btnReservar;
	private JButton btnCancelar;
	private JButton btnPesquisarUsuario;
	private JButton btnPesquisarExemplar;
	private BufferedImage picLupa;
	private FrameUsuarioPesquisa frameUsuarioPesquisa;
	private FrameExemplarPesquisa frameExemplarPesquisa; 
	private boolean listenersAdicionados;
	private Reserva model;
	
	public FrameReserva() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 510, 130);
		this.setTitle("Reserva de Obra");
		this.setName("frmReserva");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
		
		lblUsuario = getLabelUsuario();
		txtUsuario = getTextUsuario();
		lblObra = getLabelObra();
		txtObra = getTextObra();
		txtExemplar = getTextExemplar();
		btnReservar = getButtonReservar();
		btnCancelar = getButtonCancelar();
		btnPesquisarUsuario = getButtonPesquisa("btnPesquisarUsuario", "PesquisarUsuario");
		btnPesquisarExemplar = getButtonPesquisa("btnPesquisarExemplar", "PesquisarExemplar");
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.WEST, lblUsuario, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtUsuario, 0, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtUsuario, 10, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblObra, 10, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, lblObra, 0, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtObra, 0, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtObra, 10, SpringLayout.EAST, lblObra);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtExemplar, 0, SpringLayout.SOUTH, txtObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtExemplar, 5, SpringLayout.EAST, txtObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnReservar, 10, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnReservar, 0, SpringLayout.WEST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnReservar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarUsuario, 0, SpringLayout.SOUTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarUsuario, 0, SpringLayout.NORTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarUsuario, 5, SpringLayout.EAST, txtUsuario);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarExemplar, 0, SpringLayout.SOUTH, txtExemplar);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarExemplar, 0, SpringLayout.NORTH, txtExemplar);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarExemplar, 5, SpringLayout.EAST, txtExemplar);	
		
		this.setLayout(layoutManager);
		
		this.getContentPane().add(lblUsuario);
		this.getContentPane().add(txtUsuario);
		this.getContentPane().add(lblObra);
		this.getContentPane().add(txtObra);
		this.getContentPane().add(txtExemplar);
		this.getContentPane().add(btnReservar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisarUsuario);
		this.getContentPane().add(btnPesquisarExemplar);
	}
	
	private JTextField getTextExemplar() {
		JTextField campoTexto = new JTextField(3);
		campoTexto.setEditable(false);
		campoTexto.setName("txtExemplar");
		return campoTexto;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSED);
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

	private JButton getButtonReservar() {
		JButton botao = new JButton("Reservar");
		botao.setName("btnReservar");
		botao.setActionCommand("Reservar");
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
	
	private JTextField getTextObra() {
		JTextField campoTexto = new JTextField(30);
		campoTexto.setName("txtObra");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelObra() {
		JLabel label = new JLabel("Obra:");
		label.setName("lblObra");
		return label;
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}
	
	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
		btnReservar.addActionListener(actionListener);
		btnPesquisarExemplar.addActionListener(actionListener);
		btnPesquisarUsuario.addActionListener(actionListener);
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

	public boolean adicionouListeners() {
		return this.listenersAdicionados;
	}

	public void setListenersAdicionados(boolean listenersAdicionados) {
		this.listenersAdicionados = listenersAdicionados;
	}

	public void setUsuarioModel(Usuario usuario) {
		if(this.model == null)
			this.model = new Reserva();
		
		this.model.setUsuario(usuario);
	}

	public void preencheCampoUsuario() {
		this.txtUsuario.setText(model.getUsuario().getNomeUsuario() + " " + model.getUsuario().getSobrenomeUsuario());
	}

	public void mostraFramePesquisaObra() {
		boolean adicionaListeners = frameExemplarPesquisa == null;
		
		if(frameExemplarPesquisa == null) {
			try {
				frameExemplarPesquisa = getFrameExemplarPesquisa();
				((JDesktopPane)this.getParent()).add(frameExemplarPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		ExemplarPesquisaControl controladorExemplarPesquisa = new ExemplarPesquisaControl(frameExemplarPesquisa, this.getName());
		controladorExemplarPesquisa.inicia(adicionaListeners);
		
		controladorExemplarPesquisa.carregaInformacoes();
	}

	private FrameExemplarPesquisa getFrameExemplarPesquisa() throws SQLException {
		return new FrameExemplarPesquisa();
	}

	public void preencheInformacoesExemplar() {
		this.txtObra.setText(model.getExemplar().getObra().getNomeObra());
		this.txtExemplar.setText(((Integer)model.getExemplar().getNumeroExemplar()).toString());
	}

	public void setExemplarModel(Exemplar exemplar) {
		if(this.model == null)
			model = new Reserva();
		
		model.setExemplar(exemplar);
	}

	public boolean validaCamposPreenchidos() {
		return !this.txtUsuario.getText().isEmpty() &&
				!this.txtObra.getText().isEmpty() &&
				!this.txtExemplar.getText().isEmpty();
	}

	public Reserva getModel() {
		return this.model;
	}

	public void limpaTela() {
		this.txtUsuario.setText("");
		this.txtObra.setText("");
		this.txtExemplar.setText("");
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void mostraErroSQL(SQLException e1) {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}
}
