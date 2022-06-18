package es.studium.Kiriki;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;

public class MenuInicio extends Frame
{
	private static final long serialVersionUID = 1L;
	
	// Ventana men� principal
	Label lblMenuPrincipal = new Label ("MENÚ PRINCIPAL", 1); //El 1 para que salga centrado
	Panel pnlBotonera = new Panel(); //Panel para botonera principal
	Panel pnlBotoneraSalir = new Panel(); //Panel para bot�n Salir
	Button btnNuevaPartida = new Button ("Nueva Partida");
	Button btnRanking = new Button ("Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	
	public MenuInicio()
	{
		setTitle("Kiriki"); //T�tulo
		setBackground(Color.yellow);
		setLayout(new BorderLayout()); //Layout del Frame
		pnlBotonera.setLayout(new GridLayout(4,1)); //Layout del Panel
		pnlBotonera.add(lblMenuPrincipal); //A�adir Bot�n a Panel
		pnlBotonera.add(btnNuevaPartida); //A�adir Bot�n a Panel 	
		pnlBotonera.add(btnRanking); //A�adir Bot�n a Panel
		pnlBotonera.add(btnAyuda); //A�adir Bot�n a Panel
		add(pnlBotonera, "Center"); //A�adir Panel a Frame
		pnlBotoneraSalir.add(btnSalir); //A�adir Bot�n a Panel
		add(pnlBotoneraSalir, "South"); //A�adir Panel a Frame
		setSize(400,200); //Tama�o de Frame
		setLocationRelativeTo(null); //Centrar la ventana
		setResizable(false); //Evitar redimensionado
		MostrarInicio(); //Mostrarlo
	}
	
	public void MostrarInicio()
	{
		this.setVisible(true);
	}
	
	public void OcultarInicio()
	{
		this.setVisible(false);
	}
}
