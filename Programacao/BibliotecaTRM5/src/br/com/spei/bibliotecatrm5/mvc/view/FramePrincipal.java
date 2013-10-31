package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import br.com.spei.bibliotecatrm5.mvc.control.TipoObraControl;

public class FramePrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;
	private JMenuBar barraMenu;
	private JMenu menuCadastro;
	private JMenuItem itemMenuCadObra;
	private JMenuItem itemMenuCadTipoObra;
	private FrameTipoObra frameTipoObra;

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		super("Biblioteca TRM");
		inicializa();
	}

	private void inicializa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);

		setJMenuBar(getBarraMenu());
		desktopPane.add(getFrameTipoObra());
	}

	private FrameTipoObra getFrameTipoObra() {
		frameTipoObra = new FrameTipoObra();
		
		return frameTipoObra;
	}

	private JMenuBar getBarraMenu() {
		barraMenu = new JMenuBar();
		barraMenu.add(getMenuCadastro());

		return barraMenu;
	}

	private JMenu getMenuCadastro() {
		menuCadastro = new JMenu("Cadastros");
		menuCadastro.setMnemonic(KeyEvent.VK_C);		
		menuCadastro.add(getItemMenuCadastroObra());
		menuCadastro.add(getItemMenuCadastroTipoObra());
		
		return menuCadastro;
	}

	private JMenuItem getItemMenuCadastroObra() {
		itemMenuCadObra = new JMenuItem("Obra", KeyEvent.VK_O);
		
		itemMenuCadObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Implementar chamada do form de cadastro de obras
			}
		});
		
		return itemMenuCadObra;
	}
	
	private JMenuItem getItemMenuCadastroTipoObra() {
		itemMenuCadTipoObra = new JMenuItem("Tipo Obra", KeyEvent.VK_T);
		
		itemMenuCadTipoObra.setActionCommand("MenuCadastroTipoObra");
		
		return itemMenuCadTipoObra;
	}

	public void configuraOuvinte(ActionListener actionListener) {
		itemMenuCadObra.addActionListener(actionListener);
		itemMenuCadTipoObra.addActionListener(actionListener);
	}
	
	public void mostraFormCadastroTipoObra() {	
		TipoObraControl controladorTipoObra = new TipoObraControl(frameTipoObra);
		controladorTipoObra.inicia();
	}
}
