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

public class FrameReserva extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblCatUsuario;
	private JTextField txtCatUsuario;
	private JLabel lblNomeUsuario;
	private JTextField txtNomeUsuario;
	private JLabel lblDtReserva;
	private JTextField txtDtReserva;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private BufferedImage picLupa;
	
	public FrameReserva() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 320, 160);
		this.setTitle("Cadastro de Reserva");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		
		SpringLayout layoutManager = getLayoutManager();
	
		
		
		
		lblCatUsuario = getLabelCatUsuario();
		txtCatUsuario = getTextCatUsuario();
		lblNomeUsuario = getLabelNomeUsuario();
		txtNomeUsuario = getTextNomeUsuario();
		lblDtReserva = getLabelDtReserva();
		txtDtReserva = getTextDtReserva();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisar = getButtonPesquisa();
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblCatUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.EAST, lblCatUsuario, 0, SpringLayout.EAST, lblDtReserva);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtCatUsuario, 0, SpringLayout.SOUTH, lblCatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtCatUsuario, 10, SpringLayout.EAST, lblCatUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblNomeUsuario, 10, SpringLayout.SOUTH, lblCatUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, lblNomeUsuario, 0, SpringLayout.EAST, lblDtReserva);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtNomeUsuario, 0, SpringLayout.SOUTH, lblNomeUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtNomeUsuario, 10, SpringLayout.EAST, lblNomeUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblDtReserva, 10, SpringLayout.SOUTH, lblNomeUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, lblDtReserva, 20, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtDtReserva, 0, SpringLayout.SOUTH, lblDtReserva);
		layoutManager.putConstraint(SpringLayout.WEST, txtDtReserva, 10, SpringLayout.EAST, lblDtReserva);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 10, SpringLayout.SOUTH, lblDtReserva);
		layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0, SpringLayout.WEST, lblDtReserva);
		layoutManager.putConstraint(SpringLayout.EAST, btnGravar, 0, SpringLayout.EAST, lblDtReserva);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, txtDtReserva);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnGravar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtCatUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisar, 0, SpringLayout.NORTH, txtCatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 5, SpringLayout.EAST, txtCatUsuario);	
		
		this.setLayout(layoutManager);
		
		
		this.getContentPane().add(lblCatUsuario);
		this.getContentPane().add(txtCatUsuario);
		this.getContentPane().add(lblNomeUsuario);
		this.getContentPane().add(txtNomeUsuario);
		this.getContentPane().add(lblDtReserva);
		this.getContentPane().add(txtDtReserva);
		this.getContentPane().add(btnGravar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisar);
		
	}
	
	private JButton getButtonPesquisa() {
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
	
	private JTextField getTextCatUsuario() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtCatUsuario");
		return campoTexto;
	}

	private JLabel getLabelCatUsuario() {
		JLabel label = new JLabel("Categoria Usuário:");
		label.setName("lblCatUsuario");
		return label;
	}
	
	private JTextField getTextNomeUsuario() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtNomeUsuario");
		return campoTexto;
	}

	private JLabel getLabelNomeUsuario() {
		JLabel label = new JLabel("Nome Usuário:");
		label.setName("lblNomeUsuario");
		return label;
	}
	
	private JTextField getTextDtReserva() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtDtReserva");
		return campoTexto;
	}

	private JLabel getLabelDtReserva() {
		JLabel label = new JLabel("Data de Reserva:");
		label.setName("lblDtReserva");
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
}
