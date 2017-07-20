package Visual;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.Connection;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Modelo.ConexionDB;
import Modelo.Usuario;
import Modelo.UsuariosDB;
import Visual.RegistroUsuario.ActionDatos;
import Visual.RegistroUsuario.ListenerDatos;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

/*
* RegistroUsuario
* Ventana que nos permitirá registrar un usuario en
* nuestra base de datos.
*
*/
public class RegistroUsuario extends JFrame {

		//Contenedor Principal BorderLayout
		private JPanel contentPane = new JPanel();
		//Contenedor de los campos de registro
		private JPanel campos = new JPanel();
		//Contenedor del botón de registro
		private JPanel registro;
				
		//Elementos del JPanel campos
		private JTextField nombreTxt = new JTextField();
		private JTextField apellido1Txt = new JTextField();
		private JTextField apellido2Txt = new JTextField();
		private JTextField edadTxt = new JTextField();
		private JTextArea textArea = new JTextArea();

		//label		
		private JLabel lblNombre = new JLabel("Nombre");
		private JLabel lblApellido1 = new JLabel("Apellido1");
		private JLabel lblApellido2 = new JLabel("Apellido2");
		private JLabel lblEdad = new JLabel("Edad");

		
		//Elementos del JPanel registro
		private JButton BotonRegistro = new JButton("REGISTRAR");
		private JButton BotonBuscar = new JButton("BUSCAR");
		private JButton BotonJugar = new JButton("JUGAR");
		private JLabel lblUsuario = new JLabel("Usuario");
		//JComboBox
		private JComboBox comboBox = new JComboBox();

		private int numero_de_items; //numero de elementos en el ComboBox

		//Referencia para las ventanas Registro y Menu
		public	RegistroUsuario referencia;
		public	VentanaMenu ventana_Menu;
		public Juego J;

	    //Manejadores de la base de datos
		private ConexionDB db;
		private UsuariosDB udb;
		private Connection conexion; //Conexión
		private boolean connected =false; //Conexión con éxito
		
		//Objeto para la clase Jugador
		Usuario Jug=new Usuario();
		
		//referencia para Usuario
		private Usuario u;
		
		//Constructor que pasamos la VentanaMenu
		public  RegistroUsuario(VentanaMenu VM) {
			
			
			//declaramos las referencias a las ventanas Menu y Registro para utilizarlas en el constructor
			referencia=this;
			ventana_Menu=VM;
			//Características del JFrame
			setResizable(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setBounds(100, 100, 601, 402);
			
			//Panel que contendrá el Panel para los datos y el Panel para el registro
			
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
	 
			//Campos de registro en un GridLayout
			
			contentPane.add(campos);
			campos.setLayout(new GridLayout(0, 2, -200, 5));
			contentPane.add(campos, BorderLayout.NORTH);
			
			//label usuario
			campos.add(lblUsuario);
			
			//JcomoBox
			//listener para el comboBox
			comboBox.addActionListener(new MiJComboBox());
			campos.add(comboBox);
			
			//label nombre
			campos.add(lblNombre);
			//textfield nombre
			nombreTxt.addActionListener(new ActionDatos());
			nombreTxt.addMouseListener(new ListenerDatos());
			campos.add(nombreTxt);
			nombreTxt.setColumns(10);

			//label Primer apellido1
			campos.add(lblApellido1);
			//textfield apellido1
			apellido1Txt.addActionListener(new ActionDatos());
			apellido1Txt.addMouseListener(new ListenerDatos());
			campos.add(apellido1Txt);
			apellido1Txt.setColumns(10);

			//label Segundo Apellido2
			campos.add(lblApellido2);
			//textfield aplleido2
			apellido2Txt.addActionListener(new ActionDatos());
			apellido2Txt.addMouseListener(new ListenerDatos());
			campos.add(apellido2Txt);
			apellido2Txt.setColumns(10);

			
			// label Edad
			campos.add(lblEdad);
			//textfield edad
			edadTxt.addMouseListener(new ListenerDatos());
			campos.add(edadTxt);
			edadTxt.setColumns(10);
			
			//un textArea
			contentPane.add(textArea, BorderLayout.CENTER);
			
			//Boton de registro en un FlowLayout
			registro = new JPanel();
			contentPane.add(registro, BorderLayout.SOUTH);
			registro.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			
			//Botonregistro
			BotonRegistro.setVerticalAlignment(SwingConstants.BOTTOM);
			BotonRegistro.addActionListener(new miBotonRegistrar()); 
			
			//botonbuscar
			BotonBuscar.addActionListener(new MiBotonBuscar());
			registro.add(BotonBuscar);
			registro.add(BotonRegistro);
			
			//botonJugar
			BotonJugar.addActionListener(new MiBotonJugar());
			BotonJugar.setEnabled(false);
			BotonJugar.setVerticalAlignment(SwingConstants.BOTTOM);
			registro.add(BotonJugar);
			
			
			//Metodo Conectar
			Conectar();
		}
		//metodo para comprobar datos
		private boolean ComprobarDatos(){
			if (u==null)
					{textArea.setText("No hay datos que registrar"); return false;}
				else if (!u.isNumeric(edadTxt.getText()))
					{textArea.setText("EDAD no correcta."); return false;}
				else if (u.getEdad()>=100 || u.getEdad()<=0)
					{textArea.setText("EDAD no correcta."); return false;}
				else if (u.sonEspacios(nombreTxt.getText()))
					{textArea.setText("Falta el nombre."); return false;}
				else if (u.sonEspacios(apellido1Txt.getText()))
					{textArea.setText("Falta el primer apellido."); return false;}
				else if (u.sonEspacios(apellido2Txt.getText()))
					{textArea.setText("Falta el segundo apellido."); return false;}
				else{
				return true;}
			}
			
		//inner class JComboBox
		private class MiJComboBox implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				//Por defecto mostrará en pantalla la primera coincidencia con el
				//nombre encontrado en la BBDD
				//Seleccionando sobre el desplegable del JComboBox se podrá cambiar si hay varias coincidencias
				
				u=(Usuario)comboBox.getSelectedItem();
				
				if (u!=null) //Si la lista no del Combox no está vacía
				{
			    nombreTxt.setText(u.getNombre());
				apellido1Txt.setText(u.getApellido1());
				apellido2Txt.setText(u.getApellido2());
				edadTxt.setText(String.valueOf(u.getEdad()));
				//Comprobacion
				System.out.println("El usuario seleccionado es: "+u.toString());
				textArea.setText("El usuario seleccionado es: "+u.toString());
				;}
			}
		}
		
		//inner class para el botonbuscar
		private class MiBotonBuscar implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				
				Jug.setEdad(999);
				// comprobamos primero que es un numero, si pues true.
				if(Jug.isNumeric(edadTxt.getText())==true){
					Jug.setEdad(Integer.parseInt(edadTxt.getText()));// si es true nos devuelve el texto
					
				}
				// luego si es mayor que 100 o menos o igual que 1
				 if (Jug.getEdad()>=100 || Jug.getEdad()<=0){//Si la edad esta entre 1 y 100 la edad serÃ¡ correcta
					 textArea.setText("Error, datos mal introducidos");
				}
				 
				else {
					 BotonRegistro.setEnabled(true);// hacemos el boton Guarda editable true para guardar datos
					BotonJugar.setEnabled(true);
				//Concatenamos todo para que nos aparezca en el text area
				}
			
				//Vaciamos el ComboBox
				comboBox.removeAllItems(); //Vaciamos los Items anteriores
				//Conectamos con la BBDD
				Conectar();
				//Buscamos el usuario por el nombre y las coincidencias van a un ComboBox
				udb.buscarUsuario(nombreTxt.getText(),comboBox);
				
				numero_de_items=comboBox.getItemCount(); //Número de ítems en el ComboBox
				//Sirve para el caso en que el nombre buscado no está en la BBDD y el Combox esté vacío
				if (numero_de_items==0){
				nombreTxt.setText("Usuario no encontrado");
				apellido1Txt.setText("Usuario no encontrado");
				apellido2Txt.setText("Usuario no encontrado");
				edadTxt.setText("Usuario no encontrado");	
				textArea.setText("Usuario no encontrado, registrate como nuevo usuario");
				BotonJugar.setEnabled(false);
			}
			else {
				BotonJugar.setEnabled(true);
				BotonRegistro.setEnabled(true);
			}
			}
		}
		
		//inner class para el botonJugar
		public class MiBotonJugar implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {
				//Jug.setEdad(999);
				
				// comprobamos primero que es un numero, si pues true.
				if(Jug.isNumeric(edadTxt.getText())==true){
					Jug.setEdad(Integer.parseInt(edadTxt.getText()));// si es true nos devuelve el texto
				}
				// luego si es mayor que 100 o menos o igual que 1
				 if (Jug.getEdad()>=100 || Jug.getEdad()<=0){//Si la edad esta entre 1 y 100 la edad serÃ¡ correcta
					 textArea.setText("Error, datos mal introducidos");
				}
				else {
					
					 //BotonRegistro.setEnabled(true);// hacemos el boton Guarda editable true para guardar datos
					//BotonJugar.setEnabled(true);
					ventana_Menu.setVisible(true);
					referencia.setVisible(false);
					
				//	J.LabelNombre.setText("BIENVENIDO AL JUEGO: "+Jug.getNombre());
					
				//Concatenamos todo para que nos aparezca en el text area
				}
			}
		}
		//inner class para los datos txtfield
		public class ActionDatos implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JTextField EnterDatos=(JTextField)e.getSource();
				Jug.setNombre(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else
				Jug.setApellido1(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else
				Jug.setApellido2(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else
	//En este punto me he dado cuenta que da igual poner getnombre o getapellidos sirve igualmente para los dos
				if (Jug.sonEspacios(EnterDatos.getText())||(Jug.MetodoMaches(EnterDatos.getText()))){
						textArea.setText("Error, datos mal introducidos");
				}
			}
			
		}
				
				//Listener para el botón registrar
				private class miBotonRegistrar implements ActionListener{

					@Override
					public void actionPerformed(ActionEvent e) {
						//if(ComprobarDatos())
						//Insertamos nuevo usuario en la BBDD y después cerramos la conexión
					
						try{
						udb.insertarUsuario(nombreTxt.getText(), apellido1Txt.getText(), apellido2Txt.getText(), Integer.valueOf(edadTxt.getText()));
						//ventana_Menu.setVisible(true);
						BotonJugar.setEnabled(true);
						}
						catch(Exception e1)
						{
							System.out.println( " Debe haber algún problema con la BBDD, con la conexión o no has introducido tus datos.");
							textArea.setText(" Debe haber algún problema con la BBDD, con la conexión o no has introducido tus datos.");
							
						}
						}
					}
				//inner para el mouse listener
				public class ListenerDatos implements MouseListener{

					@Override
					public void mouseClicked(MouseEvent e) {
						
						JTextField texto=(JTextField)e.getSource();
						texto.setText(null);//borra el texto al hacer click en el JTextfield
						texto.setEditable(true);// lo volvemos editable true

					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				}
				//Métodos de la Clase

				//Conectar con la base de datos
				private void Conectar(){
					//Conexión con la BBDD
					//Creamos nuestro objeto para el manejo de la base de datos
					try{
						db=new ConexionDB("localhost","usuarios","root","");
						//Establecemos la conexion
						connected=db.connectDB();
						//Asignamos con el getter la conexion establecida
						conexion=db.getConexion();
						//Pasamos la conexión a un nuevo objeto UsuariosDB para insertar datos.
						udb=new UsuariosDB(conexion);

						if(connected==false) {
							System.out.println("SIN EXITO EN LA CONEXION");
							textArea.setText("SIN EXITO EN LA CONEXION");
						}
						else System.out.println("EXITO EN LA CONEXION");
						textArea.setText("EXITO EN LA CONEXION");
						}
					catch(Exception e)
					{
						System.out.println( " Debe haber algún problema con la BBDD o con la conexión.");	
					}
				}
				}


