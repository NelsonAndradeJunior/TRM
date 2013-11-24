package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import br.com.spei.bibliotecatrm5.mvc.control.AutorPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.control.EditoraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.control.ObraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.control.TipoObraPesquisaControl;
import br.com.spei.bibliotecatrm5.mvc.model.Autor;
import br.com.spei.bibliotecatrm5.mvc.model.Editora;
import br.com.spei.bibliotecatrm5.mvc.model.Obra;
import br.com.spei.bibliotecatrm5.mvc.model.TipoObra;

public class FrameObra extends JInternalFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblCodigoObra;
	private JTextField txtCodigoObra;
	private JLabel lblNomeObra;
	private JTextField txtNomeObra;
	private JLabel lblAutorObra;
	private JTextField txtAutorObra;
	private JLabel lblAno;
	private JTextField txtAno;
	private JLabel lblEditora;
	private JTextField txtEditora;
	private JLabel lblTipoObra;
	private JTextField txtTipoObra;
	private JButton btnGravar;
	private JButton btnCancelar;
	private JButton btnPesquisarObra;
	private JButton btnPesquisarAutor;
	private JButton btnPesquisarEditora;
	private JButton btnPesquisarTipoObra;
	private JCheckBox chkClassico;
	private BufferedImage picLupa;
	private FrameObraPesquisa frameObraPesquisa;
	private FrameAutorPesquisa frameAutorPesquisa;
	private FrameEditoraPesquisa frameEditoraPesquisa;
	private FrameTipoObraPesquisa frameTipoObraPesquisa;
	private Obra model;
	private boolean modoAtualizacao;
	private boolean listenersAdicionados;

	public FrameObra() {
		super("", false, true, false, true);
		inicializa();
	}
	
	private void inicializa() {
		this.setBounds(50, 50, 470, 230);
		this.setTitle("Cadastro de Obra");
		this.setName("frmObra");
		this.setDefaultCloseOperation(JInternalFrame.HIDE_ON_CLOSE);

		SpringLayout layoutManager = getLayoutManager();

		lblCodigoObra = getLabelCodObra();
		txtCodigoObra = getTextCodObra();
		lblNomeObra = getLabelNomeObra();
		txtNomeObra = getTextNomeObra();
		lblAutorObra = getLabelAutorObra();
		txtAutorObra = getTextAutorObra();
		lblAno = getLabelAno();
		txtAno = getTextAno();
		lblEditora = getLabelEditora();
		txtEditora = getTextEditora();
		lblTipoObra = getLabelTipoObra();
		txtTipoObra = getTextTipoObra();
		btnGravar = getButtonGravar();
		btnCancelar = getButtonCancelar();
		btnPesquisarObra = getButtonPesquisa("obra");
		btnPesquisarAutor = getButtonPesquisa("autor");
		btnPesquisarEditora = getButtonPesquisa("editora");
		btnPesquisarTipoObra = getButtonPesquisa("tipo_obra");
		chkClassico = getCheckBox("Clássico", "chkClassico");
		
		layoutManager.putConstraint(SpringLayout.NORTH, lblCodigoObra, 10,
				SpringLayout.NORTH, getContentPane());
		layoutManager.putConstraint(SpringLayout.EAST, lblCodigoObra, 0,
				SpringLayout.EAST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.NORTH, lblNomeObra, 10,
				SpringLayout.SOUTH, lblCodigoObra);
		layoutManager.putConstraint(SpringLayout.EAST, lblNomeObra, 0,
				SpringLayout.EAST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.NORTH, lblAutorObra, 10,
				SpringLayout.SOUTH, lblNomeObra);
		layoutManager.putConstraint(SpringLayout.EAST, lblAutorObra, 0,
				SpringLayout.EAST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, txtAutorObra, 0,
				SpringLayout.SOUTH, lblAutorObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtAutorObra, 10,
				SpringLayout.EAST, lblAutorObra);

		layoutManager.putConstraint(SpringLayout.NORTH, lblAno, 10,
				SpringLayout.SOUTH, lblAutorObra);
		layoutManager.putConstraint(SpringLayout.EAST, lblAno, 0,
				SpringLayout.EAST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, txtCodigoObra, 0,
				SpringLayout.SOUTH, lblCodigoObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtCodigoObra, 10,
				SpringLayout.EAST, lblCodigoObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, txtNomeObra, 0,
				SpringLayout.SOUTH, lblNomeObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtNomeObra, 10,
				SpringLayout.EAST, lblNomeObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, txtAno, 0,
				SpringLayout.SOUTH, lblAno);
		layoutManager.putConstraint(SpringLayout.WEST, txtAno, 10,
				SpringLayout.EAST, lblAno);

		layoutManager.putConstraint(SpringLayout.NORTH, lblEditora, 10,
				SpringLayout.SOUTH, lblAno);
		layoutManager.putConstraint(SpringLayout.EAST, lblEditora, 0,
				SpringLayout.EAST, lblAno);

		layoutManager.putConstraint(SpringLayout.SOUTH, txtEditora, 0,
				SpringLayout.SOUTH, lblEditora);
		layoutManager.putConstraint(SpringLayout.WEST, txtEditora, 10,
				SpringLayout.EAST, lblEditora);

		layoutManager.putConstraint(SpringLayout.NORTH, lblTipoObra, 10,
				SpringLayout.SOUTH, lblEditora);
		layoutManager.putConstraint(SpringLayout.WEST, lblTipoObra, 10,
				SpringLayout.WEST, getContentPane());

		layoutManager.putConstraint(SpringLayout.SOUTH, txtTipoObra, 0,
				SpringLayout.SOUTH, lblTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, txtTipoObra, 10,
				SpringLayout.EAST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.NORTH, btnGravar, 10,
				SpringLayout.SOUTH, lblTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnGravar, 0,
				SpringLayout.WEST, lblTipoObra);

		layoutManager.putConstraint(SpringLayout.NORTH, btnCancelar, 10,
				SpringLayout.SOUTH, txtTipoObra);
		layoutManager.putConstraint(SpringLayout.EAST, btnCancelar, 0,
				SpringLayout.EAST, txtTipoObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarObra, 0,
				SpringLayout.SOUTH, txtCodigoObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarObra, 0,
				SpringLayout.NORTH, txtCodigoObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarObra, 5,
				SpringLayout.EAST, txtCodigoObra);

		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarAutor, 0,
				SpringLayout.SOUTH, txtAutorObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarAutor, 0,
				SpringLayout.NORTH, txtAutorObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarAutor, 5,
				SpringLayout.EAST, txtAutorObra);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarEditora, 0,
				SpringLayout.SOUTH, txtEditora);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarEditora, 0,
				SpringLayout.NORTH, txtEditora);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarEditora, 5,
				SpringLayout.EAST, txtEditora);
		
		layoutManager.putConstraint(SpringLayout.SOUTH, btnPesquisarTipoObra, 0,
				SpringLayout.SOUTH, txtTipoObra);
		layoutManager.putConstraint(SpringLayout.NORTH, btnPesquisarTipoObra, 0,
				SpringLayout.NORTH, txtTipoObra);
		layoutManager.putConstraint(SpringLayout.WEST, btnPesquisarTipoObra, 5,
				SpringLayout.EAST, txtTipoObra);
		
		layoutManager.putConstraint(SpringLayout.WEST, chkClassico, 10, SpringLayout.EAST, btnPesquisarTipoObra);
		layoutManager.putConstraint(SpringLayout.SOUTH, chkClassico, 3, SpringLayout.SOUTH, btnPesquisarTipoObra);
		
		this.setLayout(layoutManager);

		this.getContentPane().add(lblCodigoObra);
		this.getContentPane().add(txtCodigoObra);
		this.getContentPane().add(lblNomeObra);
		this.getContentPane().add(txtNomeObra);
		this.getContentPane().add(lblAutorObra);
		this.getContentPane().add(txtAutorObra);
		this.getContentPane().add(lblAno);
		this.getContentPane().add(txtAno);
		this.getContentPane().add(lblEditora);
		this.getContentPane().add(txtEditora);
		this.getContentPane().add(lblTipoObra);
		this.getContentPane().add(txtTipoObra);
		this.getContentPane().add(btnGravar);
		this.getContentPane().add(btnCancelar);
		this.getContentPane().add(btnPesquisarObra);
		this.getContentPane().add(btnPesquisarAutor);
		this.getContentPane().add(btnPesquisarEditora);
		this.getContentPane().add(btnPesquisarTipoObra);
		this.getContentPane().add(chkClassico);
	}

	private JCheckBox getCheckBox(String texto, String nome) {
		JCheckBox checkBox = new JCheckBox(texto);
		checkBox.setName(nome);
		return checkBox;
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		this.fireInternalFrameEvent(InternalFrameEvent.INTERNAL_FRAME_CLOSED);
	}
	
	private JButton getButtonPesquisa(String actionCommand) {
		JButton botao = getButtonPesquisa();
		botao.setActionCommand(actionCommand);
		return botao;
	}

	private JTextField getTextNomeObra() {
		JTextField textField = new JTextField(33);
		return textField;
	}

	private JLabel getLabelNomeObra() {
		return new JLabel("Nome:");
	}

	private JTextField getTextCodObra() {
		JTextField textField = new JTextField(4);
		textField.setEditable(false);
		return textField;
	}

	private JLabel getLabelCodObra() {
		return new JLabel("Código:");
	}

	private JButton getButtonPesquisa() {
		picLupa = getPictureLupa();
		JButton botao = new JButton(new ImageIcon(picLupa.getScaledInstance(8,
				8, BufferedImage.SCALE_SMOOTH)));
		botao.setName("btnPesquisar");
		botao.setActionCommand("pesquisar");
		return botao;
	}

	private BufferedImage getPictureLupa() {
		BufferedImage imagem = null;

		try {
			imagem = ImageIO.read(new File("recursos\\Magnifier-icon.png"));
		} catch (IOException e) {
			// TODO Tratar
			e.printStackTrace();
		}

		return imagem;
	}

	private JButton getButtonCancelar() {
		JButton botao = new JButton("Cancelar");
		botao.setName("btnCancelar");
		botao.setActionCommand("cancelar");
		return botao;
	}

	private JButton getButtonGravar() {
		JButton botao = new JButton("Gravar");
		botao.setName("btnGravar");
		botao.setActionCommand("gravar");
		return botao;
	}

	private JTextField getTextAutorObra() {
		JTextField campoTexto = new JTextField(20);
		campoTexto.setName("txtAutorObra");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelAutorObra() {
		JLabel label = new JLabel("Autor:");
		label.setName("lblAutorObra");
		return label;
	}

	private JTextField getTextAno() {
		JTextField campoTexto = new JTextField(3);
		campoTexto.setName("txtAno");
		return campoTexto;
	}

	private JLabel getLabelAno() {
		JLabel label = new JLabel("Ano:");
		label.setName("lblAno");
		return label;
	}

	private JTextField getTextEditora() {
		JTextField campoTexto = new JTextField(15);
		campoTexto.setName("txtEditora");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelEditora() {
		JLabel label = new JLabel("Editora:");
		label.setName("lblEditora");
		return label;
	}

	private JTextField getTextTipoObra() {
		JTextField campoTexto = new JTextField(10);
		campoTexto.setName("txtTipoObra");
		campoTexto.setEditable(false);
		return campoTexto;
	}

	private JLabel getLabelTipoObra() {
		JLabel label = new JLabel("Tipo Obra:");
		label.setName("lblTipoObra");
		return label;
	}

	private SpringLayout getLayoutManager() {
		return new SpringLayout();
	}

	public void configuraOuvinteAcao(ActionListener actionListener) {
		btnCancelar.addActionListener(actionListener);
		btnGravar.addActionListener(actionListener);
		btnPesquisarObra.addActionListener(actionListener);
		btnPesquisarEditora.addActionListener(actionListener);
		btnPesquisarAutor.addActionListener(actionListener);
		btnPesquisarTipoObra.addActionListener(actionListener);
	}

	public void mostraExcecaoSQL() {
		JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar realizar a operação.", "Erro", JOptionPane.ERROR_MESSAGE);
	}

	public void mostraFrameObraPesquisa() {
		boolean adicionaListeners = frameObraPesquisa == null;
		
		if(frameObraPesquisa == null) {
			try {
				frameObraPesquisa = getFramePesquisa();
				((JDesktopPane)this.getParent()).add(frameObraPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		ObraPesquisaControl controladorObraPesquisa = new ObraPesquisaControl(frameObraPesquisa, this.getName());
		controladorObraPesquisa.inicia(adicionaListeners);
		
		controladorObraPesquisa.carregaInformacoes();
	}

	private FrameObraPesquisa getFramePesquisa() throws SQLException {
		return new FrameObraPesquisa();
	}

	public void setModel(Obra model) {
		this.model = model;
	}

	public void preencheInformacoesFrame() {
		String idObraString = ((Integer)model.getIdObra()).toString();
		this.txtCodigoObra.setText(idObraString);
		this.txtNomeObra.setText(model.getNomeObra());
		this.txtAutorObra.setText(model.getAutor().getNomeAutor());
		String anoString = ((Integer)model.getAno()).toString();
		this.txtAno.setText(anoString);
		this.txtEditora.setText(model.getEditora().getNomeEditora());
		this.txtTipoObra.setText(model.getTipoObra().getDescricaoTipoObra());
		this.chkClassico.setSelected(model.isClassico());
	}

	public void setModoAtualizacao(boolean modoAtualizacao) {
		this.modoAtualizacao = modoAtualizacao;
	}

	public void mostraFrameAutorPesquisa() {
		boolean adicionaListeners = frameAutorPesquisa == null;
		
		if(frameAutorPesquisa == null) {
			try {
				frameAutorPesquisa = getFrameAutorPesquisa();
				((JDesktopPane)this.getParent()).add(frameAutorPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		AutorPesquisaControl controladorAutorPesquisa = new AutorPesquisaControl(frameAutorPesquisa);
		controladorAutorPesquisa.inicia(adicionaListeners);
		
		controladorAutorPesquisa.carregaInformacoes();
	}

	private FrameAutorPesquisa getFrameAutorPesquisa() throws SQLException {
		return new FrameAutorPesquisa();
	}

	public void mostraMensagem(String mensagem) {
		JOptionPane.showMessageDialog(null, mensagem);
	}

	public void limpaTela() {
		txtCodigoObra.setText("");
		txtNomeObra.setText("");
		txtAno.setText("");
		txtAutorObra.setText("");
		txtEditora.setText("");
		txtTipoObra.setText("");
		chkClassico.setSelected(false);
	}

	public void configuraOuvinteFrame(InternalFrameListener listener) {
		this.addInternalFrameListener(listener);
	}

	public boolean adicionouListeners() {
		return this.listenersAdicionados;
	}
	
	public void setListenersAdicionados(boolean listenersAdicionados) {
		this.listenersAdicionados = listenersAdicionados;
	}

	public void setAutorModel(Autor autor) {
		if(this.model == null)
			this.model = new Obra();
	
		this.model.setAutor(autor);
	}

	public void preencheCampoAutor() {
		this.txtAutorObra.setText(this.model.getAutor().getNomeAutor());
	}

	public void mostraFrameEditoraPesquisa() {
		boolean adicionaListeners = frameEditoraPesquisa == null;
		
		if(frameEditoraPesquisa == null) {
			try {
				frameEditoraPesquisa = getFrameEditoraPesquisa();
				((JDesktopPane)this.getParent()).add(frameEditoraPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		EditoraPesquisaControl controladorEditoraPesquisa = new EditoraPesquisaControl(frameEditoraPesquisa);
		controladorEditoraPesquisa.inicia(adicionaListeners);
		
		controladorEditoraPesquisa.carregaInformacoes();
	}

	private FrameEditoraPesquisa getFrameEditoraPesquisa() throws SQLException {
		return new FrameEditoraPesquisa();
	}

	public void setEditoraModel(Editora editora) {
		if(this.model == null)
			this.model = new Obra();
	
		this.model.setEditora(editora);
	}

	public void preencheCampoEditora() {
		this.txtEditora.setText(this.model.getEditora().getNomeEditora());
	}

	public void mostraFrameTipoObraPesquisa() {
		boolean adicionaListeners = frameTipoObraPesquisa == null;
		
		if(frameTipoObraPesquisa == null) {
			try {
				frameTipoObraPesquisa = getFrameTipoObraPesquisa();
				((JDesktopPane)this.getParent()).add(frameTipoObraPesquisa);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Ocorreu um erro ao carregar o formulárlio de pesquisa.", "Erro", JOptionPane.ERROR_MESSAGE);
				// TODO Remover
				e.printStackTrace();
				return;
			}
		}
		
		TipoObraPesquisaControl controladorTipoObraPesquisa = new TipoObraPesquisaControl(frameTipoObraPesquisa, this.getName());
		controladorTipoObraPesquisa.inicia(adicionaListeners);
		
		controladorTipoObraPesquisa.carregaInformacoes();
	}

	private FrameTipoObraPesquisa getFrameTipoObraPesquisa() throws SQLException {
		return new FrameTipoObraPesquisa();
	}

	public void setTipoObraModel(TipoObra tipoObra) {
		if(this.model == null)
			this.model = new Obra();
	
		this.model.setTipoObra(tipoObra);
	}

	public void preencheCampoTipoObra() {
		this.txtTipoObra.setText(this.model.getTipoObra().getDescricaoTipoObra());
	}

	public boolean validaCamposPreenchidos() {
		return !txtNomeObra.getText().isEmpty() &&
				!txtAutorObra.getText().isEmpty() &&
				!txtAno.getText().isEmpty() &&
				!txtEditora.getText().isEmpty() &&
				!txtTipoObra.getText().isEmpty();
	}

	public Obra getModel() {
		return this.model;
	}

	public String getNomeObra() {
		return this.txtNomeObra.getText();
	}
	
	public int getAnoObra() {
		String anoString = this.txtAno.getText();
		int retorno = Integer.parseInt(anoString);
		return retorno;
	}

	public boolean getModoAtualizacao() {
		return this.modoAtualizacao;
	}

	public int confirmaAtualizacao() {
		int retorno = JOptionPane.showConfirmDialog(null, "Deseja modificar os dados da obra?", "Confirmar Atualização", JOptionPane.YES_NO_CANCEL_OPTION);
		return retorno;
	}

	public boolean isClassico() {
		return chkClassico.isSelected();
	}
}
