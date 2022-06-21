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

	// Declaramos elementos gr�ficos de la vista, di�logos y variables auxiliares
	Dialog pedirNumeroJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	Dialog pedirNombresJugadores = new Dialog(this, "Kiriki: Nueva Partida", true);
	int numJugadores = 0; // Guarda el n�mero de jugadores
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
	
	Dialog dlgMensajeFaltanNombres = new Dialog(this, "ERROR: Faltan datos", true);
	Label lblFaltanNombres = new Label("Por favor, introduzca el nombre de todos los jugadores.");
	
	public NuevaPartida() // Constructor de la clase
	{
		// Preguntamos el n�mero de jugadores
		Color myColor = new Color(60, 179, 113);
		pedirNumeroJugadores.setBackground(myColor); // Color del fondo del Dialog
		pedirNumeroJugadores.setLayout(new FlowLayout()); // Layout del Dialog
		pedirNumeroJugadores.setSize(240,100); // Tama�o del Dialog
		pedirNumeroJugadores.setLocationRelativeTo(null); // Centrar el Dialog
		pedirNumeroJugadores.setResizable(false); // Evitar redimensionado
		
		choNumeroJugadores.add("Elegir número de jugadores..."); // Contenido desplegable
		choNumeroJugadores.add("2");
		choNumeroJugadores.add("3");
		choNumeroJugadores.add("4");
		pedirNumeroJugadores.add(choNumeroJugadores);
		pedirNumeroJugadores.add(btnContinuar);
	}
	
	public void MostrarDialogNumeroJugadores() //M�todo para mostrar di�logo que nos preguntar� por el n�mero de jugadores de la partida
	{
		pedirNumeroJugadores.setVisible(true);
	}
	
	public void OcultarDialogNumeroJugadores() //M�todo para ocultar el di�logo anterior
	{
		pedirNumeroJugadores.setVisible(false);
	}
	
	// Di�logos Nueva Partida
	public void PrepararDialogNombresJugadores(int numero) //M�todos di�logos para preparar una nueva partida
	{
		Color myColor = new Color(60, 179, 113);
		pedirNombresJugadores.setBackground(myColor); //Color del fondo del Dialog
		pedirNombresJugadores.setLayout(new FlowLayout()); //Layout del Dialog
		pedirNombresJugadores.setSize(300,200); //Tama�o del Dialog
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
		
		numJugadores = numero;
		pedirNombresJugadores.add(btnComenzarPartida);
		this.MostrarDialogNombresJugadores(); //Llamamos al m�todo que muestra el di�logo
	}
	
	public void MostrarDialogNombresJugadores() //M�todo que muestra el di�logo que pregunta los nombres de los jugadores
	{
		pedirNombresJugadores.setVisible(true);
	}
		
	public void OcultarDialogNombresJugadores() //M�todo que oculta el di�logo anterior
	{
		pedirNombresJugadores.setVisible(false);
	}
	
	public void MensajeErrorFaltanNombres()
	{
		Color myColor4 = new Color(60, 179, 113);
		dlgMensajeFaltanNombres.setBackground(myColor4); //Color del fondo del Dialog
		dlgMensajeFaltanNombres.setLayout(new FlowLayout()); //Layout del Dialog
		dlgMensajeFaltanNombres.setSize(380,75); //Tama�o del Dialog
		dlgMensajeFaltanNombres.setLocationRelativeTo(null); //Centrar el Dialog
		dlgMensajeFaltanNombres.setResizable(false); //Evitar redimensionado
		
		dlgMensajeFaltanNombres.add(lblFaltanNombres);
		
		this.MostrarMensajeError();
	}
	
	public void MostrarMensajeError()
	{
		dlgMensajeFaltanNombres.setVisible(true);
	}

	public void OcultarMensajeError()
	{
		dlgMensajeFaltanNombres.setVisible(false);
	}
}
