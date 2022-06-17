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
	
	Label lblMenuPrincipal = new Label ("MENÚ PRINCIPAL", 1); //El 1 para que salga centrado
	Panel pnlBotonera = new Panel(); //Panel para botonera principal
	Panel pnlBotoneraSalir = new Panel(); //Panel para botón Salir
	Button btnNuevaPartida = new Button ("Nueva Partida");
	Button btnRanking = new Button ("Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	
	public MenuInicio()
	{
		setTitle("Kiriki"); //Título
		setBackground(Color.YELLOW); //Color del fondo del Frame
		setLayout(new BorderLayout()); //Layout del Frame
		pnlBotonera.setLayout(new GridLayout(4,1)); //Layout del Panel
		pnlBotonera.add(lblMenuPrincipal); //Añadir Bot�n a Panel
		pnlBotonera.add(btnNuevaPartida); //A�adir Bot�n a Panel 	
		pnlBotonera.add(btnRanking); //A�adir Bot�n a Panel
		pnlBotonera.add(btnAyuda); //A�adir Bot�n a Panel
		add(pnlBotonera, "Center"); //A�adir Panel a Frame
		pnlBotoneraSalir.add(btnSalir); //Añadir Botón a Panel
		add(pnlBotoneraSalir, "South"); //Añadir Panel a Frame
		setSize(400,200); //Tamaño de Frame
		setLocationRelativeTo(null); //Centrar la ventana
		setResizable(false); //Evitar redimensionado
		MostrarPrincipal(); //Mostrarlo
	}
	
	public void MostrarPrincipal()
	{
		this.setVisible(true);
	}
	
	public void OcultarPrincipal()
	{
		this.setVisible(false);
	}
}
