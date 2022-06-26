package es.studium.Kiriki;


import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class Jugando extends Frame
{
	private static final long serialVersionUID = 1L;
	
	Toolkit herramientas; // Llamamos a Toolkit para poder trabajar con graphics
	Image tapete, cubileteBocaArriba, cubileteBocaAbajo; //Imagenes fondo y cubilete
	Image D1, D2, D3, D4, D5, D6; // Imagenes de las caras de los dados
	
	String jugador1, jugador2, jugador3, jugador4; // Cadenas que almacenan el nombre de los jugadores escrito en la creacion de la partida
	int numJugadores = 0; // Entero que guarda el numero de jugadores elegidos en la creacion de la partida
	int turnoJugador = 1; // Entero que nos ayuda a actualizar el turno de los jugadores
	
	int imagenAmostrar1 = 0; // Entero para un switch donde se muestra la cara obtenida del dado 1 en la tirada
	int imagenAmostrar2 = 0; // Entero para un switch donde se muestra la cara obtenida del dado 2 en la tirada
	int voltearCubilete = 0; // Entero para un switch donde se anima al cubilete tirando dados
	int recuperarCubilete = 0; // Entero para un switch donde se anima al cubilete tapando dados
	
	int vidasJugador1 = 0; // Enteros donde se guarda las vidas de los jugadores
	int vidasJugador2 = 0;
	int vidasJugador3 = 0;
	int vidasJugador4 = 0;
	
	Color myColor = new Color(60, 179, 113); // Color de los dialogos
	
	Dialog dlgMensajeComienzoPartida = new Dialog(this, "Comienzo Partida"); // Mensaje aviso de que ha empezado la partida
	Label lblMensajeComienzoPartida = new Label();
	Button btnMensajeComienzoPartida = new Button("A jugar!");
	Panel pnlComienzoPartida = new Panel();
	
	Dialog dlgMensajeValorTirada = new Dialog(this, "Resultado Tirada"); // Mensaje con el resultado de la tirada y los valores posibles para anunciar al resto
	Label lblMensajeValorTirada = new Label();
	Label lblMensajeAnunciarValor = new Label();
	Panel pnlLabelValorTirada = new Panel();
	Panel pnlCheckboxValorTirada = new Panel();
	CheckboxGroup chkgrValorTirada = new CheckboxGroup();
	Checkbox chkCuatro = new Checkbox("4", false, chkgrValorTirada);
	Checkbox chkCinco = new Checkbox("5", false, chkgrValorTirada);
	Checkbox chkSeis = new Checkbox("6", false, chkgrValorTirada);
	Checkbox chkSiete = new Checkbox("7", false, chkgrValorTirada);
	Checkbox chkOcho = new Checkbox("8", false, chkgrValorTirada);
	Checkbox chkNueve = new Checkbox("9", false, chkgrValorTirada);
	Checkbox chkDiez = new Checkbox("10", false, chkgrValorTirada);
	Checkbox chkLadrillazo = new Checkbox("Ladrillazo", false, chkgrValorTirada);
	Checkbox chkParejaNegras = new Checkbox("Pareja de Negras", false, chkgrValorTirada);
	Checkbox chkParejaRojas = new Checkbox("Pareja de Rojas", false, chkgrValorTirada);
	Checkbox chkParejaJacks = new Checkbox("Pareja de Jotas", false, chkgrValorTirada);
	Checkbox chkParejaQueens = new Checkbox("Pareja de Reinas", false, chkgrValorTirada);
	Checkbox chkParejaKings = new Checkbox("Pareja de Reyes", false, chkgrValorTirada);
	Checkbox chkParejaAces = new Checkbox("Pareja de Ases", false, chkgrValorTirada);
	Button btnAnunciarValor = new Button("Anunciar Valor");
	
	Dialog dlgMensajeValorAnunciado = new Dialog(this, "Resultado Anunciado"); // Mensaje con el valor anunciado por el jugador que ha tirado los dados
	Label lblMensajeValorAnunciado = new Label();
	Button btnMensajeValorAnunciado = new Button("Vale");
	Label lblMensajeTurno = new Label();
	Panel pnlValorAnunciado =new Panel();
	
	
	Dialog dlgMensajeValorRecibido = new Dialog(this, "Resultado Recibido"); // Mensaje para el jugador que tiene que decidir si levantar el cubilete o seguir tirando
	Label lblMensajeValorRecibido = new Label();
	Button btnAceptarValor = new Button("Superar Tirada");
	Button btnRechazarValor = new Button("Destapar Dados");
	
	Dialog dlgMensajeValorAceptado = new Dialog(this, "Resultado Aceptado"); // Mensaje que aparece cuando un jugador decide seguir tirando los dados e intenta superar la anterior tirada
	Label lblMensajeValorAceptado = new Label();
	Button btnMensajeValorAceptado = new Button("Vale");
	
	Dialog dlgMensajeValorRechazado = new Dialog(this, "Resultado Rechazado"); // Mensaje que aparece cuando un jugador decide levantar el cubilete y ver si el jugador anterior ha mentido o no
	Label lblMensajeValorRechazado = new Label();
	Button btnMensajeValorRechazado = new Button("Vale");
	Panel pnlValorRechazado = new Panel();
	Label lblMensajeValorVerdadero = new Label();
	Label lblMensajeValorVerdadero1 = new Label(); 
	Label lblMensajeValorFalso = new Label();
	Label lblMensajeValorFalso1 = new Label(); 	
	
	Dialog dlgMensajeKiriki = new Dialog(this, "Kiriki!!"); // Mensaje que aparece cuando a un jugador le ha salido un kiriki en la tirada
	Label lblMensajeKiriki = new Label();
	Button btnMensajeKiriki = new Button("Toma ya!");
	
	Dialog dlgMensajeFinPartida = new Dialog(this, "Fin"); // Mensaje que aparece cuando un jugador se sale con la victoria
	Label lblMensajeFinPartida = new Label();
	Button btnMensajeFinPartida = new Button("Se acabo!");
	
	Font fuenteTurno = new Font("Harlow Solid Italic", Font.BOLD, 20); // Fuente usada para el turno del jugador actual
	Font fuenteJugadores = new Font("Harlow Solid Italic", Font.PLAIN, 18); // Fuente usada para el nombre de los jugadores y sus vidas restantes
	
	// Constructor
	public Jugando(int n, String j1, String j2, String j3, String j4)
	{
		numJugadores = n;
		jugador1 = j1;
		jugador2 = j2;
		jugador3 = j3;
		jugador4 = j4;
		
		herramientas = getToolkit(); // Imagenes del fondo, el cubilete y las caras de los dados
		tapete = herramientas.getImage("tapete612x408.jpg");
		cubileteBocaArriba = herramientas.getImage("cubilete.png");
		cubileteBocaAbajo = herramientas.getImage("cubilete2.png");

		D1 = herramientas.getImage("dadoNegro.png");
		D2 = herramientas.getImage("dadoRojo.png");
		D3 = herramientas.getImage("dadoJack.png");
		D4 = herramientas.getImage("dadoQueen.png");
		D5 = herramientas.getImage("dadoKing.png");
		D6 = herramientas.getImage("dadoAce.png");
		
		setTitle("Kiriki: Jugando"); // Titulo
		setSize(620, 446); // Tamanio del Frame
		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
		
		dlgMensajeComienzoPartida.setBackground(myColor);
		dlgMensajeComienzoPartida.setLayout(new FlowLayout());
		dlgMensajeComienzoPartida.setSize(180, 80);
		dlgMensajeComienzoPartida.setLocationRelativeTo(null);
		dlgMensajeComienzoPartida.setResizable(false);
		dlgMensajeComienzoPartida.setUndecorated(true);
		dlgMensajeComienzoPartida.add(lblMensajeComienzoPartida,"Center");
		dlgMensajeComienzoPartida.add(pnlComienzoPartida);
		pnlComienzoPartida.add(btnMensajeComienzoPartida, "Center");
		
		dlgMensajeValorTirada.setBackground(myColor);
		dlgMensajeValorTirada.setLayout(new GridLayout(2, 1));
		dlgMensajeValorTirada.setSize(300, 320);
		dlgMensajeValorTirada.setLocation(570, 395);
		dlgMensajeValorTirada.setResizable(false);
		dlgMensajeValorTirada.setUndecorated(true);
		pnlLabelValorTirada.add(lblMensajeValorTirada, "Center");
		pnlLabelValorTirada.add(lblMensajeAnunciarValor, "South");
		dlgMensajeValorTirada.add(pnlLabelValorTirada);
		pnlCheckboxValorTirada.add(chkCuatro);
		pnlCheckboxValorTirada.add(chkCinco);
		pnlCheckboxValorTirada.add(chkSeis);
		pnlCheckboxValorTirada.add(chkSiete);
		pnlCheckboxValorTirada.add(chkOcho);
		pnlCheckboxValorTirada.add(chkNueve);
		pnlCheckboxValorTirada.add(chkDiez);
		pnlCheckboxValorTirada.add(chkLadrillazo);
		pnlCheckboxValorTirada.add(chkParejaNegras);
		pnlCheckboxValorTirada.add(chkParejaRojas);
		pnlCheckboxValorTirada.add(chkParejaJacks);
		pnlCheckboxValorTirada.add(chkParejaQueens);
		pnlCheckboxValorTirada.add(chkParejaKings);
		pnlCheckboxValorTirada.add(chkParejaAces);
		pnlCheckboxValorTirada.add(btnAnunciarValor);
		dlgMensajeValorTirada.add(pnlCheckboxValorTirada, "South");
		
		
		dlgMensajeValorRecibido.setBackground(myColor);
		dlgMensajeValorRecibido.setLayout(new FlowLayout());
		dlgMensajeValorRecibido.setSize(410, 60);
		dlgMensajeValorRecibido.setLocationRelativeTo(null);
		dlgMensajeValorRecibido.setResizable(false);
		dlgMensajeValorRecibido.setUndecorated(true);
		dlgMensajeValorRecibido.add(lblMensajeValorRecibido);
		dlgMensajeValorRecibido.add(btnAceptarValor);
		dlgMensajeValorRecibido.add(btnRechazarValor);
		
		dlgMensajeValorAceptado.setBackground(myColor);
		dlgMensajeValorAceptado.setLayout(new FlowLayout());
		dlgMensajeValorAceptado.setSize(330, 60);
		dlgMensajeValorAceptado.setLocationRelativeTo(null);
		dlgMensajeValorAceptado.setUndecorated(true);
		dlgMensajeValorAceptado.setResizable(false);
		dlgMensajeValorAceptado.add(lblMensajeValorAceptado);
		dlgMensajeValorAceptado.add(btnMensajeValorAceptado);
		
		dlgMensajeValorRechazado.setBackground(myColor);
		dlgMensajeValorRechazado.setLayout(new FlowLayout());
		dlgMensajeValorRechazado.setSize(400, 110);
		dlgMensajeValorRechazado.setLocationRelativeTo(null);
		dlgMensajeValorRechazado.setResizable(false);
		dlgMensajeValorRechazado.setUndecorated(true);
		dlgMensajeValorRechazado.add(lblMensajeValorRechazado);
		dlgMensajeValorRechazado.add(lblMensajeValorVerdadero);
		dlgMensajeValorRechazado.add(lblMensajeValorVerdadero1);
		dlgMensajeValorRechazado.add(lblMensajeValorFalso);
		dlgMensajeValorRechazado.add(lblMensajeValorFalso1);
		dlgMensajeValorRechazado.add(pnlValorRechazado);
		pnlValorRechazado.add(btnMensajeValorRechazado);
		
		
		dlgMensajeKiriki.setBackground(myColor);
		dlgMensajeKiriki.setLayout(new FlowLayout());
		dlgMensajeKiriki.setSize(400, 70);
		dlgMensajeKiriki.setLocationRelativeTo(null);
		dlgMensajeKiriki.setUndecorated(true);
		dlgMensajeKiriki.setResizable(false);
		dlgMensajeKiriki.add(lblMensajeKiriki);
		dlgMensajeKiriki.add(btnMensajeKiriki);
		
		dlgMensajeFinPartida.setBackground(myColor);
		dlgMensajeFinPartida.setLayout(new FlowLayout());
		dlgMensajeFinPartida.setSize(500, 100);
		dlgMensajeFinPartida.setLocationRelativeTo(null);
		dlgMensajeFinPartida.setResizable(false);
		dlgMensajeFinPartida.add(lblMensajeFinPartida);
		dlgMensajeFinPartida.add(btnMensajeFinPartida);
	}
	
	// Dibujamos el fondo, el cubilete, los dados, el turno actual, los nombres y las vidas restantes
	public void paint(Graphics g)
	{
		g.drawImage(tapete,  0,  30,  this); // Tapete de fondo
		
		// Cambio imagen cubilete
		switch(voltearCubilete)
		{
			case 0:
				g.drawImage(cubileteBocaAbajo, 270, 180,  this);
			case 1:
				g.drawImage(cubileteBocaAbajo, 1000, 1000,  this);
		}
		switch(recuperarCubilete)
		{
			case 1:
				g.drawImage(cubileteBocaArriba, 270, 180,  this);
		}
		
		// Mostrar dados
		switch(imagenAmostrar1)
		{
			case 1:
				g.drawImage(D1, 210, 80, this);
				break;
			case 2:
				g.drawImage(D2, 210, 80, this);
				break;
			case 3:
				g.drawImage(D3, 210, 80, this);
				break;
			case 4:
				g.drawImage(D4, 210, 80, this);
				break;
			case 5:
				g.drawImage(D5, 210, 80, this);
				break;
			case 6:
				g.drawImage(D6, 210, 80, this);
				break;
		}
		
		switch(imagenAmostrar2)
		{
			case 1:
				g.drawImage(D1, 310, 80, this);
				break;
			case 2:
				g.drawImage(D2, 310, 80, this);
				break;
			case 3:
				g.drawImage(D3, 310, 80, this);
				break;
			case 4:
				g.drawImage(D4, 310, 80, this);
				break;
			case 5:
				g.drawImage(D5, 310, 80, this);
				break;
			case 6:
				g.drawImage(D6, 310, 80, this);
				break;
		}
		
		g.setFont(fuenteTurno);
		
		// Turno de jugador actual
		switch(turnoJugador)
		{
			case 1:
				g.setColor(Color.yellow);
				g.drawString("Turno de:", 450, 60);
				g.drawString(jugador1, 440, 90);
				break;
			case 2:
				g.setColor(Color.red);
				g.drawString("Turno de:", 450, 60);
				g.drawString(jugador2, 440, 90);
				break;
			case 3:
				g.setColor(Color.green);
				g.drawString("Turno de:", 450, 60);
				g.drawString(jugador3, 440, 90);
				break;
			case 4:
				g.setColor(Color.white);
				g.drawString("Turno de:", 450, 60);
				g.drawString(jugador4, 440, 90);
				break;
		}
		
		g.setFont(fuenteJugadores);
		
		// Nombre de ugadores y vidas restantes
		g.setColor(Color.yellow);
		g.drawString(jugador1 + ": " + vidasJugador1 + " vidas", 45, 54);
		g.fillOval(18, 40, 20, 20); // Ficha Amarilla
		
		g.setColor(Color.red);
		g.drawString(jugador2 + ": " + vidasJugador2 + " vidas",  45, 84);
		g.fillOval(18, 70, 20, 20); // Ficha Azul
		
		switch(numJugadores)
		{
			case 3:
				g.setColor(Color.green);
				g.drawString(jugador3 + ": " + vidasJugador3 + " vidas", 45, 114);
				g.fillOval(18, 100, 20, 20); // Ficha Verde
				break;
			case 4:
				g.setColor(Color.green);
				g.drawString(jugador3 + ": " + vidasJugador3 + " vidas", 45, 114);
				g.fillOval(18, 100, 20, 20);
				g.setColor(Color.white);
				g.drawString(jugador4 + ": " + vidasJugador4 + " vidas", 45, 144);
				g.fillOval(18, 130, 20, 20); // Ficha Roja
				break;
		}
	}
	
	public void cargarDados() // Cargamos en el controlador las imagenes de los dados
	{
		D1 = herramientas.getImage("dadoNegro.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D2 = herramientas.getImage("dadoRojo.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D3 = herramientas.getImage("dadoJack.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D4 = herramientas.getImage("dadoQueen.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D5 = herramientas.getImage("dadoKing.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D6 = herramientas.getImage("dadoAce.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
	}
	
	public void mostrarDadoCubiletes(int dado1, int dado2) // Mostramos el resultado de la tirada, se oculta con: 0, 0 como parametro
	{
		imagenAmostrar1 = dado1;
		imagenAmostrar2 = dado2;
		repaint();
	}
	
	public void mostrarValorAnunciado(String valorCheckbox) // Mostramos mensaje con valor anunciado por jugador anterior y avisamos al siguiente jugador
	{
		
		
		if(numJugadores == 4)
		{
			if(turnoJugador == 1)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador1 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador2);
			}
			else if(turnoJugador == 2)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador2 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador3);
			}
			else if(turnoJugador == 3)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador3 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador4);
			}
			else if(turnoJugador == 4)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador4 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador1);
			}
		}
		else if(numJugadores == 3)
		{
			if(turnoJugador == 1)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador1 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador2);
			}
			else if(turnoJugador == 2)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador2 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador3);
			}
			else if(turnoJugador == 3)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador3 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador1);
			}
		}
		else if(numJugadores == 2)
		{
			if(turnoJugador == 1)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador1 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador2);
			}
			else if(turnoJugador == 2)
			{
				lblMensajeValorAnunciado.setText("El valor anunciado por " + jugador2 + " es: " + valorCheckbox);
				lblMensajeTurno.setText("\r" + "Ahora es el turno de " + jugador1);
			}
		}
		
		dlgMensajeValorAnunciado.setBackground(myColor);
		dlgMensajeValorAnunciado.setLayout(new GridLayout(3, 3));
		dlgMensajeValorAnunciado.add(btnMensajeValorAnunciado);
		dlgMensajeValorAnunciado.add(lblMensajeValorAnunciado, "Center");
		dlgMensajeValorAnunciado.add(lblMensajeTurno, "Center");
		dlgMensajeValorAnunciado.add(pnlValorAnunciado);
		pnlValorAnunciado.add(btnMensajeValorAnunciado,"Center");
		dlgMensajeValorAnunciado.setSize(340, 120);
		dlgMensajeValorAnunciado.setLocation(600, 395);
		dlgMensajeValorAnunciado.setResizable(false);
		dlgMensajeValorAnunciado.setVisible(true);
		
	}
	
	public void actualizarTurno(int turno) // Metodo para actualizar el turno actual segun el numero de jugadores
	{
		if((numJugadores == 4) && ((turno - 1) < 4))
		{
			turnoJugador = turno;
			repaint();
		}
		else if((numJugadores == 4) && ((turno - 1) >= 4))
		{
			turnoJugador = 1;
			repaint();
		}
		else if((numJugadores == 3) && ((turno - 1) < 3))
		{
			turnoJugador = turno;
			repaint();
		}
		else if((numJugadores == 3) && ((turno - 1) >= 3))
		{
			turnoJugador = 1;
			repaint();
		}
		else if((numJugadores == 2) && ((turno - 1) < 2))
		{
			turnoJugador = turno;
			repaint();
		}
		else if((numJugadores == 2) && ((turno - 1) >= 2))
		{
			turnoJugador = 1;
			repaint();
		}
	}
	
	// Metodos para restarle vidas a los jugadores
	public void quitarVidasJugador1()
	{
		vidasJugador1--;
		repaint();
	}
	
	public void quitarVidasJugador2()
	{
		vidasJugador2--;
		repaint();
	}
	
	public void quitarVidasJugador3()
	{
		vidasJugador3--;
		repaint();
	}
	
	public void quitarVidasJugador4()
	{
		vidasJugador4--;
		repaint();
	}
	
	// Metodo para colocar la cantidad de vidas de los jugadores segun el numero de jugadores
	public void resetearVidas()
	{
		if(numJugadores == 4)
		{
			vidasJugador1 = 3;
			vidasJugador2 = 3;
			vidasJugador3 = 3;
			vidasJugador4 = 3;
			repaint();
		}
		else if(numJugadores == 3)
		{
			vidasJugador1 = 3;
			vidasJugador2 = 3;
			vidasJugador3 = 3;
			repaint();
		}
		else if(numJugadores == 2)
		{
			vidasJugador1 = 3;
			vidasJugador2 = 3;
			repaint();
		}

	}
	
	// Metodos para mostrar las dos imagenes de la animacion del cubilete
	public void cambiarCubilete(int voltear)
	{
		voltearCubilete = voltear;
		repaint();
	}
	public void recuperarCubilete(int recup)
	{
		recuperarCubilete = recup;
		repaint();
	}
	
	// Metodo para el sonido de dados que hace el cubilete
	public void sonidoDados()
	{
		try
		{
            Clip sonido = AudioSystem.getClip();
            sonido.open(AudioSystem.getAudioInputStream(new File("dados.wav")));
            sonido.start();
        } 
		catch (Exception e) 
		{
            System.out.println("" + e);
        }

	}
	public void MostrarJugando()
	{
		this.setVisible(true);
	}
	
	public void OcultarJugando()
	{
		this.setVisible(false);
	}
}
