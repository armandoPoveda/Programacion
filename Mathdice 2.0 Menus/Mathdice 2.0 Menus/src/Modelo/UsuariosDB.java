package Modelo;

import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;

import java.sql.PreparedStatement;

import Visual.RegistroUsuario;

import java.awt.TextArea;
import java.sql.Connection;
import java.sql.ResultSet;

public class UsuariosDB {
	
	//BBDD usuarios, con tabla usuario. Sentencia SQL
			private static String USUARIOS_LIST="SELECT * FROM usuario";
			
			//Debe coincidir con los nombres de los campos de tabla usuario:id, nombre, apellido1, ....
			private static String ID_COL="id";
			private static String NOMBRE_COL="nombre";
			private static String APELLIDO1_COL="apellido1";
			private static String APELLIDO2_COL="apellido2";
			private static String EDAD_COL="edad";
			
			//Conexion
			private Connection conexion = null;// maneja la conexión
			private Statement instruccion = null;
			private ResultSet conjuntoResultados = null;
	//Conexion a la base de datos
			
		//Objeto para ejecutar una orden sobre la base de datos
		private Statement orden = null;
		//Constructor: recoge la conexión establecida para la base de datos
		public UsuariosDB(Connection c) {
			this.conexion=c;
			
		}
		
		//Método que permite insertar un usuario en la base de datos
		//La BBDD se llama usuarios, y la tabla correspondiente usuario
		public void insertarUsuario(String nombre,String apellido1,String apellido2,int edad){
			try{
				orden = conexion.createStatement();
			    String sql = "INSERT INTO usuario (nombre,apellido1,apellido2,edad) " +
			                   "VALUES ('"+nombre+"', '"+apellido1+"', '"+apellido2+"', "+edad+")";
			    orden.executeUpdate(sql);
			    System.out.println("Usuario registrado con exito");
			   
			   }catch(SQLException se){
				      //Se produce un error con la consulta
				      se.printStackTrace();
			   }catch(Exception e){
				      //Se produce cualquier otro error
				      e.printStackTrace();
			   }finally{
				      //Cerramos los recursos
				      try{
				         if(orden!=null)
				        	 orden.close();
				      }catch(SQLException se){
				    	  se.printStackTrace();
				      }
				      try{
				         if(conexion!=null)
				        	 conexion.close();
				      	 }catch(SQLException se){
				         se.printStackTrace();
				      }//end finally try
				}//end try
		}

				//Método que permite buscar un usuario en la base de datos devolviendolos en un JComboBox
				//Busca los usuarios por el nombre y devuelve todos los que tienen el mismo nombre
				//Método sobrecargado
					public void buscarUsuario(String nombreBuscar,JComboBox jc){
						ResultSet rs;
						try{
							orden = conexion.createStatement();
							  /*Si quisiésemos que devolviese todos los usuarios de la BBDD en el COMBOX haríamos
							   * String sql = "SELECT id,nombre, apellido1, apellido2, edad FROM usuario";
							   * y eliminaríamos el input de nombreBuscar en el método
							   */
						      String sql = "SELECT id,nombre, apellido1, apellido2, edad FROM usuario WHERE nombre='"+nombreBuscar+"'";
						      rs = orden.executeQuery(sql);
							//Cogemos los usuarios
							      while(rs.next()){
							    	  Usuario u=new Usuario(); //Se generará un usuario por cada coincidencia
							    	  u.setId(rs.getInt("id"));
								      u.setNombre(rs.getString("nombre"));
								      u.setApellido1(rs.getString("apellido1"));
								      u.setApellido2(rs.getString("apellido2"));
								      u.setEdad(rs.getInt("edad"));
								      //Añadimos el usuario encontrado al JComboBox
								      //El usuario no "será destruido" al salir del método porque se almacena en un objeto
								      //superior que lo guarda, que es el jc (un objeto ComboBox)
								      jc.addItem(u);
								      //Comprobación por monitor
								      System.out.println("Coincidencias: "+u.toString()+"\n");
							      }
							    //Debemos cerrar la conexion
							      rs.close();
								}catch(SQLException se){
									      //Se produce un error con la consulta
									      se.printStackTrace();
								}catch(Exception e){
									      //Se produce cualquier otro error
									      e.printStackTrace();
								}
					}
					//Método que permite buscar actualizar un usuario en la base de datos usando la
					//sentencia SQL UPDATE
							
							
								public void actualizarUsuario(Usuario u){
									try{
									  orden = conexion.createStatement();
									// create the java mysql update preparedstatement
									// Creamos la sentencia "tipo" que queremos ejecutar
								      String sql = "UPDATE usuario " +
							                       "SET nombre = ?"+
							                       ",apellido1 = ?"+
							                       ",apellido2 = ?"+
							                       ",edad = ? "+
								    		       "WHERE id = "+u.getId();
								      PreparedStatement preparedStmt = conexion.prepareStatement(sql);
								      
								      //Asignamos valores concretos a ?
								      preparedStmt.setString(1, u.getNombre());
								      preparedStmt.setString(2, u.getApellido1());
								      preparedStmt.setString(3, u.getApellido2());
								      preparedStmt.setInt(4, u.getEdad());
								 
								      // Se ejecuta la consulta y el update
								      
								      preparedStmt.executeUpdate();
								      
								      //Comprobación por monitor
								      System.out.println(sql+"\n");
								      System.out.println("Actualizado: "+u.toString()+"\n");
									}catch(SQLException se){
										      //Se produce un error con la consulta
										      se.printStackTrace();
									}catch(Exception e){
										      //Se produce cualquier otro error
										      e.printStackTrace();
									}
								}
							

							public void getDatosmodeloUsuarios() {
								try{
									instruccion = this.conexion.createStatement();
									conjuntoResultados = instruccion.executeQuery(USUARIOS_LIST);

									//Listaremos por pantalla los datos
									while( conjuntoResultados.next() ) {
										System.out.print(conjuntoResultados.getInt(ID_COL)+". ");
										System.out.print(conjuntoResultados.getString(NOMBRE_COL)+" ");
										System.out.print(conjuntoResultados.getString(APELLIDO1_COL)+" ");
										System.out.print(conjuntoResultados.getString(APELLIDO2_COL)+", ");
										System.out.println(conjuntoResultados.getInt(EDAD_COL)+".\n");
									}// fin de while
								}
								catch( SQLException excepcionSql ) 
								{
									excepcionSql.printStackTrace();
								}
								finally{
									try{ //Cerramos la conexión
										conjuntoResultados.close();
										instruccion.close();
										conexion.close();
									}
									catch( SQLException excepcionSql ) 
									{
										excepcionSql.printStackTrace();
									}
								}								
							}

							
							
}



