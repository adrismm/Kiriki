package es.studium.Kiriki;

import java.awt.Color;
import java.awt.Dialog;
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
	Image tapete;
	int numJugadores;
	String jugador1, jugador2, jugador3, jugador4;
	int turnoJugador = 1;
	int tirada = 0;
	Font fuenteTirada = new Font("Jokerman", Font.BOLD, 24);
	Font fuenteTurno = new Font("Jokerman", Font.BOLD, 24);
	Font fuenteJugadores = new Font("Jokerman", Font.BOLD, 22);
	
	Frame ventanaJuego = new Frame("Kiriki: Jugando");
	Dialog dialogoTurno = new Dialog(this, "Turno", true);
	Dialog dialogoAnunciarValor = new Dialog(this, "El valor de la tirada es...");
	Dialog dialogoRecibirValor = new Dialog(this, "¿Desatapas el cubilete o superas la tirada?");
	Dialog dialogoVictoria = new Dialog(this, "¡Has ganado!");
	Dialog dialogoComenzar = new Dialog(this, "¡La partida ha comenzado!");
	Label lblTurno = new Label();
	Label lblComienzo = new Label();
	Label lblVictoria = new Label();
	Label lblMentira = new Label();
	Label lblVerdad = new Label();
	
	
	int xAmarilla = 100, yAmarilla = 380;
	int xAzul=95, yAzul=380;
	int xVerde=90, yVerde=380;
	int xRoja=85, yRoja=380;
	
	public Jugando(int n, String j1, String j2, String j3, String j4)
	{
		numJugadores = n;
		jugador1 = j1;
		jugador2 = j2;
		jugador3 = j3;
		jugador4 = j4;
		herramientas = getToolkit();
		tapete = herramientas.getImage("tapete612x408.jpg");
		setTitle("Jugando a Kiriki"); // Título
		setSize(600,420); // Tamaño del Frame
		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
	}
	
	public void paint(Graphics g)
	{
		g.drawImage(tapete,  0,  30,  this);
		g.setFont(fuenteTirada);
		g.setColor(Color.black);
		
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
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador1, 10, 90);
				break;
			case 2:
				g.setColor(Color.blue);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador2, 10, 90);
				break;
			case 3:
				g.setColor(Color.green);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador3, 10, 90);
				break;
			case 4:
				g.setColor(Color.red);
				g.drawString("Turno de", 10, 60);
				g.drawString(jugador4, 10, 90);
				break;
		}
		
		g.setFont(fuenteJugadores);
		
		// Jugadores
		g.setColor(Color.yellow);
		g.drawString(jugador1, 10, 320);
		g.fillOval(xAmarilla, yAmarilla, 10, 320); // Ficha Amarilla
		
		g.setColor(Color.blue);
		g.drawString(jugador2,  10, 350);
		g.fillOval(xAzul, yAzul, 20, 20); // Ficha Azul
		
		switch(numJugadores)
		{
			case 3:
				g.setColor(Color.green);
				g.drawString(jugador3, 10, 380);
				g.fillOval(xVerde, yVerde, 20, 20); // Ficha Verde
				break;
			case 4:
				g.setColor(Color.green);
				g.drawString(jugador3, 10, 380);
				g.fillOval(xVerde, yVerde, 20, 20);
				g.setColor(Color.red);
				g.drawString(jugador4, 10, 410);
				g.fillOval(xRoja, yRoja, 20, 20); // Ficha Roja
				break;
		}
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
