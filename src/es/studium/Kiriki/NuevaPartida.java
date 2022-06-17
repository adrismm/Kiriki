package es.studium.Kiriki;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;

public class NuevaPartida
{
	private static final long serialVersionUID = 1L;

	//Declaramos elementos gráficos de la vista, diálogos y variables auxiliares
	Dialog pedirNumeroJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	Dialog pedirNombresJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	int NumerosJugadores = 0; //Guarda el número de jugadores
	String[] nombresJugadores = null; //Guarda los nombres de los jugadores
	Choice choNumeroJugadores = new Choice();
	Button btnContinuar = new Button("Continuar");
	
	TextField txfNombre1 = new TextField(15);
	Label lblEtiqueta1 = new Label("Jugador 1:");
	TextField txfNombre2 = new TextField(15);
	Label lblEtiqueta2 = new Label("Jugador 2:");
	TextField txfNombre3 = new TextField(15);
	Label lblEtiqueta3 = new Label("Jugador 3:");
	TextField txfNombre4 = new TextField(15);
	Label lblEtiqueta4 = new Label("Jugador 4:");
	Button btnComenzarPartida = new Button("Comenzar Partida");
	
	public NuevaPartida() //Constructor de la clase
	{
		//Preguntamos el número de jugadores
		pedirNumeroJugadores.setBackground(Color.YELLOW); //Color del fondo del Dialog
		pedirNumeroJugadores.setLayout(new FlowLayout()); //Layout del Dialog
		pedirNumeroJugadores.setSize(240,100); //Tamaño del Dialog
		pedirNumeroJugadores.setLocationRelativeTo(null); //Centrar el Dialog
		pedirNumeroJugadores.setResizable(false); //Evitar redimensionado
		
		choNumeroJugadores.add("Elegir número de jugadores..."); //Contenido desplegable
		choNumeroJugadores.add("2");
		choNumeroJugadores.add("3");
		choNumeroJugadores.add("4");
		pedirNumeroJugadores.add(choNumeroJugadores);
		pedirNumeroJugadores.add(btnContinuar);
	}
}
