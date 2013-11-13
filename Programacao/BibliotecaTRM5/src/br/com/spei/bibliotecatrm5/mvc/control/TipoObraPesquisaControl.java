package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObraPesquisa;

public class TipoObraPesquisaControl extends MouseAdapter implements ActionListener {
	private FrameTipoObraPesquisa view;
	private TipoObra model;
	
	public TipoObraPesquisaControl(FrameTipoObraPesquisa view, TipoObra model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("pesquisar")) {
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
	}

	private void inicia() {
		view.setVisible(true);
	}
	
	public void inicia(boolean adicionaListeners) {
		if(adicionaListeners) {
			view.configuraOuvinteMouse(this);
			view.configuraOuvinteAcao(this);
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
				model.setCodObra(codigo);
				model.setDescricaoTipoObra(descricao);
				view.setVisible(false);
				try {
					for (JInternalFrame frame : view.getDesktopPane().getAllFrames()) {
						if(frame.getName() != null && frame.getName().equalsIgnoreCase("frmTipoObraPesquisa"))
							frame.setSelected(true);
					}
					
				} catch (PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
