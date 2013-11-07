package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spei.bibliotecatrm5.mvc.dao.DevolucaoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.DevolucaoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;
import br.com.spei.bibliotecatrm5.mvc.view.FrameDevolucao;

public class DevolucaoControl implements ActionListener{
	
	private FrameDevolucao view;
	private Devolucao model;
	
	public DevolucaoControl(FrameDevolucao view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			// TODO Teste - Remover
			DevolucaoDAO t = new DevolucaoDAOImpl();
			List<Devolucao> l = t.listAll();
			
			for (Devolucao devolucao : l) {
				JOptionPane.showMessageDialog(null, devolucao.getMatUsuario());
				
			}
			
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
