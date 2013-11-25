package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.ReservaDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ReservaDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Reserva;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;

public class ReservaControl implements ActionListener, InternalFrameListener {
	
	
	private FrameReserva view;
	private Reserva model;
	
	public ReservaControl(FrameReserva view){
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		ReservaDAO reservaDAO = new ReservaDAOImpl();
		
		switch (e.getActionCommand()) {
		case "Reservar":
			if(view.validaCamposPreenchidos()) {
				model = view.getModel();
				
				Date dataReserva = new Date();
				
				model.setDataReserva(dataReserva);
				model.getExemplar().setReservado(true);
				
				try {
					reservaDAO.insert(model);
					view.limpaTela();
					view.mostraMensagem("Cadastro efetuado com sucesso.");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					view.mostraErroSQL(e1);
				}
			}
			else
				view.mostraMensagem("Há campos não preenchidos.");
			break;
		case "Cancelar":
			view.setVisible(false);
			break;
		case "PesquisarUsuario":
			view.mostraFramePesquisaUsuario();
			break;
		case "PesquisarExemplar":
			view.mostraFramePesquisaObra();
			break;
		default:
			break;
		}
	}

	private void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.setListenersAdicionados(true);
		}
		
		inicia();
	}
}


