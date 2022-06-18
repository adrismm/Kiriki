package es.studium.Kiriki;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;

public class NuevaPartida extends Frame
{
	private static final long serialVersionUID = 1L;

	// Declaramos elementos gráficos de la vista, diálogos y variables auxiliares
	Dialog pedirNumeroJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	Dialog pedirNombresJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	int NumerosJugadores = 0; // Guarda el número de jugadores
	String[] nombresJugadores = null; // Guarda los nombres de los jugadores
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
	
	
	
	public NuevaPartida() // Constructor de la clase
	{
		// Preguntamos el número de jugadores
		pedirNumeroJugadores.setBackground(Color.YELLOW); // Color del fondo del Dialog
		pedirNumeroJugadores.setLayout(new FlowLayout()); // Layout del Dialog
		pedirNumeroJugadores.setSize(240,100); // Tamaño del Dialog
		pedirNumeroJugadores.setLocationRelativeTo(null); // Centrar el Dialog
		pedirNumeroJugadores.setResizable(false); // Evitar redimensionado
		
		choNumeroJugadores.add("Elegir nï¿½mero de jugadores..."); // Contenido desplegable
		choNumeroJugadores.add("2");
		choNumeroJugadores.add("3");
		choNumeroJugadores.add("4");
		pedirNumeroJugadores.add(choNumeroJugadores);
		pedirNumeroJugadores.add(btnContinuar);
	}
	
	public void MostrarDialogNumeroJugadores() //Método para mostrar diálogo que nos preguntará por el número de jugadores de la partida
	{
		pedirNumeroJugadores.setVisible(true);
	}
	
	public void OcultarDialogNumeroJugadores() //Método para ocultar el diálogo anterior
	{
		pedirNumeroJugadores.setVisible(false);
	}
	
	// Diálogos Nueva Partida
	public void PrepararDialogNombresJugadores(int numero) //Método que prepara el diálogo que nos pregunta por los nombres de los jugadores, cuya cantidad vendrá definida por lo que se haya elegido en el cuadro del diálogo despegable
	{
		pedirNombresJugadores.setBackground(Color.YELLOW); //Color del fondo del Dialog
		pedirNombresJugadores.setLayout(new FlowLayout()); //Layout del Dialog
		pedirNombresJugadores.setSize(240,200); //Tamaño del Dialog
		pedirNombresJugadores.setLocationRelativeTo(null); //Centrar el Dialog
		pedirNombresJugadores.setResizable(false); //Evitar redimensionado
		
		//Preguntamos los nombres
		//Jugador 1, siempre existe
		pedirNombresJugadores.add(lblEtiqueta1);
		txfNombre1.selectAll(); //Reseteamos los cuadros de texto
		txfNombre1.setText("");
		pedirNombresJugadores.add(txfNombre1);
		//Jugador 2, siempre existe
		pedirNombresJugadores.add(lblEtiqueta2);
		txfNombre2.selectAll(); //Reseteamos los cuadros de texto
		txfNombre2.setText("");
		pedirNombresJugadores.add(txfNombre2);
		
		//Si existe Jugador 3
		if(numero == 3)
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
		}
		else
		{
			pedirNombresJugadores.remove(lblEtiqueta3);
			txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.remove(txfNombre3);
		}
		if(numero == 4)
		{
			pedirNombresJugadores.add(lblEtiqueta3);
			txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre3);
			pedirNombresJugadores.add(lblEtiqueta4);
			txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.add(txfNombre4);
		}
		else
		{
			pedirNombresJugadores.remove(lblEtiqueta4);
			txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			txfNombre3.setText("");
			pedirNombresJugadores.remove(txfNombre4);
		}
		
		pedirNombresJugadores.add(btnComenzarPartida);
		this.MostrarDialogNombresJugadores(); //Llamamos al método que muestra el diálogo
	}
	
	public void MostrarDialogNombresJugadores() //Método que muestra el diálogo que pregunta los nombres de los jugadores
	{
		pedirNombresJugadores.setVisible(true);
	}
		
	public void OcultarDialogNombresJugadores() //Método que oculta el diálogo anterior
	{
		pedirNombresJugadores.setVisible(false);
	}
	
	public void MostrarNuevaPartida()
	{
		this.setVisible(true);
	}
	
	public void OcultarNuevaPartida()
	{
		this.setVisible(false);
	}

}
