package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.ConfigurationException;
import javax.swing.DefaultRowSorter;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import br.com.spei.bibliotecatrm5.mvc.dao.*;
import br.com.spei.bibliotecatrm5.mvc.model.*;
import br.com.spei.bibliotecatrm5.mvc.view.FrameEmprestimo;
import br.com.spei.bibliotecatrm5.mvc.view.FrameReserva;

public class EmprestimoControl extends MouseAdapter implements ActionListener, InternalFrameListener {
	
	private FrameEmprestimo view;
	private Emprestimo model;
	private List<Integer> listaCodRemovidosTabelaExemplares;
	
	public EmprestimoControl(FrameEmprestimo view) {
		this.view = view;
		//view.configuraOuvinteAcao(this);
	}
	
	public EmprestimoControl(FrameEmprestimo view, Emprestimo model) {
		this.view = view;
		this.model = model;
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Confirmar":
			if(!view.validaEmprestimo())
				return;
			
			model = new Emprestimo();
			
			model = view.getModel();
			
			ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
			try {
				model.setExemplares(exemplarDAO.getByIds(listaCodRemovidosTabelaExemplares));
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return;
			}
			
			model.setDataEmprestimo(new Date());
			
			EmprestimoDAO emprestimoDAO = new EmprestimoDAOImpl();
			try {
				emprestimoDAO.insert(model);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return;
			}
			
			view.mostraMensagem("Empréstimo efetuado.");
			
			List<Exemplar> exemplaresDisponiveis;
			try {
				exemplaresDisponiveis = exemplarDAO.listAllLocaveis();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return;
			}
			
			view.limpaTela(exemplaresDisponiveis);
			break;
		case "Cancelar":
			view.setVisible(false);
			break;
		case "PesquisarUsuario":
			listaCodRemovidosTabelaExemplares = new ArrayList<>();
			view.mostraFramePesquisaUsuario();
			break;
		default:
			break;
		}
	}

	private void inicia() {
		view.setVisible(true);
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteAcao(this);
			view.configuraOuvinteFrame(this);
			view.setListenersAdicionados(true);
			view.setMouseListener(this);
		}
		inicia();
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		view.limpaTela(new ArrayList<Exemplar>());
		view.setModel(null);
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
		view.atualizaTabelaExemplaresDisponiveis(new ArrayList<Exemplar>());
		view.atualizaTabelaItensEmprestimo(new ArrayList<Exemplar>());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass() == JTable.class) {
			if(e.getClickCount() == 2) {
				JTable fonte = (JTable)e.getSource();
				int codigo = (int)fonte.getModel().getValueAt(fonte.getSelectedRow(), 0);
				ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
				
				List<Exemplar> listaExemplares  = null; 
				
				try {
					listaExemplares = exemplarDAO.listAllLocaveis();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					view.mostraErroSQL(e1);
					return;
				}
				
				if(listaCodRemovidosTabelaExemplares == null)
					listaCodRemovidosTabelaExemplares = new ArrayList<>();
					
				if(fonte.getName().equalsIgnoreCase("tblExemplaresDisponiveis")) {
					listaCodRemovidosTabelaExemplares.add(codigo);
				} else if (fonte.getName().equalsIgnoreCase("tblItensEmprestimo")) {
					listaCodRemovidosTabelaExemplares.remove((Object)codigo);
				}
				
				List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
				List<Exemplar> itensEmprestimo = new ArrayList<>();
				
				boolean achou = false;
				
				for (int i = 0; i < listaExemplares.size(); i++) {
					achou = false;
					for (int j = 0; j < listaCodRemovidosTabelaExemplares.size(); j++) {
						if(listaCodRemovidosTabelaExemplares.indexOf(listaExemplares.get(i).getCodExemplar()) > -1) {
							itensEmprestimo.add(listaExemplares.get(i));
							achou = true;
							break;
						}
					}
					if(!achou) {
						Exemplar exemplar = listaExemplares.get(i);
						try {
							if(podeLocar(exemplar))
								exemplaresDisponiveis.add(exemplar);
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							view.mostraErroSQL(e1);
							return;
						} catch (Exception e2) {
							e2.printStackTrace();
							view.mostraMensagemErro(e2.getMessage());
							return;
						}
					}
				}
				
				view.atualizaTabelaExemplaresDisponiveis(exemplaresDisponiveis);
				view.atualizaTabelaItensEmprestimo(itensEmprestimo);
			}
		}
	}
	
	private boolean podeLocar(Exemplar exemplar) throws SQLException, Exception {
		
		if(!exemplar.isReservado())
			return true;
			
		if(model == null)
			model = view.getModel();
		
		if(model == null) 
			throw new Exception("Nenhum usuário selecionado.");
		
		Usuario usuario = model.getUsuario();
		
		ReservaDAO reservaDAO = new ReservaDAOImpl();
		
		int codUsuarioReserva = reservaDAO.getCodUsuarioReservaParaExemplar(exemplar);
		
		if(codUsuarioReserva <= 0)
			return true;
		
		if(codUsuarioReserva == usuario.getCodUsuario())
			return true;
		
		return false;
	}

	public List<Exemplar> getListaTabelaExemplaresDisponiveis() {
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		
		List<Exemplar> listaExemplares  = null; 
		
		try {
			listaExemplares = exemplarDAO.listAllLocaveis();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			view.mostraErroSQL(e1);
			return null;
		}
		
		for (int i = 0; i < listaExemplares.size(); i++) {
			Exemplar exemplar = listaExemplares.get(i);
			try {
				if(!podeLocar(exemplar))
					listaExemplares.remove(exemplar);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return null;
			} catch (Exception e2) {
				e2.printStackTrace();
				view.mostraMensagemErro(e2.getMessage());
				return null;
			}
		}
		
		return listaExemplares;
	}
}
	
	


