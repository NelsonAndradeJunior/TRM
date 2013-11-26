package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.DefaultRowSorter;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.RowFilter.ComparisonType;
import javax.swing.RowSorter;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import org.omg.PortableServer.ID_ASSIGNMENT_POLICY_ID;

import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.ExemplarPesquisaTableModel;
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
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		List<Exemplar> listaExemplares = null;
		
		try {
			listaExemplares = exemplarDAO.listAllLocaveis();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			view.mostraErroSQL(e);
		}
		
		view.atualizaTabelaExemplaresDisponiveis(listaExemplares);
		view.atualizaTabelaItensEmprestimo(new ArrayList<Exemplar>());
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource().getClass() == JTable.class) {
			if(e.getClickCount() == 2) {
				JTable fonte = (JTable)e.getSource();
				int codigo = (int)fonte.getModel().getValueAt(fonte.getSelectedRow(), 0);
				if(fonte.getName().equalsIgnoreCase("tblExemplaresDisponiveis")) {
					if(listaCodRemovidosTabelaExemplares == null)
						listaCodRemovidosTabelaExemplares = new ArrayList<>();
						
					listaCodRemovidosTabelaExemplares.add(codigo);					
					
					List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
					try {
						exemplaresDisponiveis = obtemListaExemplaresDisponiveis();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						view.mostraErroSQL(e1);
						return;
					}
					
					List<Exemplar> itensEmprestimo = new ArrayList<>();
					
					try {
						itensEmprestimo = obtemListaItensEmprestimo();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						view.mostraErroSQL(e1);
						return;
					}
 					
					view.atualizaTabela(exemplaresDisponiveis, fonte);
					view.atualizaTabelaItensEmprestimo(itensEmprestimo);
				} else if (fonte.getName().equalsIgnoreCase("tblItensEmprestimo")) {
					if(listaCodRemovidosTabelaExemplares == null)
						listaCodRemovidosTabelaExemplares = new ArrayList<>();
						
					listaCodRemovidosTabelaExemplares.remove((Object)codigo);					
					
					List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
					try {
						exemplaresDisponiveis = obtemListaExemplaresDisponiveis();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						view.mostraErroSQL(e1);
						return;
					}
					
					List<Exemplar> itensEmprestimo = new ArrayList<>();
					
					try {
						itensEmprestimo = obtemListaItensEmprestimo();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
						view.mostraErroSQL(e1);
						return;
					}
 					
					view.atualizaTabela(itensEmprestimo, fonte);
					view.atualizaTabelaExemplaresDisponiveis(exemplaresDisponiveis);
				}
			}
		}
	}
	
	private List<Exemplar> obtemListaItensEmprestimo() throws SQLException {
		List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		
		exemplaresDisponiveis = exemplarDAO.getByIds(listaCodRemovidosTabelaExemplares);
		
		return exemplaresDisponiveis;
	}

	private List<Exemplar> obtemListaExemplaresDisponiveis() throws SQLException {
		List<Exemplar> exemplaresDisponiveis = new ArrayList<>();
		ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
		
		exemplaresDisponiveis = exemplarDAO.getExceptId(listaCodRemovidosTabelaExemplares);
		
		return exemplaresDisponiveis;
	}
}
	
	


