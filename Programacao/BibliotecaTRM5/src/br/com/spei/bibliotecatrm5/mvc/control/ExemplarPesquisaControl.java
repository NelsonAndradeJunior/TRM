package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.view.FrameAutorPesquisa;
import br.com.spei.bibliotecatrm5.mvc.view.FrameExemplarPesquisa;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;

public class ExemplarPesquisaControl extends MouseAdapter implements ActionListener, InternalFrameListener{

	private FrameExemplarPesquisa view;
	private Exemplar model;
	private String callerName;
	
	public ExemplarPesquisaControl(FrameExemplarPesquisa view, String callerName) {
		super();
		this.view = view;
		this.callerName = callerName;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Pesquisar")) {
			atualizaInformacoes();
		}
	}

	private void atualizaInformacoes() {
		String textoPesquisa = view.getTextoPesquisa();
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		try {
			List<Exemplar> listaExemplar = exemplarDAO.getByName(textoPesquisa);
			view.atualizaTabela(listaExemplar);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
	}

	public void carregaInformacoes() {
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		try {
			List<Exemplar> listaExemplares = exemplarDAO.listAll();
			view.atualizaTabela(listaExemplares);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraActionListener(this);
			view.configuraMouseListener(this);
		}
		inicia();
	}
	
	private void inicia() {
		view.setVisible(true);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass() == JTable.class) {
			if(e.getClickCount() == 2) {
				JTable fonte = (JTable)e.getSource();
				int codigo = (int)fonte.getModel().getValueAt(fonte.getSelectedRow(), 0);
				
				ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
				try {
					model = exemplarDAO.get(codigo);
				} catch (SQLException e2) {
					view.mostraMensagemErroSQL(e2);
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null && frame.getName().equalsIgnoreCase(this.callerName)) {
							frame.setSelected(true);
							// TODO Criar Frame Abstrato
							((FrameReserva)frame).setExemplarModel(model);
							((FrameReserva)frame).preencheInformacoesExemplar();
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
}
