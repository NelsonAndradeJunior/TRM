package br.com.spei.bibliotecatrm5.mvc.control;

import br.com.spei.bibliotecatrm5.mvc.view.FramePrincipal;

public class PrincipalControl {

	FramePrincipal framePrincipal = null;
	
	public PrincipalControl(FramePrincipal framePrincipal) {
		this.framePrincipal = framePrincipal;
	}

	public void inicia() {
		framePrincipal.setVisible(true);
	}
}
