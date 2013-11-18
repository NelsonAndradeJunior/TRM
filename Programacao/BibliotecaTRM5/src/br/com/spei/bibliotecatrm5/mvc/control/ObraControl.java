package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;

public class ObraControl implements ActionListener {

	private FrameObra view;
	private Obra model;
	
	public ObraControl(FrameObra view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			ObraDAO obraDAO = new ObraDAOImpl();
			// TODO Tratar exceção
//			obraDAO.insert(model);
			break;
		case "cancelar":
			view.setVisible(false);
			break;
		case "pesquisar":
			break;
		default:
			break;
		}
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
}
