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
	
	Toolkit herramientas;
	Image tapete, cubileteBocaArriba, cubileteBocaAbajo;
	Image D1, D2, D3, D4, D5, D6; // Im�genes de las caras de los dados
	
	int dadosTapados = 0;
	int cargarDados = 0;
	
	String jugador1, jugador2, jugador3, jugador4;
	int numJugadores = 0;
	int turnoJugador = 1;
	int tirada = 0;
	
	int imagenAmostrar1 = 0;
	int imagenAmostrar2 = 0;
	int voltearCubilete = 0;
	int recuperarCubilete = 0;
	
	int vidasJugador1 = 0;
	int vidasJugador2 = 0;
	int vidasJugador3 = 0;
	int vidasJugador4 = 0;
	
	Color myColor = new Color(60, 179, 113);
	
	Dialog dlgMensajeComienzoPartida = new Dialog(this, "Comienzo Partida", true);
	Label lblMensajeComienzoPartida = new Label();
	Button btnMensajeComienzoPartida = new Button("¡Dale!");
	
	Dialog dlgMensajeTurno = new Dialog(this, "Turno Actual", true);
	Label lblMensajeTurno = new Label();
	Button btnMensajeTurno = new Button("Vale");
	
	Dialog dlgMensajeValorTirada = new Dialog(this, "Resultado Tirada");
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
	
	Dialog dlgMensajeValorAnunciado = new Dialog(this, "Resultado Anunciado");
	Label lblMensajeValorAnunciado = new Label(); // + jugadorActual + valorAnunciado
	Button btnMensajeValorAnunciado = new Button("¡Vale!");
	
	Dialog dlgMensajeValorRecibido = new Dialog(this, "Resultado Recibido");
	Label lblMensajeValorRecibido = new Label();
	Button btnAceptarValor = new Button("Superar Tirada");
	Button btnRechazarValor = new Button("Destapar Dados");
	
	Dialog dlgMensajeValorAceptado = new Dialog(this, "Resultado Aceptado");
	Label lblMensajeValorAceptado = new Label();
	Button btnMensajeValorAceptado = new Button("Vale");
	
	Dialog dlgMensajeValorRechazado = new Dialog(this, "Resultado Rechazado");
	Label lblMensajeValorRechazado = new Label(); // jugadorActual + jugadorAnterior + valorAnunciado
	Button btnMensajeValorRechazado = new Button("Vale");
	
	Dialog dlgMensajeValorVerdadero = new Dialog(this, "Resultado Verdadero");
	Label lblMensajeValorVerdadero = new Label("El valor anunciado es verdad, " + " ha perdido 1 vida."); // + jugadorActual
	Button btnMensajeValorVerdadero = new Button("Chúpate esa");
	
	Dialog dlgMensajeValorFalso = new Dialog(this, "Resultado Falso");
	Label lblMensajeValorFalso = new Label("El valor anunciado es mentira, " + " ha perdido 1 vida."); // + jugadorAnterior
	Button btnMensajeValorFalso = new Button("En la frente");
	
	Dialog dlgMensajeKiriki = new Dialog(this, "¡Kiriki!");
	Label lblMensajeKiriki = new Label("¡Toma ya! Vaya kirikazo, " + " ha perdido 1 vida."); // + jugadorSiguiente
	Button btnMensajeKiriki = new Button("¡Toma!");
	
	Dialog dlgMensajeFinPartida = new Dialog(this, "Fin");
	Label lblMensajeFinPartida = new Label("Ha ganado: "); // + jugadorGanador
	Button btnMensajeFinPartida = new Button("¡Se acabó!");
	
	Font fuenteTirada = new Font("Harlow Solid Italic", Font.BOLD, 20);
	Font fuenteTurno = new Font("Harlow Solid Italic", Font.BOLD, 20);
	Font fuenteJugadores = new Font("Harlow Solid Italic", Font.PLAIN, 18);
	
	// Constructor
	public Jugando(int n, String j1, String j2, String j3, String j4)
	{
		numJugadores = n;
		jugador1 = j1;
		jugador2 = j2;
		jugador3 = j3;
		jugador4 = j4;
		
		herramientas = getToolkit();
		tapete = herramientas.getImage("tapete612x408.jpg");
		cubileteBocaArriba = herramientas.getImage("cubilete.png");
		cubileteBocaAbajo = herramientas.getImage("cubilete2.png");

		D1 = herramientas.getImage("dadoNegro.png");
		D2 = herramientas.getImage("dadoRojo.png");
		D3 = herramientas.getImage("dadoJack.png");
		D4 = herramientas.getImage("dadoQueen.png");
		D5 = herramientas.getImage("dadoKing.png");
		D6 = herramientas.getImage("dadoAce.png");
		
		setTitle("Kiriki: Jugando"); // T�tulo
		setSize(620, 446); // Tama�o del Frame
		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
		
		dlgMensajeComienzoPartida.setBackground(myColor);
		dlgMensajeComienzoPartida.setLayout(new FlowLayout());
		dlgMensajeComienzoPartida.setSize(220, 100);
		dlgMensajeComienzoPartida.setLocationRelativeTo(null);
		dlgMensajeComienzoPartida.setResizable(false);
		dlgMensajeComienzoPartida.add(lblMensajeComienzoPartida);
		dlgMensajeComienzoPartida.add(btnMensajeComienzoPartida);
		
		dlgMensajeTurno.setBackground(myColor);
		dlgMensajeTurno.setLayout(new FlowLayout());
		dlgMensajeTurno.setSize(300, 80);
		dlgMensajeTurno.setLocationRelativeTo(null);
		dlgMensajeTurno.setResizable(false);
		dlgMensajeTurno.add(lblMensajeTurno);
		dlgMensajeTurno.add(btnMensajeTurno);
		
		dlgMensajeValorTirada.setBackground(myColor);
		dlgMensajeValorTirada.setLayout(new GridLayout(2, 1));
		dlgMensajeValorTirada.setSize(300, 320);
		dlgMensajeValorTirada.setLocation(570, 395);
		dlgMensajeValorTirada.setResizable(false);
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
		//dlgMensajeValorTirada.add(btnAnunciarValor);
		
		dlgMensajeValorRecibido.setBackground(myColor);
		dlgMensajeValorRecibido.setLayout(new FlowLayout());
		dlgMensajeValorRecibido.setSize(450, 100);
		dlgMensajeValorRecibido.setLocationRelativeTo(null);
		dlgMensajeValorRecibido.setResizable(false);
		dlgMensajeValorRecibido.add(lblMensajeValorRecibido);
		dlgMensajeValorRecibido.add(btnAceptarValor);
		dlgMensajeValorRecibido.add(btnRechazarValor);
		
		dlgMensajeValorAceptado.setBackground(myColor);
		dlgMensajeValorAceptado.setLayout(new FlowLayout());
		dlgMensajeValorAceptado.setSize(400, 100);
		dlgMensajeValorAceptado.setLocationRelativeTo(null);
		dlgMensajeValorAceptado.setResizable(false);
		dlgMensajeValorAceptado.add(lblMensajeValorAceptado);
		dlgMensajeValorAceptado.add(btnMensajeValorAceptado);
		
		dlgMensajeValorRechazado.setBackground(myColor);
		dlgMensajeValorRechazado.setLayout(new FlowLayout());
		dlgMensajeValorRechazado.setSize(400, 100);
		dlgMensajeValorRechazado.setLocationRelativeTo(null);
		dlgMensajeValorRechazado.setResizable(false);
		dlgMensajeValorRechazado.add(lblMensajeValorRechazado);
		dlgMensajeValorRechazado.add(btnMensajeValorRechazado);
		
		dlgMensajeValorVerdadero.setBackground(myColor);
		dlgMensajeValorVerdadero.setLayout(new FlowLayout());
		dlgMensajeValorVerdadero.setSize(400, 100);
		dlgMensajeValorVerdadero.setLocationRelativeTo(null);
		dlgMensajeValorVerdadero.setResizable(false);
		dlgMensajeValorVerdadero.add(lblMensajeValorVerdadero);
		dlgMensajeValorVerdadero.add(btnMensajeValorVerdadero);
		
		dlgMensajeValorFalso.setBackground(myColor);
		dlgMensajeValorFalso.setLayout(new FlowLayout());
		dlgMensajeValorFalso.setSize(400, 100);
		dlgMensajeValorFalso.setLocationRelativeTo(null);
		dlgMensajeValorFalso.setResizable(false);
		dlgMensajeValorFalso.add(lblMensajeValorFalso);
		dlgMensajeValorFalso.add(btnMensajeValorFalso);
		
		dlgMensajeKiriki.setBackground(myColor);
		dlgMensajeKiriki.setLayout(new FlowLayout());
		dlgMensajeKiriki.setSize(100, 100);
		dlgMensajeKiriki.setLocationRelativeTo(null);
		dlgMensajeKiriki.setResizable(false);
		dlgMensajeKiriki.add(lblMensajeKiriki);
		dlgMensajeKiriki.add(btnMensajeKiriki);
		
		dlgMensajeFinPartida.setBackground(myColor);
		dlgMensajeFinPartida.setLayout(new FlowLayout());
		dlgMensajeFinPartida.setSize(100, 100);
		dlgMensajeFinPartida.setLocationRelativeTo(null);
		dlgMensajeFinPartida.setResizable(false);
		dlgMensajeFinPartida.add(lblMensajeFinPartida);
		dlgMensajeFinPartida.add(btnMensajeFinPartida);
	}
	
	// Dibujamos
	public void paint(Graphics g)
	{
		g.drawImage(tapete,  0,  30,  this);
		g.setFont(fuenteTurno);
		
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
		
		// Turno
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
		
		// Jugadores
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
	
	public void cargarDados() // Ya en el constructor
	{
		D1 = herramientas.getImage("dadoNegro.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D2 = herramientas.getImage("dadoRojo.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D3 = herramientas.getImage("dadoJack.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D4 = herramientas.getImage("dadoQueen.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D5 = herramientas.getImage("dadoKing.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
		D6 = herramientas.getImage("dadoAce.png").getScaledInstance(50, 50, Image.SCALE_AREA_AVERAGING);
	}
	
	public void mostrarDadoCubiletes(int dado1, int dado2)
	{
		imagenAmostrar1 = dado1;
		imagenAmostrar2 = dado2;
		repaint();
	}
	
	public void mostrarValorAnunciado(String valorCheckbox)
	{
		dlgMensajeValorAnunciado.setBackground(myColor);
		dlgMensajeValorAnunciado.setLayout(new FlowLayout());
		dlgMensajeValorAnunciado.setSize(300, 110);
		dlgMensajeValorAnunciado.setLocation(600, 395);
		dlgMensajeValorAnunciado.setResizable(false);
		
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
		
		dlgMensajeValorAnunciado.add(lblMensajeValorAnunciado);
		dlgMensajeValorAnunciado.add(lblMensajeTurno);
		dlgMensajeValorAnunciado.setVisible(true);
	}
	
	public void actualizarTurno(int turno)
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
	
	public void resetearContadores()
	{
		vidasJugador1 = 10;
		vidasJugador2 = 10;
		vidasJugador3 = 10;
		vidasJugador4 = 10;
		repaint();
	}
	
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
	public void sonidoDados()
	{
		//sonido
		try
		{
			// Se obtiene un Clip de sonido
            Clip sonido = AudioSystem.getClip();
            
            // Se carga con un fichero wav
            sonido.open(AudioSystem.getAudioInputStream(new File("dados.wav")));
            
            // Comienza la reproducción
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
	public void setVisible(boolean b)
	{
		//Jugando.setVisible(b);
	}
}
