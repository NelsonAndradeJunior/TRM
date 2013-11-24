package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;
import javax.swing.event.*;

import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObraPesquisa;

public class TipoObraPesquisaControl extends MouseAdapter implements ActionListener, InternalFrameListener {
	private FrameTipoObraPesquisa view;
	private TipoObra model;
	private String callerName;
	
	public TipoObraPesquisaControl(FrameTipoObraPesquisa view, String callerName) {
		this.view = view;
		this.callerName = callerName;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("pesquisar")) {
			atualizaInformacoes();
		}
	}

	private void inicia() {
		view.setVisible(true);
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteMouse(this);
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteFrame(this);
		}
		this.inicia();
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass() == JTable.class) {
			if(e.getClickCount() == 2) {
				JTable fonte = (JTable)e.getSource();
				int codigo = (int)fonte.getModel().getValueAt(fonte.getSelectedRow(), 0);
				String descricao = (String)fonte.getModel().getValueAt(fonte.getSelectedRow(), 1);
				boolean dicionario = (boolean)fonte.getModel().getValueAt(fonte.getSelectedRow(), 2);
				boolean enciclopedia = (boolean)fonte.getModel().getValueAt(fonte.getSelectedRow(), 3);
				boolean periodico = (boolean)fonte.getModel().getValueAt(fonte.getSelectedRow(), 4);
				this.model = new TipoObra();
				model.setCodTipoObra(codigo);
				model.setDescricaoTipoObra(descricao);
				model.setDicionario(dicionario);
				model.setEnciclopedia(enciclopedia);
				model.setPeriodico(periodico);
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null) {
							if(frame.getName().equalsIgnoreCase(callerName)) {
								frame.setSelected(true);
								if(callerName.equalsIgnoreCase("frmTipoObra")) {
									// TODO Criar Frame Abstrato
									((FrameTipoObra)frame).setModel(model);
									((FrameTipoObra)frame).preencheInformacoes(model);
									((FrameTipoObra)frame).setModoAtualizacao(true);
									break;
								} else if (callerName.equalsIgnoreCase("frmObra")) {
									// TODO Criar Frame Abstrato
									((FrameObra)frame).setTipoObraModel(model);
									((FrameObra)frame).preencheCampoTipoObra();
									break;
								}
							}
						}
					}
					
					view.setVisible(false);
					view.limpaTexto();
				} catch (PropertyVetoException e1) {
					view.disparaExcecao(e1);
				}
			}
		}
	}

	public void atualizaInformacoes() {
		String textoPesquisa = view.getTextoPesquisa();
		TipoObraDAO tipoObraDAO = new TipoObraDAOImpl();
		try {
			List<TipoObra> listaTipoObra = tipoObraDAO.getByName(textoPesquisa);
			view.atualizaTabela(listaTipoObra);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.disparaExcecaoSQL(e1);
		}
		
	}

	public void carregaInformacoes() {
		TipoObraDAO tipoObraDAO = new TipoObraDAOImpl();
		try {
			List<TipoObra> listaTipoObra = tipoObraDAO.listAll();
			view.atualizaTabela(listaTipoObra);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.disparaExcecaoSQL(e1);
		}
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
