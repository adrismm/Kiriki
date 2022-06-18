package es.studium.Kiriki;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextArea;

public class Ranking extends Frame
{
	private static final long serialVersionUID = 1L;
	
	TextArea txaRanking = new TextArea(20,10);
	Button btnVolver = new Button("Volver");
	Panel pnlJugadores = new Panel();
	Panel pnlPuntos = new Panel();
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
	
	
	public Ranking()
	{
		setTitle("Kiriki: Ranking"); //T�tulo
		setBackground(Color.YELLOW); //Color de fondo del Frame
		setLayout(new FlowLayout(FlowLayout.CENTER)); //Layout del Frame
		pnlJugadores.add(lblJugador1);
		pnlJugadores.add(lblJugador2);
		pnlJugadores.add(lblJugador3);
		pnlJugadores.add(lblJugador4);
		pnlJugadores.add(lblJugador5);
		pnlJugadores.add(lblJugador6);
		pnlJugadores.add(lblJugador7);
		pnlJugadores.add(lblJugador8);
		pnlJugadores.add(lblJugador9);
		pnlJugadores.add(lblJugador10);
		pnlPuntos.add(lblPuntos1);
		pnlPuntos.add(lblPuntos2);
		pnlPuntos.add(lblPuntos3);
		pnlPuntos.add(lblPuntos4);
		pnlPuntos.add(lblPuntos5);
		pnlPuntos.add(lblPuntos6);
		pnlPuntos.add(lblPuntos7);
		pnlPuntos.add(lblPuntos8);
		pnlPuntos.add(lblPuntos9);
		pnlPuntos.add(lblPuntos10);
		pnlJugadores.setBackground(Color.black);
		pnlJugadores.setPreferredSize(new Dimension(50,30));
		pnlPuntos.setBackground(Color.orange);
		pnlPuntos.setPreferredSize(new Dimension(50,30));
		setSize(400,200); //Tama�o del Frame
		setLocationRelativeTo(null); //Centrar la ventana
		setResizable(false); //Evitar redimensionado
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
