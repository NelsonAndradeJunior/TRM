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
import br.com.spei.bibliotecatrm5.mvc.dao.EditoraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EditoraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Editora;
import br.com.spei.bibliotecatrm5.mvc.view.FrameEditoraPesquisa;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;

public class EditoraPesquisaControl extends MouseAdapter implements
ActionListener, InternalFrameListener {

	private FrameEditoraPesquisa view;
	private Editora model;

	public EditoraPesquisaControl(FrameEditoraPesquisa view) {
		super();
		this.view = view;
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
				
				EditoraDAO editoraDAO = new EditoraDAOImpl();
				try {
					model = editoraDAO.get(codigo);
				} catch (SQLException e2) {
					view.mostraMensagemErroSQL(e2);
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null && frame.getName().equalsIgnoreCase("frmObra")) {
							frame.setSelected(true);
							// TODO Criar Frame Abstrato
							((FrameObra)frame).setEditoraModel(model);
							((FrameObra)frame).preencheCampoEditora();
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
	
	public void carregaInformacoes() {
		EditoraDAO editoraDAO = new EditoraDAOImpl();
		try {
			List<Editora> listaEditora = editoraDAO.listAll();
			view.atualizaTabela(listaEditora);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
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
		if(e.getActionCommand().equals("pesquisar")) {
			atualizaInformacoes();
		}
	}
	
	private void atualizaInformacoes() {
		String textoPesquisa = view.getTextoPesquisa();
		EditoraDAO editoraDAO = new EditoraDAOImpl();
		try {
			List<Editora> listaAutor = editoraDAO.getByName(textoPesquisa);
			view.atualizaTabela(listaAutor);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
	}

}
