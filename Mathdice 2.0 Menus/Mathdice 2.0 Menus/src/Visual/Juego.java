package Visual;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

public class Juego extends JPanel {
	
		private JPanel contentPane = new JPanel();
		private JLabel Operaciones = new JLabel("");
		private JLabel Puntuacion = new JLabel("Tu puntuacion es:");
		//variables para los botonoes
		public JButton BotonMas = new JButton("+");
		public JButton BotonMenos = new JButton("-");
		public JButton BotonMath = new JButton("MATHDICE");
		private JButton BotonStart = new JButton("START");
		private JLabel ResultadoFinal = new JLabel("");
		private JLabel ImagenMath = new JLabel("");
		public JLabel LabelNombre = new JLabel("BIENVENIDO AL JUEGO");
		
		//label para dados
		private JLabel Dado1 = new JLabel();
		private JLabel Dado2 = new JLabel();
		private JLabel Dado3 = new JLabel();
		private JLabel Dado4 = new JLabel();
		private JLabel Dado5 = new JLabel();
		private JLabel Dado6 = new JLabel();
		//variables para realizar las operaciones suma y resta
		private final static int SUMA=0;
		private final static int RESTA=1;
		private int tipoOp=0;
		//varables para almacenar los numeros y sus correspodientes String
		private int num1=0;
		private String numTxt1="";
		private int num2=0;
		private String numTxt2="";
		
		//variable tipo boolean para comprobar si es el primer numero intrducido o no
		private boolean isNum1=true;
		private boolean isNum2=true;
		private boolean suma=true;
		
		//array de ImageIcon para dados con valor de 1 a 3 para guardar las caras de los dados
		public ImageIcon[] dados3=new ImageIcon[3];
		public ImageIcon[] dados6=new ImageIcon[6];
		public ImageIcon[] dados12=new ImageIcon[12];
		
		// clase Random para las imagenes, que sean aleatorios
		 public Random dado=new Random();
		
		// array para los numeros almacenados de  enteros donde guardare  numeros aleatorios de random
		public int[] numerosAlmacenadosDados3=new int [3];
		public int[] numerosAlmacenadosDados6=new int [2];
		public int[] numerosAlmacenadosDados12=new int [1];

		// importamos la clase jugador
			Usuario Jug=new Usuario();
		// variable semaforo al hacer click en la imagen dado que es lo que hará
		private boolean tocaNumero=true;
		// variable para las operaciones
		private boolean tocaOperacion=true;
		//variable para guardar el resualtado de las operaciones
		private int resultado;
		
		// con ImageIcon colocamos las imagenes que queramos utilizar
		public ImageIcon imagenGris=new ImageIcon(getClass().getResource("/imagenes/dadogris.png"));
		public ImageIcon imagenMath=new ImageIcon(getClass().getResource("/imagenes/math-dice-2.jpg"));

		// comenzamos con el constructor
		public Juego() {
			Jug.setPuntuacion(Jug.getPuntuacion());
			
			setBorder(new EmptyBorder(5, 5, 5, 5));
			setLayout(null);
			
			//Label para recoger el nombre del usuario
			LabelNombre.setHorizontalAlignment(SwingConstants.LEFT);
			LabelNombre.setBounds(562, 27, 258, 26);
			add(LabelNombre);
			
			//Label para la imagen que sale nada mas iniciar el Juego
			ImagenMath.setBounds(16, 27, 434, 512);
			ImagenMath.setIcon(imagenMath);
			add(ImagenMath);
			
			//Label para la puntuacion
			Puntuacion.setBounds(562, 68, 176, 26);
			add(Puntuacion);
			
			
			//Boton Mas
			BotonMas.setBounds(475, 117, 141, 35);
			BotonMas.addActionListener(new BotonOperaciones());
			add(BotonMas);
			
			//BotonMenos
			BotonMenos.setBounds(652, 117, 141, 35);
			BotonMenos.addActionListener(new BotonOperaciones());
			add(BotonMenos);
			
			//Label donde saldran las Operaciones a realizar
			Operaciones.setHorizontalAlignment(SwingConstants.CENTER);
			Operaciones.setBounds(513, 187, 225, 35);
			add(Operaciones);
			
			//BotonMath
			BotonMath.addActionListener(new BotonMath());	
			BotonMath.setBounds(562, 264, 153, 35);
			add(BotonMath);
			
			//Boton Start
			BotonStart.addActionListener(new BotonStart());
			BotonStart.setBounds(562, 343, 153, 35);
			add(BotonStart);
			
			//JLabel para el resultado Final
			ResultadoFinal.setHorizontalAlignment(SwingConstants.CENTER);
			ResultadoFinal.setBounds(498, 452, 261, 26);
			add(ResultadoFinal);
			
			//DADO1
			Dado1.setBounds(6, 21, 153, 150);
			add(Dado1);
			
			//DADO2
			Dado2.setBounds(153, 21, 153, 150);
			add(Dado2);
			
			//DADO3
			Dado3.setBounds(302, 21, 153, 150);
			add(Dado3);
			
			//DADO4
			Dado4.setBounds(6, 217, 153, 150);
			add(Dado4);
					
			//DADO5
			Dado5.setBounds(153, 217, 153, 150);
			add(Dado5);
					
			//DADO06
			Dado6.setBounds(33, 379, 167, 160);
			add(Dado6);
			
			
		
			}
		
		public void calcular(String numTxt1, String numTxt2){
			
			
		    int num1, num2, SUMA, RESTA;

		    if(!numTxt1.isEmpty() && !numTxt2.isEmpty()){

		        num1=Integer.parseInt(numTxt1);
		        num2=Integer.parseInt(numTxt2);

		        SUMA=num1+num2;
		        RESTA=num1-num2;

		        Operaciones.setText(String.valueOf(SUMA));
		        Operaciones.setText(String.valueOf(RESTA));

		    }else{

		        JOptionPane.showMessageDialog(null, "Debe ingresar los n�meros para "
		        + "realizar las operaciones", "Error en la operaci�n", JOptionPane.ERROR_MESSAGE);

		    }

		}           
		
		//inner class BotonMath
		private class BotonMath implements ActionListener{
			
			public void actionPerformed(ActionEvent arg0) {//añadimos un action listener para cuando lo pulsemos haga algo
				
				
				ResultadoFinal.setText(ResultadoFinal.getText());
				BotonStart.setEnabled(true);//Boton start enable true
				BotonMath.setEnabled(false);//Boton Math enable False
				BotonMas.setEnabled(false);// Botnon Mas enable false
				BotonMenos.setEnabled(false);
					tocaNumero=false;// TocaNumero, no pues falso, utilizamos las comprobaciones tipo semaforo
					tocaOperacion=false;//TocaOperacion, no pues falso
					
			 if(isNum2){
				 Operaciones.setText(Operaciones.getText());
				 String num1=numTxt1.toString();
					String num2=numTxt2.toString();
					calcular(num1, num2);
			 }
			}
		}
		
		//inner class botonStart
		private class BotonStart implements ActionListener{
			public void actionPerformed(ActionEvent arg0) {//actionListener para que haga algo cuando apretemos
				InicializarJuego();//incializamos Juego, con esto conseguiremos que se incialice el juego con unas 					
				
				//series de intruciones que creamos al inciarlizar el juego
					BotonMas.setEnabled(true);
					BotonMenos.setEnabled(true);
					BotonStart.setEnabled(false);
					BotonMath.setEnabled(true);
					Operaciones.setText("");
					ResultadoFinal.setText("");
					ImagenMath.setIcon(null);
					
					//las comprobaciones tipo semaforo
						tocaNumero=true;
						tocaOperacion=false;
			}
		}


		// inner class para los botones + y -
		//creamos una inner class para los botones de las operaciones, con esto conseguimos que los botones que vaya a realizar la misma operacion
		//podamos ordenarlos en una inner class y no tener que ñadir a cada boton un actionlistener diferente, con un solo action listener controlamos
		// los dos botones, nuestro codigo quedará mas aseado
		private class BotonOperaciones implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {

				JButton BotonOp=(JButton) e.getSource();// con e.getsource reconocerá el boton sobre el que hemos pulsado, le damos un nombre ara todos
				//Los botones "BotonOp"
				
			if(tocaOperacion){//Si toca operación
				BotonOp.setText(BotonOp.getText());//coge el texto sobre el que hemos hecho click
					Operaciones.setText(Operaciones.getText()+BotonOp.getText());//muestramelo por el Jlabel operaciones
						tocaOperacion=false;//ahora tocara número y operación la ponemos como false
						tocaNumero=true;//tocanumero true
			}
			}
		}
		
		
		// inner class para los JLabels de las imagenes de los dados
		/*con esto conseguiremos hacer un solo MouseListener para todos los Jlabels de los Dados
		 * al añadir el mouselistener el eclipse nos añade todos los metodos de la clase, esto lo hace porque si no, no funcionaria
		 * */
		private class ListenerDados implements MouseListener{

			@Override
			public void mouseClicked(MouseEvent e) {
				
				BotonMas.setEnabled(true);
				BotonMenos.setEnabled(true);
				BotonMath.setEnabled(true);
					
					JLabel dados= (JLabel)e.getSource();//e.getsource nos reconocera sobre que label hemos hecho click
				 	 int numeroDado=Integer.valueOf(dados.getName()); //pasamos el valor de dados de String a int
				 	
						if(tocaNumero) //Tocanumero
							if(numeroDado<4){// coge el dato del array y si es menor que 4 nos recogera el numero del array dados3
								dados.setName(String.valueOf(numerosAlmacenadosDados3[numeroDado-1]+1));//coge el nombre del dado que hemos hecho click
								dados.setIcon(imagenGris);// ponemos el jlabel la imagenGris
								dados.removeMouseListener(this);//removemos el label sobre el que hemos hecho click para no volver a recoger ese dato
								Operaciones.setText(Operaciones.getText()+dados.getName());// mostramos en operaciones el numero del label que hicimos click
								tocaOperacion=true;//operacion a true
								tocaNumero=false;//numero a false	
							}else{//si no es el array de dados3, pues es el array de dados6, hacemos lo mismo
							dados.setName(String.valueOf(numerosAlmacenadosDados6[numeroDado-4]+1));
							tocaOperacion=true;
							tocaNumero=false;
							dados.setIcon(imagenGris);
							dados.removeMouseListener(this);
							Operaciones.setText(Operaciones.getText()+dados.getName());
						}
			}	
			//nos añade todos los metodos de la clase mouselistener, aunque no los utilizemos, tienen que estar para que funcione correctacmente
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
		//inicializaremos el Juego, esto lo creamos para, por ejemplo, cuando lo añadimos al boton start lo que hace es reiniciar todo el juego
		//para poder empezar de nuevo.
		private void InicializarJuego() {

			//recoremos el array para cada dado con un for
			for(int i=0; i<dados3.length; i++){
				//le indicamos de donde tiene que coger las imagenes
				dados3[i]=new ImageIcon(getClass().getResource("/imagenes/dado"+String.valueOf(i+1)+"_3.png"));
				System.out.println("dados3"+i);
			}
			//recoremos el array para los numeros almacenados
				for (int i=0; i<numerosAlmacenadosDados3.length; i++){
					numerosAlmacenadosDados3[i]=dado.nextInt(3);	//utilizamos la clase Random para que nos cambie las imagenes aleatoriamente
				// y colocamos cada imagen en un label
				Dado1.setIcon(dados3[numerosAlmacenadosDados3[0]]);
				Dado1.setName("1");
				Dado2.setIcon(dados3[numerosAlmacenadosDados3[1]]);
				Dado2.setName("2");
				Dado3.setIcon(dados3[numerosAlmacenadosDados3[2]]);
				Dado3.setName("3");
				

		}
				//dados6 random
				//hacemos los mismo con los dados 6
				for(int i=0; i<dados6.length; i++){
					dados6[i]=new ImageIcon(getClass().getResource("/imagenes/dado"+String.valueOf(i+1)+"_6.png"));
					System.out.println("dados6"+i);
				}
					for (int i=0; i<numerosAlmacenadosDados6.length; i++){
						numerosAlmacenadosDados6[i]=dado.nextInt(6);	
					
					Dado4.setIcon(dados6[numerosAlmacenadosDados6[0]]);
					Dado4.setName("4");
					Dado5.setIcon(dados6[numerosAlmacenadosDados6[1]]);
					Dado5.setName("5");
					}
					
					//dado12 random
					//y lo mismo para el dado de 12 caras
					for(int i=0; i<dados12.length; i++){
						dados12[i]=new ImageIcon(getClass().getResource("/imagenes/dadodoce_"+String.valueOf(i+1)+".png"));
						System.out.println("dados12"+i);
					}
						for (int i=0; i<numerosAlmacenadosDados12.length; i++){
							numerosAlmacenadosDados12[i]=dado.nextInt(12);	

						Dado6.setIcon(dados12[numerosAlmacenadosDados12[0]]);
						Dado6.setName("12");
						}
						//llamada al MouseListener cuando inicializamos el juego para que vuelvan
						//a la inner class correspondiente y empezar de nuevo con el juego
						Dado1.addMouseListener(new ListenerDados());
						Dado2.addMouseListener(new ListenerDados());
						Dado3.addMouseListener(new ListenerDados());
						Dado4.addMouseListener(new ListenerDados());
						Dado5.addMouseListener(new ListenerDados());


	}
}
