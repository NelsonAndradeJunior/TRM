package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.InternalFrameEvent;

public class FrameReserva extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblUsuario;
	private JTextField txtUsuario;
	private JLabel lblObra;
	private JTextField txtObra;
	private JButton btnReservar;
	private JButton btnCancelar;
	private JButton btnPesquisarUsuario;
	private JButton btnPesquisarObra;
	private BufferedImage picLupa;
	
	public FrameReserva() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 470, 130);
		this.setTitle("Reserva de Obra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
			
		SpringLayout layoutManager = getLayoutManager();
		
		lblUsuario = getLabelUsuario();
		txtUsuario = getTextUsuario();
		lblObra = getLabelObra();
		txtObra = getTextObra();
		btnReservar = getButtonReservar();
		btnCancelar = getButtonCancelar();
		btnPesquisarUsuario = getButtonPesquisa("btnPesquisarUsuario", "PesquisarUsuario");
		btnPesquisarObra = getButtonPesquisa("btnPesquisarObra", "PesquisarObra");
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.WEST, lblUsuario, 10, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtUsuario, 0, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtUsuario, 10, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblObra, 10, SpringLayout.SOUTH, lblUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, lblObra, 0, SpringLayout.EAST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtObra, 0, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtObra, 10, SpringLayout.EAST, lblObra);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnReservar, 10, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnReservar, 0, SpringLayout.WEST, lblUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, lblObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnReservar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarUsuario, 0, SpringLayout.SOUTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarUsuario, 0, SpringLayout.NORTH, txtUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarUsuario, 5, SpringLayout.EAST, txtUsuario);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarObra, 0, SpringLayout.SOUTH, txtObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarObra, 0, SpringLayout.NORTH, txtObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarObra, 5, SpringLayout.EAST, txtObra);	
		
		this.setLayout(layoutManager);
		
		
		this.getContentPane().add(lblUsuario);
		this.getContentPane().add(txtUsuario);
		this.getContentPane().add(lblObra);
		this.getContentPane().add(txtObra);
		this.getContentPane().add(btnReservar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisarUsuario);
		this.getContentPane().add(btnPesquisarObra);
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
		JLabel label = new JLabel("Usu�rio:");
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
		btnPesquisarObra.addActionListener(actionListener);
		btnPesquisarUsuario.addActionListener(actionListener);
	}
}
