package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObraPesquisa;

public class TipoObraPesquisaControl implements ActionListener {
	private FrameTipoObraPesquisa view;
	private TipoObra model;
	
	public TipoObraPesquisaControl(FrameTipoObraPesquisa view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("pesquisar")) {
			// TODO Implementar Pesquisa
		}
	}

	public void inicia() {
		
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
}
