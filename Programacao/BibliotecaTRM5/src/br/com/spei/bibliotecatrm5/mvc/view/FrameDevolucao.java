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

public class FrameDevolucao extends JInternalFrame{
	
	private static final long serialVersionUID = 1L;
	private JLabel lblMatUsuario;
	private JTextField txtMatUsuario;
	private JLabel lblCatUsuario;
	private JTextField txtCatUsuario;
	private JLabel lblDtDevolucao;
	private JTextField txtDtDevolucao;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private BufferedImage picLupa;

	
	public FrameDevolucao() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 320, 160);
		this.setTitle("Realizar Devolução");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		
		SpringLayout layoutManager = getLayoutManager();
	
		lblMatUsuario = getLabelMatUsuario();
		txtMatUsuario = getTextMatUsuario();
		lblCatUsuario = getLabelCatUsuario();
		txtCatUsuario = getTextCatUsuario();
		lblDtDevolucao = getLabelDtDevolucao();
		txtDtDevolucao = getTextDtDevolucao();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisar = getButtonPesquisa();
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblMatUsuario, 10, SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.EAST, lblMatUsuario, 0, SpringLayout.EAST, lblDtDevolucao);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtMatUsuario, 0, SpringLayout.SOUTH, lblMatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtMatUsuario, 10, SpringLayout.EAST, lblMatUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblCatUsuario, 10, SpringLayout.SOUTH, lblMatUsuario);
		layoutManager.putConstraint(SpringLayout.EAST, lblCatUsuario, 0, SpringLayout.EAST, lblDtDevolucao);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtCatUsuario, 0, SpringLayout.SOUTH, lblCatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, txtCatUsuario, 10, SpringLayout.EAST, lblCatUsuario);
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblDtDevolucao, 10, SpringLayout.SOUTH, lblCatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, lblDtDevolucao, 15, SpringLayout.WEST, getContentPane());
		
		layoutManager.putConstraint(SpringLayout.SOUTH, txtDtDevolucao, 0, SpringLayout.SOUTH, lblDtDevolucao);
		layoutManager.putConstraint(SpringLayout.WEST, txtDtDevolucao, 10, SpringLayout.EAST, lblDtDevolucao);

		layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 10, SpringLayout.SOUTH, lblDtDevolucao);
		layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0, SpringLayout.WEST, lblDtDevolucao);
		layoutManager.putConstraint(SpringLayout.EAST, btnGravar, 0, SpringLayout.EAST, lblDtDevolucao);
		
		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, txtDtDevolucao);
		layoutManager.putConstraint(SpringLayout.WEST, btnCancelar, 10, SpringLayout.EAST, btnGravar);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtMatUsuario);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisar, 0, SpringLayout.NORTH, txtMatUsuario);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 5, SpringLayout.EAST, txtMatUsuario);	
		
		this.setLayout(layoutManager);

		this.getContentPane().add(lblMatUsuario);
		this.getContentPane().add(txtMatUsuario);
		this.getContentPane().add(lblCatUsuario);
		this.getContentPane().add(txtCatUsuario);
		this.getContentPane().add(lblDtDevolucao);
		this.getContentPane().add(txtDtDevolucao);
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
	
	private JTextField getTextMatUsuario() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtMatUsuario");
		return campoTexto;
	}

	private JLabel getLabelMatUsuario() {
		JLabel label = new JLabel("Matrícula Usuário:");
		label.setName("lblMatUsuario");
		return label;
	}
	
	private JTextField getTextDtDevolucao() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtDtDevolucao");
		return campoTexto;
	}

	private JLabel getLabelDtDevolucao() {
		JLabel label = new JLabel("Data de Devolução:");
		label.setName("lblDtDevolucao");
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
