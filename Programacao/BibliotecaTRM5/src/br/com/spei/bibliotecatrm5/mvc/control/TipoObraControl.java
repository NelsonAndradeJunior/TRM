package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;

public class TipoObraControl implements ActionListener {

	private FrameTipoObra view;
	private TipoObra model;
	
	public TipoObraControl(FrameTipoObra view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("cancelar")) {
			view.setVisible(false);
		}
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
}
