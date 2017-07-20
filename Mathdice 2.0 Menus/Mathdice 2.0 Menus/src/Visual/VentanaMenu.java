package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Menu;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;

import java.awt.CardLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class VentanaMenu extends JFrame {
	
	private JMenu mnOpciones = new JMenu("Opciones");
	private JMenuItem mntmJuego = new JMenuItem("Juego");
	private JMenuItem mntmRegistro = new JMenuItem("Perfil");
	private JMenuBar menuBar = new JMenuBar();
	private	JMenu mnAyuda = new JMenu("Ayuda");
	private JMenu mnInformacion = new JMenu("Información");
	private JPanel contentPane;
	public	Juego Play;
	
	final static String VentanaPerfil = "ventana Perfil";
	final static String VentanaJuego = "ventana Juego";
	
	Perfil Vperfil=new Perfil();
	Juego Jue=new Juego();
	Usuario Jug=new Usuario();
	public VentanaMenu() {
		

		Jug.setNombre(Jug.getNombre());

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(Jue,VentanaJuego);
		Jue.setLayout(null);
		contentPane.add(Vperfil,VentanaPerfil);
		Vperfil.setLayout(null);


		
		setJMenuBar(menuBar);
		
		menuBar.add(mnOpciones);
		
		mnOpciones.add(mntmJuego);
		mntmJuego.addActionListener(new ActionListener() {  //Listener, cuando seleccionamos nos muestra el Perfil
            public void actionPerformed(ActionEvent arg0)  {
				  CardLayout cl = (CardLayout)(contentPane.getLayout());
				    cl.show(contentPane, VentanaJuego);
			}
		});
		
		mnOpciones.add(mntmRegistro);
		//Accedemos al boton de Ventana 2. Al pulsarlo pasarÃ¡ a mostrar la ventana1
		mntmRegistro.addActionListener(new ActionListener() {  //Listener, cuando seleccionamos nos muestra el Perfil
            public void actionPerformed(ActionEvent arg0)  {
            	
				  CardLayout cl = (CardLayout)(contentPane.getLayout());
				    cl.show(contentPane, VentanaPerfil);
			}
		});
		
		menuBar.add(mnAyuda);
		
		menuBar.add(mnInformacion);
		
		Jue.setVisible(true);
		//Jue.Login.setText("BIENVENIDO AL JUEGO: "+ TextNombre.getText());// recoge el Nombre y nos lo muestra en la ventana Juego
		Jue.BotonMas.setEnabled(false);//dejamos una serie de botones en fasle
		Jue.BotonMenos.setEnabled(false);
		Jue.BotonMath.setEnabled(false);
		//Vperfil.BotonActualizar.setEnabled(false);
	}

}
