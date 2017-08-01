import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BailandoTexto extends JFrame {

	private JPanel contentPane;
	private JTextField caja;
	private JButton boton2;
	private JTextField caja2;

	/**
	 * Esto nos lanza la aplicacion
	 * hemos creado un Jframe
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BailandoTexto frame = new BailandoTexto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * esto es la configuracion de nuestra ventana
	 * que nos ha creado el Jframe
	 */
	public BailandoTexto() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// creamos un boton con JButton
		JButton boton = new JButton("ChangeText ->");
		
		/**
		 *  al hacer doble click sobre el nos aparace actionlistener
		 *  para darle una accion a ese boton
		 */
		boton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			/**
			 *  la caja la ponemos dentro del action listener 
			 *  para que nos salga el texto cuando apretamos el boton
			 */
				
			/**
			 *  escribimos algo en la caja uno, le damos al boton y nos aparecera en la caja2
			 *  con el "null" hacemos que se borre el texto de la caja 1
			 */
			caja2.setText(caja.getText());
		    caja.setText(null);
			}
		});
		boton.setBounds(165, 53, 129, 23);
		contentPane.add(boton);
		
		// creamos una caja para el texto
		caja = new JTextField();
		caja.setBounds(0, 126, 161, 20);
		contentPane.add(caja);
		caja.setColumns(10);
		
		// creamos un segundo boton2
		boton2 = new JButton("<-ChangeText");
		
		// al hacer doble click sobre el, nos añade lo siguiente actionlistener
		boton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
		/**
		 * Aquí lo mismo que con la primera caja, para que al darle al boton2
		 * el texto cambie a la caja primera
		 */
				caja.setText(caja2.getText());
			    caja2.setText(null);

			}
		});
		boton2.setBounds(165, 191, 129, 23);
		contentPane.add(boton2);
		
		caja2 = new JTextField();
		caja2.setBounds(273, 126, 161, 20);
		contentPane.add(caja2);
		caja2.setColumns(10);
		
	}

}
