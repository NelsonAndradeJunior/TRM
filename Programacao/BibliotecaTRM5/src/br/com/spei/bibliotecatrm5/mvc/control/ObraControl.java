package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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
		ObraDAO obraDAO = new ObraDAOImpl();
		switch (e.getActionCommand()) {
		case "gravar":
			try {
				obraDAO.insert(model);
			} catch (SQLException e1) {
				view.mostraExcecaoSQL();
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "cancelar":
			view.setVisible(false);
			break;
		case "obra":
			view.mostraFrameObraPesquisa();
			break;
		case "autor":
			view.mostraFrameAutorPesquisa();
			break;
		case "editora":
			break;
		case "tipo_obra":
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
