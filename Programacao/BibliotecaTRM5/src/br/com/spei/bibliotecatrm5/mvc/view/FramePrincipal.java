package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.GraphicsEnvironment;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import br.com.spei.bibliotecatrm5.mvc.control.DevolucaoControl;
import br.com.spei.bibliotecatrm5.mvc.control.EmprestimoControl;
import br.com.spei.bibliotecatrm5.mvc.control.ObraControl;
import br.com.spei.bibliotecatrm5.mvc.control.ReservaControl;
import br.com.spei.bibliotecatrm5.mvc.control.TipoObraControl;

public class FramePrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JDesktopPane desktopPane;
	private JMenuBar barraMenu;
	private JMenu menuCadastro;
	private JMenu reserva;
	private JMenu devolucao;
	private JMenuItem itemMenuReservaObra;
	private JMenuItem itemMenuCadObra;
	private JMenuItem itemMenuCadTipoObra;
	private JMenuItem itemMenuCadEmprestimo;
	private JMenuItem itemMenuCadDevolucao;
	private FrameTipoObra frameTipoObra;
	private FrameObra frameObra;
	private FrameEmprestimo frameEmprestimo;
	private FrameReserva frameReserva;
	private FrameDevolucao frameDevolucao;

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
		desktopPane.add(getFrameEmprestimo());
		desktopPane.add(getFrameReservaO());
		desktopPane.add(getFrameDevolucao());
	}

	private FrameTipoObra getFrameTipoObra() {
		frameTipoObra = new FrameTipoObra();
		
		return frameTipoObra;
	}
	
	private FrameObra getFrameObra(){
		frameObra = new FrameObra();
		
		return frameObra;
	}
	
	private FrameEmprestimo getFrameEmprestimo(){
		frameEmprestimo = new FrameEmprestimo();
		
		return frameEmprestimo;
	}
	
	private FrameReserva getFrameReservaO(){
		frameReserva = new FrameReserva();
		
		return frameReserva;
	}
	
	private FrameDevolucao getFrameDevolucao(){
		frameDevolucao = new FrameDevolucao();
		
		return frameDevolucao;
	}
	
	

	private JMenuBar getBarraMenu() {
		barraMenu = new JMenuBar();
		barraMenu.add(getMenuCadastro());
		barraMenu.add(getFrameReserva());
		barraMenu.add(getFrameDevolucaoObra());

		return barraMenu;
	}

	private JMenu getMenuCadastro() {
		menuCadastro = new JMenu("Cadastros");
		menuCadastro.setMnemonic(KeyEvent.VK_C);		
		menuCadastro.add(getItemMenuCadastroObra());
		menuCadastro.add(getItemMenuCadastroTipoObra());
		menuCadastro.add(getItemMenuCadastroEmprestimo());
		
		return menuCadastro;
	}
	
	private JMenu getFrameReserva(){
		reserva = new JMenu("Reserva");
		reserva.setMnemonic(KeyEvent.VK_R);
		reserva.add(getItemMenuCadastroReserva());
		
		return reserva;
		
	}
	
	private JMenu getFrameDevolucaoObra(){
		devolucao = new JMenu("Devolucao");
		devolucao.setMnemonic(KeyEvent.VK_D);
		devolucao.add(getItemMenuCadastroDevolucao());
		
		return devolucao;
		
	}
	
	private JMenuItem getItemMenuCadastroReserva(){
		itemMenuReservaObra = new JMenuItem("Cadastrar Reserva", KeyEvent.VK_0);
		
		itemMenuReservaObra.setActionCommand("MenuReservaObra");
		
		return itemMenuReservaObra;
		}
	
	private JMenuItem getItemMenuCadastroDevolucao(){
		itemMenuCadDevolucao = new JMenuItem("Realizar Devolucao", KeyEvent.VK_0);
		
		itemMenuCadDevolucao.setActionCommand("MenuDevolucaoObra");
		
		return itemMenuCadDevolucao;
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
	
	private JMenuItem getItemMenuCadastroEmprestimo() {
		itemMenuCadEmprestimo = new JMenuItem("Emprestimo", KeyEvent.VK_T);
		
		itemMenuCadEmprestimo.setActionCommand("MenuCadastroEmprestimo");
		
		return itemMenuCadEmprestimo;
	}

	public void configuraOuvinte(ActionListener actionListener) {
		itemMenuCadObra.addActionListener(actionListener);
		itemMenuCadTipoObra.addActionListener(actionListener);
		itemMenuCadEmprestimo.addActionListener(actionListener);
		itemMenuReservaObra.addActionListener(actionListener);
		itemMenuCadDevolucao.addActionListener(actionListener);
	}
	
	public void mostraFormCadastroTipoObra() {
		
		TipoObraControl controladorTipoObra = new TipoObraControl(frameTipoObra);
		controladorTipoObra.inicia(!frameTipoObra.adicionouListeners());
	}
	
	public void mostraFormCadastroObra() {
		ObraControl controladorObra = new ObraControl(frameObra);
		controladorObra.inicia(!frameObra.adicionouListeners());
	}
	
	public void mostraFormCadastroEmprestimo() {	
		EmprestimoControl controladorEmprestimo = new EmprestimoControl(frameEmprestimo);
		controladorEmprestimo.inicia();
	}
	
	public void mostraFormReserva(){
		ReservaControl controladorReserva = new ReservaControl(frameReserva);
		controladorReserva.inicia();
	}
	
	public void mostraFormDevolucao(){
		DevolucaoControl controladorDevolucao = new DevolucaoControl(frameDevolucao);
		controladorDevolucao.inicia();
	}
}
