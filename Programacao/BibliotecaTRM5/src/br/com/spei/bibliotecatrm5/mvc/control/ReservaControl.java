package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;

import br.com.spei.bibliotecatrm5.mvc.dao.ReservaDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ReservaDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Reserva;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;

public class ReservaControl implements ActionListener{
	
	
	private FrameReserva view;
	private Reserva model;
	
	public ReservaControl(FrameReserva view){
		this.view = view;
		view.configuraOuvinteAcao(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			// TODO Teste - Remover
			ReservaDAO t = new ReservaDAOImpl();
			List<Reserva> l = t.listAll();
			
			for (Reserva reserva : l) {
				JOptionPane.showMessageDialog(null, reserva.getNomeUsuario());
				
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


