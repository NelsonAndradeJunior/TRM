package br.com.spei.bibliotecatrm5.mvc.control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAO;
import br.com.spei.bibliotecatrm5.mvc.dao.TipoObraDAOImpl;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObra;
import br.com.spei.bibliotecatrm5.mvc.view.FrameTipoObraPesquisa;
import br.com.spei.bibliotecatrm5.util.ConfigurationFile;

public class TipoObraControl implements ActionListener {

	private FrameTipoObra view;
	private TipoObra model;
	
	public TipoObraControl(FrameTipoObra view) {
		this.view = view;
		view.configuraOuvinteAcao(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "gravar":
			// TODO Teste - Remover
			TipoObraDAO t = new TipoObraDAOImpl();
			List<TipoObra> l = t.listAll();
			
			for (TipoObra tipoObra : l) {
				JOptionPane.showMessageDialog(null, tipoObra.getDescricaoTipoObra());
			}
			
			break;
		case "cancelar":
			view.setVisible(false);
			break;
		case "pesquisar":
			view.MostraFormTipoObraPesquisa();		
			break;
		default:
			break;
		}
	}

	public void inicia() {
		view.setVisible(true);
//		view.configuraOuvinteFoco(this);
	}
}
