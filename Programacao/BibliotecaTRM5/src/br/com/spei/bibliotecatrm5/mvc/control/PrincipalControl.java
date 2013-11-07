package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.spei.bibliotecatrm5.mvc.view.*;

public class PrincipalControl implements ActionListener {

	FramePrincipal framePrincipal = null;
	
	public PrincipalControl(FramePrincipal framePrincipal) {
		this.framePrincipal = framePrincipal;
		framePrincipal.configuraOuvinte(this);
	}

	public void inicia() {
		framePrincipal.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "MenuCadastroTipoObra":
			framePrincipal.mostraFormCadastroTipoObra();
			break;
		case "MenuCadastroObra":
			framePrincipal.mostraFormCadastroObra();
			break;
		case "MenuCadastroEmprestimo":
			framePrincipal.mostraFormCadastroEmprestimo();
			break;
		case "MenuReservaObra":
			framePrincipal.mostraFormReserva();
			break;	
		case "MenuDevolucaoObra":
			framePrincipal.mostraFormDevolucao();
			break;
		default:
			break;
		}
	}
}
