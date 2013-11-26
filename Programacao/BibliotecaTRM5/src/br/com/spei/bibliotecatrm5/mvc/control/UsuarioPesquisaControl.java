package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.*;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.AutorDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.UsuarioDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.UsuarioDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;
import br.com.spei.bibliotecatrm5.mvc.view.FrameEmprestimo;
import br.com.spei.bibliotecatrm5.mvc.view.FrameObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;
import br.com.spei.bibliotecatrm5.mvc.view.FrameUsuarioPesquisa;

public class UsuarioPesquisaControl extends MouseAdapter implements ActionListener, InternalFrameListener{

	private FrameUsuarioPesquisa view;
	private Usuario model;
	private String callerName; 
	
	public UsuarioPesquisaControl(FrameUsuarioPesquisa view, String callerName) {
		this.view = view;
		this.callerName = callerName;
	}

	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteFrame(this);
			view.configuraMouseListener(this);
		}
		inicia();
	}

	private void inicia() {
		view.setVisible(true);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equalsIgnoreCase("Pesquisar")) {
			atualizaInformacoes();
		}
	}

	private void atualizaInformacoes() {
		String textoPesquisa = view.getTextoPesquisa();
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		try {
			List<Usuario> listaUsuarios = usuarioDAO.getByName(textoPesquisa);
			view.atualizaTabela(listaUsuarios);
		} catch (SQLException e1) {
			// TODO remover
			e1.printStackTrace();
			view.mostraMensagemErroSQL(e1);
		}
	}

	public void carregaInformacoes() {
		UsuarioDAO usuarioDAO = new UsuarioDAOImpl();
		try {
			List<Usuario> listaUsuarios = usuarioDAO.listAll();
			view.atualizaTabela(listaUsuarios);
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
				
				UsuarioDAO autorDAO = new UsuarioDAOImpl();
				try {
					model = autorDAO.get(codigo);
				} catch (SQLException e2) {
					view.mostraMensagemErroSQL(e2);
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null){ 
							if(frame.getName().equalsIgnoreCase(callerName)) {
								frame.setSelected(true);
								if(callerName.equalsIgnoreCase("frmReserva")) {
									// TODO Criar Frame Abstrato
									((FrameReserva)frame).setUsuarioModel(model);
									((FrameReserva)frame).preencheCampoUsuario();
									break;
								} else if (callerName.equalsIgnoreCase("frmEmprestimo")) {
									// TODO Criar Frame Abstrato
									((FrameEmprestimo)frame).setUsuarioModel(model);
									((FrameEmprestimo)frame).preencheCampoUsuario();
									break;
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

}
