package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.spei.bibliotecatrm5.mvc.control.ObraControl;
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
	private FrameObra frameObra;

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
		desktopPane.add(getFrameObra());
	}

	private FrameTipoObra getFrameTipoObra() {
		frameTipoObra = new FrameTipoObra();
		
		return frameTipoObra;
	}
	
	private FrameObra getFrameObra(){
		frameObra = new FrameObra();
		
		return frameObra;
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
		
		itemMenuCadObra.setActionCommand("MenuCadastroObra");
		
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
	
	public void mostraFormCadastroObra() {	
		ObraControl controladorObra = new ObraControl(frameObra);
		controladorObra.inicia();
	}
}
