package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObraPesquisa;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;

public class ObraPesquisaControl extends MouseAdapter implements ActionListener, InternalFrameListener {

	FrameObraPesquisa view;
	Obra model;
	String callerName;
	
	public ObraPesquisaControl(FrameObraPesquisa view, String callerName) {
		this.view = view;
		this.callerName = callerName;
	}

	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteMouse(this);
		}
			
		view.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("pesquisar")) {
			atualizaInformacoes();
		}
	}

	public void carregaInformacoes() {
		ObraDAO obraDAO = new ObraDAOImpl();
		try {
			List<Obra> listaTipoObra = obraDAO.listAll();
			view.atualizaTabela(listaTipoObra);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
	}
	
	public void atualizaInformacoes() {
		String textoPesquisa = view.getTextoPesquisa();
		ObraDAO obraDAO = new ObraDAOImpl();
		try {
			List<Obra> listaObra = obraDAO.getByName(textoPesquisa);
			view.atualizaTabela(listaObra);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass() == JTable.class) {
			if(e.getClickCount() == 2) {
				JTable fonte = (JTable)e.getSource();
				int codigo = (int)fonte.getModel().getValueAt(fonte.getSelectedRow(), 0);
				
				ObraDAO obraDAO = new ObraDAOImpl();
				try {
					model = obraDAO.get(codigo);
				} catch (SQLException e2) {
					view.mostraMensagemErroSQL(e2);
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null) {
							if(frame.getName().equalsIgnoreCase(callerName)) {
								frame.setSelected(true);
								if(callerName.equalsIgnoreCase("frmObra")) {
									// TODO Criar Frame Abstrato
									((FrameObra)frame).setModel(model);
									((FrameObra)frame).preencheInformacoesFrame();
									((FrameObra)frame).setModoAtualizacao(true);
								}
							}
						}
					}
					view.setVisible(false);
					view.limpaTela();
				} catch (PropertyVetoException e1) {
					// TODO Remover
					e1.printStackTrace();
					view.mostraMensagemErro("Ocorreu um erro ( " + e1.getMessage() +").");
				}
			}
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
		view.limpaTela();
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
