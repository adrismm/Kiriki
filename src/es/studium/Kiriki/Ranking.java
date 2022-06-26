package es.studium.Kiriki;


import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class Ranking extends Frame
{
	private static final long serialVersionUID = 1L;
	
	Button btnVolver = new Button("Volver");
	Panel pnlJugadores = new Panel();
	Panel lblCabecera = new Panel();
	Label lblJugador = new Label("Jugadores");
	Label lblPuntos = new Label("Puntos");
	Label lblJugador1 = new Label();
	Label lblJugador2 = new Label();
	Label lblJugador3 = new Label();
	Label lblJugador4 = new Label();
	Label lblJugador5 = new Label();
	Label lblJugador6 = new Label();
	Label lblJugador7 = new Label();
	Label lblJugador8 = new Label();
	Label lblJugador9 = new Label();
	Label lblJugador10 = new Label();
	Label lblPuntos1 = new Label();
	Label lblPuntos2 = new Label();
	Label lblPuntos3 = new Label();
	Label lblPuntos4 = new Label();
	Label lblPuntos5 = new Label();
	Label lblPuntos6 = new Label();
	Label lblPuntos7 = new Label();
	Label lblPuntos8 = new Label();
	Label lblPuntos9 = new Label();
	Label lblPuntos10 = new Label();
	
	Label lblMejoresJugadores = new Label("Kiriki: Mejores Jugadores");
//	TextArea listadoJugadores = new TextArea(15, 40);
//	Button btnVolver = new Button("Volver");
	
	public Ranking()
	{
		setTitle("Kiriki: Ranking"); // Titulo
		setBackground(Color.YELLOW); // Color de fondo del Frame
		setLayout(new FlowLayout()); // Layout del Frame
		add(lblMejoresJugadores);
//		add(listadoJugadores);
//		listadoJugadores.append("JUGADOR\tPUNTOS\n");
		add(btnVolver);
		
		add(lblCabecera);
		add(pnlJugadores);
		lblCabecera.add(lblJugador);
		lblCabecera.add(lblPuntos);
		pnlJugadores.add(lblJugador1);
		pnlJugadores.add(lblPuntos1);
		pnlJugadores.add(lblJugador2);
		pnlJugadores.add(lblPuntos2);
		pnlJugadores.add(lblJugador3);
		pnlJugadores.add(lblPuntos3);
		pnlJugadores.add(lblJugador4);
		pnlJugadores.add(lblPuntos4);
		pnlJugadores.add(lblJugador5);
		pnlJugadores.add(lblPuntos5);
		pnlJugadores.add(lblJugador6);
		pnlJugadores.add(lblPuntos6);
		pnlJugadores.add(lblJugador7);
		pnlJugadores.add(lblPuntos7);
		pnlJugadores.add(lblJugador8);
		pnlJugadores.add(lblPuntos8);
		pnlJugadores.add(lblJugador9);
		pnlJugadores.add(lblPuntos9);
		pnlJugadores.add(lblJugador10);
		pnlJugadores.add(lblPuntos10);
		
		lblCabecera.setLayout(new GridLayout(1,2));
		lblCabecera.setBackground(Color.WHITE);
		lblCabecera.setPreferredSize(new Dimension(340,30));
		
		pnlJugadores.setLayout(new GridLayout(10,2));
		pnlJugadores.setBackground(Color.GREEN);
		pnlJugadores.setPreferredSize(new Dimension(340,220));
		
		add(btnVolver);
		
		setSize(400,360); // Tamanio del Frame
		setLocationRelativeTo(null); // Centrar la ventana
		setResizable(false); // Evitar redimensionado
	}
	
	public void MostrarRanking()
	{
		this.setVisible(true);
	}
	
	public void OcultarRanking()
	{
		this.setVisible(false);
	}

}
