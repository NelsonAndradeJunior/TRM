package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.*;

import javax.swing.*;

public class FrameTipoObra extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel lblCodigoTipoObra;
	private JTextField txtCodigoTipoObra;
	private JLabel lblDescricaoTipoObra;
	private JTextField txtDescricaoTipoObra;

	public FrameTipoObra() {
		super("", false, true, false, true);
		inicializa();
	}

	private void inicializa() {
		this.setSize(200, 150);
		this.setTitle("Cadastro de Tipos de Obra");
		
		this.setLayout(getLayoutManager());
		
		this.getContentPane().add(getLabelCodTipoObra());
		this.getContentPane().add(getTextCodTipoObra());
		this.getContentPane().add(getLabelDescTipoObra());
		this.getContentPane().add(getTextlDescTipoObra());
	}

	private Component getTextlDescTipoObra() {
		txtDescricaoTipoObra = new JTextField(10);
		
		return txtDescricaoTipoObra;
	}

	private Component getLabelDescTipoObra() {
		lblDescricaoTipoObra = new JLabel("Descrição:");
		
		
		return lblDescricaoTipoObra;
	}

	private JTextField getTextCodTipoObra() {
		txtCodigoTipoObra = new JTextField(10);
		
		return txtCodigoTipoObra;
	}

	private JLabel getLabelCodTipoObra() {
		lblCodigoTipoObra = new JLabel("Código:");
		
		return lblCodigoTipoObra;
	}

	private LayoutManager getLayoutManager() {
		return new FlowLayout();
	}
}
