package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;

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
			if(!view.validaCamposPreenchidos()) {
				view.mostraMensagem("Há campos não preenchidos.");
				return;
			}
			try {
				if(view.getModoAtualizacao()) {
					switch (view.confirmaAtualizacao()) {
					case JOptionPane.YES_OPTION:
						model = view.getModel();
						preencheInformacoes();
						obraDAO.update(model);
						view.limpaTela();
						view.setModoAtualizacao(false);
						break;
					case JOptionPane.NO_OPTION:
						view.limpaTela();
						view.setModoAtualizacao(false);
						break;
					case JOptionPane.CANCEL_OPTION:
						break;
					default:
						break;
					}
				} else {
					model = view.getModel();
					preencheInformacoes();
					obraDAO.insert(model);
					view.limpaTela();
					view.mostraMensagem("Cadastro efetuado com sucesso.");
				}
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
			view.mostraFrameEditoraPesquisa();
			break;
		case "tipo_obra":
			view.mostraFrameTipoObraPesquisa();
			break;
		default:
			break;
		}
	}

	private void preencheInformacoes() {
		model.setNomeObra(view.getNomeObra());
		model.setAno(view.getAnoObra());
		model.setClassico(view.isClassico());
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteFrame(this);
			view.setListenersAdicionados(true);
		}
		inicia();
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		view.limpaTela();
		view.setModoAtualizacao(false);
		view.setModel(null);
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		
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
