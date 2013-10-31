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

public class FrameObra extends JInternalFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblAutorObra;
	private JTextField txtAutorObra;
	private JLabel lblDtFabricacao;
	private JTextField txtDtFabricacao;
	private JLabel lblEditora;
	private JTextField txtEditora;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisar;
	private BufferedImage picLupa;
	
	public FrameObra() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 300, 180);
		this.setTitle("Cadastro de Obra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
	
	
	SpringLayout layoutManager = getLayoutManager();
	
	
	lblAutorObra = getLabelAutorObra();
	txtAutorObra = getTextAutorObra();
	lblDtFabricacao = getLabelDtFabricacao();
	txtDtFabricacao = getTextDtFabricacao();
	lblEditora = getLabelEditora();
	txtEditora = getTextEditora();
	btnGravar = getButtonGravar();
	btnCancelar = getButtonCancelar();
	btnPesquisar = getButtonPesquisa();
	
	layoutManager.putConstraint(SpringLayout.NORTH, lblAutorObra, 10, SpringLayout.NORTH, getContentPane());
	layoutManager.putConstraint(SpringLayout.EAST, lblAutorObra, 0, SpringLayout.EAST, lblDtFabricacao);
	
	layoutManager.putConstraint(SpringLayout.SOUTH, txtAutorObra, 0, SpringLayout.SOUTH, lblAutorObra);
	layoutManager.putConstraint(SpringLayout.WEST, txtAutorObra, 10, SpringLayout.EAST, lblAutorObra);
	
	layoutManager.putConstraint(SpringLayout.NORTH, lblDtFabricacao, 10, SpringLayout.SOUTH, lblAutorObra);
	layoutManager.putConstraint(SpringLayout.WEST, lblDtFabricacao, 10, SpringLayout.WEST, getContentPane());
	
	layoutManager.putConstraint(SpringLayout.SOUTH, txtDtFabricacao, 0, SpringLayout.SOUTH, lblDtFabricacao);
	layoutManager.putConstraint(SpringLayout.WEST, txtDtFabricacao, 10, SpringLayout.EAST, lblDtFabricacao);
	
	layoutManager.putConstraint(SpringLayout.NORTH, lblEditora, 10, SpringLayout.SOUTH, lblDtFabricacao);
	layoutManager.putConstraint(SpringLayout.EAST, lblEditora, 0, SpringLayout.EAST, lblDtFabricacao);
	
	layoutManager.putConstraint(SpringLayout.SOUTH, txtEditora, 0, SpringLayout.SOUTH, lblEditora);
	layoutManager.putConstraint(SpringLayout.WEST, txtEditora, 10, SpringLayout.EAST, lblEditora);
	
	layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 10, SpringLayout.SOUTH, lblEditora);
	layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0, SpringLayout.WEST, lblEditora);
	
	layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10, SpringLayout.SOUTH, txtEditora);
	layoutManager.putConstraint(SpringLayout.EAST, btnCancelar, 0, SpringLayout.EAST, txtEditora);
	
	layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisar, 0, SpringLayout.SOUTH, txtAutorObra);
	layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisar, 0, SpringLayout.NORTH, txtAutorObra);
	layoutManager.putConstraint(SpringLayout.WEST, btnPesquisar, 5, SpringLayout.EAST, txtAutorObra);	
	
	
	this.setLayout(layoutManager);
	
	this.getContentPane().add(lblAutorObra);
	this.getContentPane().add(txtAutorObra);
	this.getContentPane().add(lblDtFabricacao);
	this.getContentPane().add(txtDtFabricacao);
	this.getContentPane().add(lblEditora);
	this.getContentPane().add(txtEditora);
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
	
	private JTextField getTextAutorObra() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtAutorObra");
		return campoTexto;
	}

	private JLabel getLabelAutorObra() {
		JLabel label = new JLabel("Autor:");
		label.setName("lblAutorObra");
		return label;
	}
	
	private JTextField getTextDtFabricacao() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtDtFabricacao");
		return campoTexto;
	}

	private JLabel getLabelDtFabricacao() {
		JLabel label = new JLabel("Data Fabricação:");
		label.setName("lblDtFabricacao");
		return label;
	}
	
	private JTextField getTextEditora() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtEditora");
		return campoTexto;
	}

	private JLabel getLabelEditora() {
		JLabel label = new JLabel("Editora:");
		label.setName("lblEditora");
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
