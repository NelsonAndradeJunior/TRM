package br.com.spei.bibliotecatrm5.mvc.view;


import java.awt.BorderLayout;

import javax.swing.*;

import br.com.spei.bibliotecatrm5.mvc.control.LoginControl;

public class FramePrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JDesktopPane desktopPane;
	
	/**
	 * Create the frame.
	 */
	public FramePrincipal() {
		super("Biblioteca TRM");
		inicializa();
		
		
		
	}
	
	private void inicializa() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		desktopPane = new JDesktopPane();
		setContentPane(desktopPane);
		this.getal
		desktopPane.add(getFrameLogin());
	}

	private FrameLogin getFrameLogin() {
		FrameLogin frameLogin = new FrameLogin(this);
		LoginControl controladorLogin = new LoginControl(frameLogin);
		controladorLogin.inicia();
		
		return frameLogin;
	}

}
