package es.studium.Kiriki;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.TextArea;

public class Ranking extends Frame
{
	private static final long serialVersionUID = 1L;
	
	TextArea txaRanking = new TextArea(20,10);
	Button btnVolver = new Button("Volver");

	public Ranking()
	{
		setTitle("Kiriki: Ranking"); //Título
		setBackground(Color.YELLOW); //Color de fondo del Frame
		setLayout(new BorderLayout()); //Layout del Frame
		txaRanking.append("#\tNombre\tPuntos");
		add(txaRanking, "Center");
		add(btnVolver, "South");
		setSize(400,200); //Tamaño del Frame
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
