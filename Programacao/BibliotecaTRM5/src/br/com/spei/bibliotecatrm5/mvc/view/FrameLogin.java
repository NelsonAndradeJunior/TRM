package br.com.spei.bibliotecatrm5.mvc.view;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SpringLayout;

public class FrameLogin extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6663349763588316139L;
	private JLabel lblUsuario = new JLabel("Usuário:");
	private JLabel lblSenha = new JLabel("Senha:");
	private JPasswordField pwdSenha = new JPasswordField(10);
	private JTextField txtUsuario = new JTextField(10);
	private BufferedImage picCadeado = null;
	private JLabel lblCadeado = new JLabel();
	private JButton btnLogin = new JButton("Log in");
	private JButton btnCancelar = new JButton("Cancelar");

	/**
	 * Create the frame.
	 */
	public FrameLogin() {
		super();
		initialize();
		
		SpringLayout springLayout = new SpringLayout();
		
		try {
			//picCadeado = ImageIO.read(new File("recursos\\Cadeado.png"));
			picCadeado = ImageIO.read(new File("C:\\Users\\Alan\\Documents\\GitHub\\TRM\\Programacao\\BibliotecaTRM5\\recursos\\Cadeado.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lblCadeado.setIcon(new ImageIcon(picCadeado.getScaledInstance(90, 90, Image.SCALE_SMOOTH)));
		
		springLayout.putConstraint(SpringLayout.NORTH, lblCadeado, 10, SpringLayout.NORTH, getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, lblCadeado, 10, SpringLayout.WEST, getContentPane());
		
		springLayout.putConstraint(SpringLayout.NORTH, lblUsuario, 5, SpringLayout.NORTH, lblCadeado);
		springLayout.putConstraint(SpringLayout.WEST, lblUsuario, 10, SpringLayout.EAST, lblCadeado);
		springLayout.putConstraint(SpringLayout.SOUTH, txtUsuario, 0, SpringLayout.SOUTH, lblUsuario);
		springLayout.putConstraint(SpringLayout.WEST, txtUsuario, 60, SpringLayout.WEST, lblUsuario);
		
		springLayout.putConstraint(SpringLayout.NORTH, lblSenha, 25, SpringLayout.NORTH, lblUsuario);
		springLayout.putConstraint(SpringLayout.WEST, lblSenha, 0, SpringLayout.WEST, lblUsuario);
		springLayout.putConstraint(SpringLayout.SOUTH, pwdSenha, 0, SpringLayout.SOUTH, lblSenha);
		springLayout.putConstraint(SpringLayout.WEST, pwdSenha, 0, SpringLayout.WEST, txtUsuario);
		
		springLayout.putConstraint(SpringLayout.WEST, btnLogin, 0, SpringLayout.WEST, lblSenha);
		springLayout.putConstraint(SpringLayout.NORTH, btnLogin, 10, SpringLayout.SOUTH, lblSenha);
		
		springLayout.putConstraint(SpringLayout.NORTH, btnCancelar, 0, SpringLayout.NORTH, btnLogin);
		springLayout.putConstraint(SpringLayout.EAST, btnCancelar, 0, SpringLayout.EAST, pwdSenha);
		
		springLayout.putConstraint(SpringLayout.EAST, btnLogin, -10, SpringLayout.WEST, btnCancelar);
		
		getContentPane().add(lblUsuario);
		getContentPane().add(txtUsuario);
		getContentPane().add(lblSenha);
		getContentPane().add(pwdSenha);
		getContentPane().add(lblCadeado);
		getContentPane().add(btnLogin);
		getContentPane().add(btnCancelar);
		getContentPane().setLayout(springLayout);
	}
	
	private void initialize() {
		this.setTitle("Log in");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(320, 142);
		this.setLocationRelativeTo(null);
	}
}
