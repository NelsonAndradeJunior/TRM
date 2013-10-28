package br.com.spei.bibliotecatrm5.mvc.control;

import br.com.spei.bibliotecatrm5.mvc.view.FrameLogin;

public class LoginControl {

	FrameLogin frameLogin = null;
	
	public LoginControl(FrameLogin frameLogin) {
		this.frameLogin = frameLogin;
	}

	public void inicia() {
		frameLogin.setVisible(true);
	}
}
