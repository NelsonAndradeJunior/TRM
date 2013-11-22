package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;

public class ObraControl implements ActionListener, InternalFrameListener {

	private FrameObra view;
	private Obra model;
	
	public ObraControl(FrameObra view) {
		this.view = view;
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
			try {
				view.setSelected(false);
			} catch (PropertyVetoException e1) {
				view.mostraMensagem("Ocorreu um erro ao tentar fechar o formulário.");
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			view.setVisible(false);
			break;
		case "obra":
			view.mostraFrameObraPesquisa();
			break;
		case "autor":
			view.mostraFrameAutorPesquisa();
			break;
		case "editora":
			view.mostraFrameEditoraPesquisa();
			break;
		case "tipo_obra":
			view.mostraFrameTipoObraPesquisa();
			break;
		default:
			break;
		}
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteFrame(this);
		}
		inicia();
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
		view.limpaTela();
		view.setModoAtualizacao(false);
		view.setModel(null);
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
}
