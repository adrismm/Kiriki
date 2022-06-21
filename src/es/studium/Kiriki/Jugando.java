package es.studium.Kiriki;


import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;



public class Jugando extends Frame
{
	private static final long serialVersionUID = 1L;
	
	Toolkit herramientas;
	Image tapete, cubilete, cubileteBocaAbajo;
	Image D1, D2, D3, D4, D5, D6; // Im�genes de las caras de los dados
	
	int dadosTapados = 0;
	int cargarDados = 0;
	
	String jugador1, jugador2, jugador3, jugador4;
	int numJugadores = 0;
	int turnoJugador = 1;
	int tirada = 0;
	
	int imagenAmostrar1 = 0;
	int imagenAmostrar2 = 0;
	int vidasJugador1 = 0;
	int vidasJugador2 = 0;
	int vidasJugador3 = 0;
	int vidasJugador4 = 0;
	int turno = 0;
	
	
	
	Dialog dlgTurno = new Dialog(this, "Turno", true);
	Label lblTurno = new Label();
	
	Dialog dlgMensajeComienzoPartida = new Dialog(this, "¡La partida ha comenzado!", true);
	Label lblMensajeComienzoPartida = new Label();
	
	Dialog dlgMensajeValorTirada = new Dialog(this, "Resultado Tirada");
	Label lblMensajeValorTirada = new Label("El valor de la tirada es: "); // + valorTirada
	
	Dialog dlgMensajeValorAnunciado = new Dialog(this, "Resultado Anunciado");
	Label lblMensajeValorAnunciado = new Label("El valor anunciado por " + " es: "); // + jugadorActual + valorAnunciado
	
	Dialog dlgMensajeValorRecibido = new Dialog(this, "Resultado Recibido");
	Label lblMensajeValorRecibido = new Label("�Destapas el cubilete o superas el valor recibido?"); // + btnAceptarValor + btnRechazarValor
	
	Dialog dlgMensajeValorAceptado = new Dialog(this, "Resultado Aceptado");
	Label lblMensajeValorAceptado = new Label(" ha aceptado el valor anunciado por " + " de: "); // jugadorActual + jugadorAnterior + valorAnunciado
	
	Dialog dlgMensajeValorRechazado = new Dialog(this, "Resultado Rechazado");
	Label lblMensajeValorRechazado = new Label(" ha rechazado el valor anunciado por " + " de: "); // jugadorActual + jugadorAnterior + valorAnunciado
	
	Dialog dlgMensajeValorVerdadero = new Dialog(this, "Resultado Verdadero");
	Label lblMensajeValorVerdadero = new Label("El valor anunciado es verdad, " + " ha perdido 1 vida."); // + jugadorActual
	
	Dialog dlgMensajeValorFalso = new Dialog(this, "Resultado Falso");
	Label lblMensajeValorFalso = new Label("El valor anunciado es mentira, " + " ha perdido 1 vida."); // + jugadorAnterior
	
	Dialog dlgMensajeKiriki = new Dialog(this, "�Kiriki!");
	Label lblMensajeKiriki = new Label("�Toma ya! Vaya kirikazo, " + " ha perdido 1 vida."); // + jugadorSiguiente
	
	Dialog dlgMensajeFinPartida = new Dialog(this, "Fin");
	Label lblMensajeFinPartida = new Label("Ha ganado: "); // + jugadorGanador
	
	Font fuenteTirada = new Font("Harlow Solid Italic", Font.BOLD, 20);
	Font fuenteTurno = new Font("Harlow Solid Italic", Font.BOLD, 20);
	Font fuenteJugadores = new Font("Harlow Solid Italic", Font.PLAIN, 18);
	
	int xJugador1 = 100, yJugador1 = 380;
	int xJugador2 = 95, yJugador2 = 380;
	int xJugador3 = 90, yJugador3 = 380;
	int xJugador4 = 85, yJugador4 = 380;
	
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
		cubilete = herramientas.getImage("cubilete.png");
		cubileteBocaAbajo = herramientas.getImage("cubilete2.png");
		
		setTitle("Jugando a Kiriki"); // T�tulo
		setSize(620,446); // Tama�o del Frame
		D1 = herramientas.getImage("dadoNegro.png");
		D2 = herramientas.getImage("dadoRojo.png");
		D3 = herramientas.getImage("dadoJack.png");
		D4 = herramientas.getImage("dadoQueen.png");
		D5 = herramientas.getImage("dadoKing.png");
		D6 = herramientas.getImage("dadoAce.png");
		
		setTitle("Jugando a Kiriki"); // T�tulo
		setSize(620, 446); // Tama�o del Frame

		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
		
		dlgMensajeComienzoPartida.setLayout(new FlowLayout());
		dlgMensajeComienzoPartida.setSize(100, 100);
		dlgMensajeComienzoPartida.setLocationRelativeTo(null);
		dlgMensajeComienzoPartida.setResizable(false);
		dlgMensajeComienzoPartida.add(lblMensajeComienzoPartida);
		
		dlgMensajeComienzoPartida.setLayout(new FlowLayout()); // Di�logo turno
		dlgMensajeComienzoPartida.setSize(200, 100);
		dlgMensajeComienzoPartida.setLocationRelativeTo(null);
		dlgMensajeComienzoPartida.setResizable(false);
		dlgMensajeComienzoPartida.add(lblTurno);
		
		dlgMensajeValorTirada.setLayout(new FlowLayout());
		dlgMensajeValorTirada.setSize(100, 100);
		dlgMensajeValorTirada.setLocationRelativeTo(null);
		dlgMensajeValorTirada.setResizable(false);
		dlgMensajeValorTirada.add(lblMensajeValorTirada);
		
		dlgMensajeValorAnunciado.setLayout(new FlowLayout());
		dlgMensajeValorAnunciado.setSize(100, 100);
		dlgMensajeValorAnunciado.setLocationRelativeTo(null);
		dlgMensajeValorAnunciado.setResizable(false);
		dlgMensajeValorAnunciado.add(lblMensajeValorAnunciado);
		
		dlgMensajeValorRecibido.setLayout(new FlowLayout());
		dlgMensajeValorRecibido.setSize(100, 100);
		dlgMensajeValorRecibido.setLocationRelativeTo(null);
		dlgMensajeValorRecibido.setResizable(false);
		dlgMensajeValorRecibido.add(lblMensajeValorRecibido);
		
		dlgMensajeValorAceptado.setLayout(new FlowLayout());
		dlgMensajeValorAceptado.setSize(100, 100);
		dlgMensajeValorAceptado.setLocationRelativeTo(null);
		dlgMensajeValorAceptado.setResizable(false);
		dlgMensajeValorAceptado.add(lblMensajeValorAceptado);
		
		dlgMensajeValorRechazado.setLayout(new FlowLayout());
		dlgMensajeValorRechazado.setSize(100, 100);
		dlgMensajeValorRechazado.setLocationRelativeTo(null);
		dlgMensajeValorRechazado.setResizable(false);
		dlgMensajeValorRechazado.add(lblMensajeValorRechazado);
		
		dlgMensajeValorVerdadero.setLayout(new FlowLayout());
		dlgMensajeValorVerdadero.setSize(100, 100);
		dlgMensajeValorVerdadero.setLocationRelativeTo(null);
		dlgMensajeValorVerdadero.setResizable(false);
		dlgMensajeValorVerdadero.add(lblMensajeValorVerdadero);
		
		dlgMensajeValorFalso.setLayout(new FlowLayout());
		dlgMensajeValorFalso.setSize(100, 100);
		dlgMensajeValorFalso.setLocationRelativeTo(null);
		dlgMensajeValorFalso.setResizable(false);
		dlgMensajeValorFalso.add(lblMensajeValorFalso);
		
		dlgMensajeKiriki.setLayout(new FlowLayout());
		dlgMensajeKiriki.setSize(100, 100);
		dlgMensajeKiriki.setLocationRelativeTo(null);
		dlgMensajeKiriki.setResizable(false);
		dlgMensajeKiriki.add(lblMensajeKiriki);
		
		dlgMensajeFinPartida.setLayout(new FlowLayout());
		dlgMensajeFinPartida.setSize(100, 100);
		dlgMensajeFinPartida.setLocationRelativeTo(null);
		dlgMensajeFinPartida.setResizable(false);
		dlgMensajeFinPartida.add(lblMensajeFinPartida);
	}
	
	// Dibujamos
	public void paint(Graphics g)
	{
		g.drawImage(tapete,  0,  30,  this);
		g.drawImage(cubilete, 270, 180,  this);
		g.drawImage(cubileteBocaAbajo, 270, 180,  this);
		g.setFont(fuenteTurno);
		
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
		
		if(tirada != 0)
		{
			g.drawString(tirada + "", 53, 255);
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
		D1 = herramientas.getImage("dadoNegro.png");
		D2 = herramientas.getImage("dadoRojo.png");
		D3 = herramientas.getImage("dadoJack.png");
		D4 = herramientas.getImage("dadoQueen.png");
		D5 = herramientas.getImage("dadoKing.png");
		D6 = herramientas.getImage("dadoAce.png");
	}
	
	public void mostrarDadoCubilete1(int dado)
	{
		imagenAmostrar1 = dado;
		repaint();
	}
	
	public void mostrarDadoDubilete2(int dado)
	{
		imagenAmostrar2 = dado;
		repaint();
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
	
	public void actualizarTurno(int t)
	{
		turnoJugador = t;
		repaint();
	}
	
	public void mostrarTirada(int t)
	{
		tirada = t;
		repaint();
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
