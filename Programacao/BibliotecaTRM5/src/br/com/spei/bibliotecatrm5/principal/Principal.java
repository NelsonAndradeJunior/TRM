package br.com.spei.bibliotecatrm5.principal;

import br.com.spei.bibliotecatrm5.mvc.control.PrincipalControl;
import br.com.spei.bibliotecatrm5.mvc.view.FramePrincipal;

public class Principal {

	public static void main(String[] args) {
		//FrameLogin frameLogin = new FrameLogin();
		//LoginControl controladorLogin = new LoginControl(frameLogin);
		//controladorLogin.inicia();
		FramePrincipal framePrincipal = new FramePrincipal();
		PrincipalControl controladorPrincipal = new PrincipalControl(framePrincipal);
		controladorPrincipal.inicia();
	}
}
