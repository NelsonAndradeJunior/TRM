package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.view.FrameEmprestimo;

public class EmprestimoControl implements ActionListener {
	
	private FrameEmprestimo view;
	private Emprestimo model;
	
	public EmprestimoControl(FrameEmprestimo view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			// TODO Teste - Remover
			EmprestimoDAO t = new EmprestimoDAOImpl();
			List<Emprestimo> l = t.listAll();
			
			for (Emprestimo emprestimo : l) {
				JOptionPane.showMessageDialog(null, emprestimo.getIdUsuario());
				
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
	
	


