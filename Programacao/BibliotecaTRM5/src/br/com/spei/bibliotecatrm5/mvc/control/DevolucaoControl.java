package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.DevolucaoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.DevolucaoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.EmprestimoDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.ExemplarDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.Devolucao;
import br.com.spei.bibliotecatrm5.mvc.model.Emprestimo;
import br.com.spei.bibliotecatrm5.mvc.model.Exemplar;
import br.com.spei.bibliotecatrm5.mvc.model.Usuario;
import br.com.spei.bibliotecatrm5.mvc.view.FrameDevolucao;

public class DevolucaoControl extends MouseAdapter implements ActionListener, InternalFrameListener {
	
	private FrameDevolucao view;
	private Devolucao model;
	
	public DevolucaoControl(FrameDevolucao view, Devolucao model) {
		this.view = view;
		this.model = model;
	}
	
	public DevolucaoControl(FrameDevolucao view) {
		this.view = view;
	}
	
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "Confirmar":
			if(!view.validaDevolucao())
				return;
			
			ExemplarDAO exemplarDAO = new ExemplarDAOImpl();
			
			model = view.getModel();
			
			List<Exemplar> exemplaresSelecionados = new ArrayList<>();
			
			try {
				List<Integer> codigosExemplaresSelecionados = view.getCodigoExemplaresSelecionados();
				
				exemplaresSelecionados = exemplarDAO.getEmprestadosById(codigosExemplaresSelecionados);
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return;
			}
			
			if(exemplaresSelecionados.size() <= 0) {
				view.mostraMensagemAlerta("Não há nenhum exemplar selecionado para devolução.");
				return;
			}
			
			model.setExemplares(exemplaresSelecionados);
			
			DevolucaoDAO devolucaoDAO = new DevolucaoDAOImpl();
			try {
				devolucaoDAO.insert(model);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				view.mostraErroSQL(e1);
				return;
			}
			
			view.mostraMensagem("Devolução efetuada com sucesso.");
			
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

	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.adicionaActionListeners(this);
			view.adicionaMouseListeners(this);
			view.adicionaInternalFrameListeners(this);
		}
		
		view.setVisible(true);
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		view.limpaTela(new ArrayList<Emprestimo>());
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		// TODO Auto-generated method stub
		
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
		view.atualizaTabelaEmprestimos(new ArrayList<Emprestimo>());
	}

	public List<Emprestimo> listAllEmprestimosUsuario(Usuario usuario) throws SQLException {
		EmprestimoDAO emprestimoDAO = new EmprestimoDAOImpl();
		
		List<Emprestimo> emprestimos = emprestimoDAO.getByUsuario(usuario);
		
		return emprestimos;
	}

}
