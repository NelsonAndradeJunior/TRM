package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;

public class TipoObraControl implements ActionListener, InternalFrameListener{

	private FrameTipoObra view;
	private TipoObra model;
	
	public TipoObraControl(FrameTipoObra view) {
		this.view = view;
		model = new TipoObra();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			TipoObraDAO tipoObraDAO = new TipoObraDAOImpl();
			try {
				if(view.getModoAtualizacao()) 
					switch (view.confirmaAtualizacao()) {
					case JOptionPane.YES_OPTION:
						model = view.getModel();
						model.setDescricaoTipoObra(view.getDescricaoTipoObra());
						tipoObraDAO.update(model);
						view.limpaTexto();
						view.setModoAtualizacao(false);
						break;
					case JOptionPane.NO_OPTION:
						view.limpaTexto();
						view.setModoAtualizacao(false);
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
				else {
					model = new TipoObra();
					model.setDescricaoTipoObra(view.getDescricaoTipoObra());
					tipoObraDAO.insert(model);
					view.limpaTexto();
					view.mostraMensagem("Cadastro efetuado com sucesso.");
				}
			} catch (SQLException e1) {
				view.disparaExcecaoSQL(e1);
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
		case "pesquisar":
			view.mostraFormTipoObraPesquisa();
			break;
		default:
			break;
		}
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
	
	public void inicia(boolean configuraOuvinte) {
		if(configuraOuvinte) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteInternalFrame(this);
			view.setListenersAdicionados(true);
		}
		inicia();			
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		view.limpaTexto();
		view.setModoAtualizacao(false);
		view.setModel(null);
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}
}
