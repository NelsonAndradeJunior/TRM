package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;

public class TipoObraControl implements ActionListener {

	private FrameTipoObra view;
	private TipoObra model;
	
	public TipoObraControl(FrameTipoObra view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public void inicia() {
		view.setVisible(true);
	}
}
