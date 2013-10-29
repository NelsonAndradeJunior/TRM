package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

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

	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		super("Biblioteca TRM");
		inicializa();
	}

	private void inicializa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(800, 600);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);

		setJMenuBar(getBarraMenu());
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
		
		itemMenuCadTipoObra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Implementar chamada do form de cadastro de obras
				
			}
		});
		
		return itemMenuCadTipoObra;
	}
}
