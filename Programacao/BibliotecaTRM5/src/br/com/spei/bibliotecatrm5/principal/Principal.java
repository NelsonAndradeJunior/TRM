package br.com.spei.bibliotecatrm5.principal;

import br.com.spei.bibliotecatrm5.mvc.control.LoginControl;
import br.com.spei.bibliotecatrm5.mvc.view.FrameLogin;

public class Principal {

	public static void main(String[] args) {
		FrameLogin frameLogin = new FrameLogin();
		LoginControl controladorLogin = new LoginControl(frameLogin);
		controladorLogin.inicia();
	}
}
