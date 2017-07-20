/*
 * Creamos una clase Jugador, sin el MAIN pero sin con el constructor
 * Aquï¿½ es donde irï¿½ toda la informacion del Jugador, los datos que contendra
 */
package Modelo;// paquete Propiedades

public class Usuario {//creamos una clase Jugador para los Datos
	
	/*
	 * Crearemos las variables que vayamos utilizar, del tipo que sean INT, String, Float, Boolean...
	 */
	private int Id;
	private String Nombre;
	private String Apellido1;
	private String Apellido2;
	private int Edad=0;
	private int Puntuacion;
	


	// comenzamos con el constructor
	public Usuario() {

	}
	
	// añadimos los setters y getters correspondientes
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}

	public String getApellido1() {
		return Apellido1;
	}

	public void setApellido1(String apellido1) {
		this.Apellido1 = apellido1;
	}
	
	public String getApellido2() {
		return Apellido2;
	}

	public void setApellido2(String apellido2) {
		Apellido2 = apellido2;
	}

	public int getEdad() {
		return Edad;
	}

	public void setEdad(int edad) {
		this.Edad = edad;
	}
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		this.Id = id;
	}
	
	public int getPuntuacion() {
		return Puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.Puntuacion = puntuacion;
	}
	
	//Permitirá mostrar todos los campos de usuario
			//Sobreescribimos el método toString que se hereda automáticamente por se un objeto de java
			//Es un método que "transforma" un objeto en una cadena
	public String toString(){
		return this.Id+" "+this.Nombre+" "+this.Apellido1+" "+this.Apellido2+" "+this.Edad;
	}
	
	//creamos metodos para las comprobacines de los datos que estan introduciendo, son correctos
	// boolean para comprobar si es numero, si es numero nos dará¡ true para comprobar la edad
	//añadimos un try{}catch{} para comprobar que es correcto y el programa no se nos quede colgado, nos dara una "Exception e" y nos retornarÃ¡ falso
	public boolean isNumeric(String Text) {
		try {Integer.parseInt(Text);
		return true;
		}catch (NumberFormatException nfe){
			return false;
		}
	}
	  public boolean numeros (String num)
	  { 
		  if (getEdad()>100||getEdad()<0)
		return false;
		  else
			  return true;
	  }
		// boolean para los espacios en blanco
		public boolean sonEspacios (String cad)
		{
			for(int i=0; i<cad.length(); i++)
				if(cad.charAt(i)!=' ')
					return false;
			return true;
		}
		
		// he creado un boolean con el método 
		//maches para comprobar que no es un dígito si no un caracter en el campo de nombre y apellidos
		public boolean MetodoMaches (String cadena)
		{
			if (cadena.matches("[0123456789].*")||cadena.matches("Error, datos mal introducidos"))
				return true;	
			else
				return false;
		}
	}
