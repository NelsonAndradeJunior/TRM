package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

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
	
	public FrameTipoObra() {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() {
		this.setBounds(50, 50, 260, 120);
		this.setTitle("Cadastro de Tipos de Obra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);
		
		SpringLayout layoutManager = getLayoutManager();
				
		lblDescricaoTipoObra = getLabelDescTipoObra();
		txtDescricaoTipoObra = getTextDescTipoObra();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisar = getButtonPesquisa();
		
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

//	public void configuraOuvinteFoco(FocusListener focusListener) {
//		txtCodigoTipoObra.addFocusListener(focusListener);
//	}
}
