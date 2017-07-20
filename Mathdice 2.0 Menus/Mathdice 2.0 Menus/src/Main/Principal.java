package Main;//Paquete Ventana MAin

//import Visual.Perfil;

import java.sql.Connection;
import Modelo.ConexionST;
import Modelo.ConexionDB;
import Modelo.UsuariosDB;
//import Visual.Juego;//importamos Juego y Login
import Visual.RegistroUsuario;
import Visual.VentanaMenu;

public class Principal {// la clase principal que contendra el MAIN
	
	private static ConexionDB usuariosDB1;
	private static ConexionDB usuariosDB2;
	private static boolean connected1=false;
	private static boolean connected2=false;
	private static Connection conexion1;
	private static Connection conexion2;
	private static UsuariosDB mUsuario;
	
	public static void main(String[] args) {
		
		/*
		  * La clase principal que hemos creado contendrÃ¡ todas las ventanas que vayamos aÃ±adiendo
		  * Los JFRAME	
		  */
				// Creamos un objeto para La ventana de LOGIN
				/*
				 * al crear el objeto nos dara un error en LOGIN, ponemos el cursos sobre la palabra LOGIN
				 * e importamos la libreria que harÃ¡ referencia a la ventana LOGIN
				 */
		VentanaMenu VM=new VentanaMenu();
		RegistroUsuario login=new RegistroUsuario(VM);

				/*
				 *  para que el LOGIN sea visible, hacemos un setVisible de LOGIN boolean True
				 *   para que aparezca la ventana al compilar
				 */
				login.setVisible(true); 
				//VM.setVisible(true);

				//Generado el objeto usuariosDB que creará una única instancia static de esta clase
				usuariosDB1=ConexionDB.getInstance("localhost","usuarios","root","");
				usuariosDB2=ConexionDB.getInstance("localhost","usuarios","root","");
				
				if (usuariosDB2==usuariosDB1)
				{System.out.println("Ya hay una instancia del objeto y no se crea otra diferente\n");}
				
				//Ejemplo para un host externo
				//usuariosDB=ConexionDB.getInstance("mysql.hostinger.es","u499092340_dam20","u499092340_dam20","12345");
				
				
				//Establecemos la conexion
				connected1=usuariosDB1.connectDB();
				connected2=usuariosDB2.connectDB();
				
				//Esta es la conexión
				conexion1=usuariosDB1.getConexion();
				conexion2=usuariosDB2.getConexion();
				
				if (conexion1==conexion2)
				{System.out.println("Solo tengo una conexión\n");}

		        //Comprobamos si hemos conectado y sacamos por consola los datos de la tabla
				if(connected1) {
					System.out.println("CONECTADOS CON EXITO\n");
					System.out.println("LISTADO DE USUARIOS\n");
					mUsuario=new UsuariosDB(conexion1);
					mUsuario.getDatosmodeloUsuarios();
		            
				}
				else System.out.println("ERROR EN LA CONEXION");
			    }
	}

