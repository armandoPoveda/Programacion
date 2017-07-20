package Visual;


import javax.swing.JPanel;
import javax.swing.JButton;

import com.jgoodies.forms.layout.FormSpecs;

import Modelo.ConexionDB;
import Modelo.Usuario;
import Modelo.UsuariosDB;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.awt.Color;

public class Perfil extends JPanel {
	
	private JPanel contentPane = new JPanel();
	private JTextField textNombre = new JTextField();
	private JTextField textApellido1 = new JTextField();
	private JTextField textApellido2 = new JTextField();
	private JTextField textEdad = new JTextField();
	private JLabel LabelNombre = new JLabel("Nombre");
	private JLabel LabelApellido1= new JLabel("Apellido1");
	private JLabel LabelApellido2 = new JLabel("Apellido2");
	private JLabel LabelEdad = new JLabel("Edad");
	public JButton BotonActualizar = new JButton("ACTUALIZAR");
	private JTextArea textArea = new JTextArea();
	private JLabel lblUsuario = new JLabel("Usuario");
	private JComboBox comboBox = new JComboBox();
	private JButton BotonBuscar = new JButton("BUSCAR");

	public	Juego Play;
	public	RegistroUsuario referencia;
	 
	 /*
	  * CREAMOS UN OBJETO LLAMADO JUGADOR QUE UTILIZAREMOS en la ventana LOGIN
	  * Este objeto hara referencia a la clase JUGADOR
	  *
	  * al crear el objeto nos dara un error en JUGADOR, ponemos el cursos sobre la palabra LOGIN
	  * e importamos la clase Jugador que hara referencia a la ventana JUGADOR
	  */
	//objeto jugador
		 Usuario Jug=new Usuario();
		 
	 //referencia a la clase Usuario
	 private Usuario u;
	 

	 //Manejadores de la base de datos
		private ConexionDB db;
		private UsuariosDB udb;
		private Connection conexion; //Conexión
		private boolean connected =false; //Conexión con éxito
		private int numero_de_items; //numero de elementos en el ComboBox

	public Perfil() {
				
			/*
			 *  INICIALIZAMOS LAS VARIABLES DEL OBJETO JUGADOR Y UTILIZAREMOS LOS 
			 *  SETTERS DE LA CLASE JUGADOR
			 */
			Jug.setNombre(Jug.getNombre());
			Jug.setApellido1(Jug.getApellido1());
			Jug.setApellido2(Jug.getApellido2());
			Jug.setEdad(Jug.getEdad());
			Jug.setId(Jug.getId());
			
			setBounds(100, 100, 701, 439);// medidas del contentPanent 
			setBorder(new EmptyBorder(5, 5, 5, 5));
			
			setLayout(null);
			lblUsuario.setBounds(5, 6, 92, 26);
			add(lblUsuario);
			
			comboBox.addActionListener(new MiJComboBox());
			comboBox.setBounds(165, 6, 251, 32);
			add(comboBox);
			
			LabelNombre.setBounds(5, 53, 111, 29);
			LabelNombre.setHorizontalAlignment(SwingConstants.LEFT);
			add(LabelNombre);
			textNombre.setBounds(165, 50, 251, 35);
			textNombre.addActionListener(new ActionDatos());
			textNombre.addMouseListener(new ListenerDatos());
			add(textNombre);
			textNombre.setColumns(10);
			
			LabelApellido1.setBounds(5, 94, 145, 29);
			LabelApellido1.setHorizontalAlignment(SwingConstants.LEFT);
			add(LabelApellido1);
			textApellido1.setBounds(165, 91, 251, 35);
			textApellido1.addActionListener(new ActionDatos());
			textApellido1.addMouseListener(new ListenerDatos());
			textApellido1.setColumns(10);
			add(textApellido1);
			
			
			LabelApellido2.setBounds(5, 136, 92, 26);
			add(LabelApellido2);
			textApellido2.setBounds(165, 133, 251, 32);
			textApellido2.addActionListener(new ActionDatos());
			textApellido2.addMouseListener(new ListenerDatos());
			add(textApellido2);
			textApellido2.setColumns(10);
			LabelEdad.setBounds(5, 180, 104, 29);
			
			LabelEdad.setHorizontalAlignment(SwingConstants.LEFT);
			add(LabelEdad);
			
			textEdad.setBounds(165, 177, 251, 35);
			textEdad.addActionListener(new ComprobarEdad());
			textEdad.setColumns(10);
			add(textEdad);
			textEdad.addMouseListener(new ListenerDatos());
			//textArea
			textArea.setBounds(21, 318, 634, 111);
			add(textArea);
			
			BotonActualizar.addActionListener(new BotonActualizar());
			BotonActualizar.setForeground(Color.GREEN);
			BotonActualizar.setVerticalAlignment(SwingConstants.TOP);
			BotonActualizar.setBounds(165, 274, 290, 37);
			add(BotonActualizar);
			
			BotonBuscar.addActionListener(new MiBotonBuscar());
			BotonBuscar.setForeground(Color.RED);
			BotonBuscar.setVerticalAlignment(SwingConstants.TOP);
			BotonBuscar.setBounds(165, 223, 290, 35);
			add(BotonBuscar);
			
			Conectar();
		}
	
		//metodo para comprobar datos
	private boolean ComprobarDatos(){
		if (u==null)
			{textArea.setText("No hay datos que registrar"); return false;}
			else if (!u.isNumeric(textEdad.getText()))
			{textArea.setText("EDAD no correcta."); return false;}
			else if (u.getEdad()>=100 || u.getEdad()<=0)
			{textArea.setText("EDAD no correcta."); return false;}
			else if (u.sonEspacios(textNombre.getText())||u.MetodoMaches(textNombre.getText()))
				{textArea.setText("Falta el nombre."); return false;}
			else if (u.sonEspacios(textApellido1.getText())||u.MetodoMaches(textApellido1.getText()))
				{textArea.setText("Falta el primer apellido."); return false;}
			else if (u.sonEspacios(textApellido2.getText())||u.MetodoMaches(textApellido1.getText()))
				{textArea.setText("Falta el segundo apellido."); return false;}
			else{
			return true;}
		}
	
		//inner class botonActualizar
		private class BotonActualizar implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			Usuario u=new Usuario();
			
			if(ComprobarDatos())
			{u.setNombre(textNombre.getText());
			u.setApellido1(textApellido1.getText());
			u.setApellido2(textApellido2.getText());
			u.setEdad(Integer.valueOf(textEdad.getText()));
			//Conectamos y actualizamos usuario
			Conectar();
			
			udb.actualizarUsuario(u);
			//Comprobacion
			System.out.println("Tu usuario se ha actualizado como: "+u.toString()+"\n Puedes hacer una nueva búsqueda");
			textArea.setText("Tu usuario se ha actualizado como: "+u.toString()+"\n Puedes hacer una nueva búsqueda");
			System.out.println("El usuario se ha actualizado a: "+u.toString());
			textArea.setText("El usuario se ha actualizado a: "+u.toString());
			//Vaciamos el ComboBox u los campos de texto
			comboBox.removeAllItems();
			textNombre.setText("");
			textApellido1.setText("");
			textApellido2.setText("");
			textEdad.setText("");
			}
	        
		}
	}
		//inner class BotonBuscar
		private class MiBotonBuscar implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				//Vaciamos el ComboBox
				comboBox.removeAllItems(); //Vaciamos los Items anteriores
				//Conectamos con la BBDD
				Conectar();
				//Buscamos el usuario por el nombre y las coincidencias van a un ComboBox
				udb.buscarUsuario(textNombre.getText(),comboBox);
				
				numero_de_items=comboBox.getItemCount(); //Número de ítems en el ComboBox
				//Sirve para el caso en que el nombre buscado no está en la BBDD y el Combox esté vacío
				if (numero_de_items==0){
				textNombre.setText("Usuario no encontrado");
				textApellido1.setText("Usuario no encontrado");
				textApellido2.setText("Usuario no encontrado");
				textEdad.setText("Usuario no encontrado");	
				}
			}
		}
		//inner class JcomboBox
		private class MiJComboBox implements ActionListener{
			public void actionPerformed(ActionEvent e) {
				
				//Por defecto mostrará en pantalla la primera coincidencia con el
				//nombre encontrado en la BBDD
				//Seleccionando sobre el desplegable del JComboBox se podrá cambiar si hay varias coincidencias
				
				u=(Usuario)comboBox.getSelectedItem();
				
				if (u!=null) //Si la lista no del Combox no está vacía
				{
			    textNombre.setText(u.getNombre());
				textApellido1.setText(u.getApellido1());
				textApellido2.setText(u.getApellido2());
				textEdad.setText(String.valueOf(u.getEdad()));
				//Comprobacion
				textArea.setText("El usuario seleccionado es: "+u.toString());}
			}
		}
		
		// inner class a la hora de presionar enter en textnombre y textapellidos
		public class ActionDatos implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {	
				JTextField EnterDatos=(JTextField)e.getSource();
				Jug.setNombre(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else
				Jug.setApellido1(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else
				Jug.setApellido2(EnterDatos.getText());// cogemos de jugador el texto y hacemos las comprobaciones con el if y else

	//En este punto me he dado cuenta que da igual porner getnombre o getapellidos sirve igualmente para los dos
				if (Jug.sonEspacios(Jug.getNombre())||(Jug.MetodoMaches(Jug.getNombre()))){
						EnterDatos.setText("Error, datos mal introducidos");
				}			
		}

		}
			//innerclass para comprobar la edad
			private class ComprobarEdad implements ActionListener{
				public void actionPerformed(ActionEvent e) {
					
					Jug.setEdad(999);
					// comprobamos primero que es un numero, si pues true.
					if(Jug.isNumeric(textEdad.getText())==true){
						Jug.setEdad(Integer.parseInt(textEdad.getText()));// si es true nos devuelve el texto
						BotonActualizar.setEnabled(false);// hacemos el boton Guarda editable true para guardar datos
					}
					// luego si es mayor que 100 o menos o igual que 1
					 if (Jug.getEdad()>=100 || Jug.getEdad()<=0){//Si la edad esta entre 1 y 100 la edad será correcta
						textEdad.setText("Error, datos mal introducidos");
					BotonActualizar.setEnabled(false);// hacemos el boton Guarda editable true para guardar datos

					}
					else {
						//BotonActualizar.setEnabled(true);
					//Concatenamos todo para que nos aparezca en el text area
					//textArea.setText(Jug.getNombre()+"  "+Jug.getApellido1()+"  de "+Jug.getEdad()+" años de edad");
					}
				}
			}
			
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
					}
					else System.out.println("EXITO EN LA CONEXION");
					}
				catch(Exception e)
				{
					System.out.println( " Debe haber algún problema con la BBDD o con la conexión.");	
					textArea.setText( " Debe haber algún problema con la BBDD o con la conexión.");	
				}
	}
			// inner class para los mouselistener a la hora de hacer click con el raton sobre el Jtextfield
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
}

