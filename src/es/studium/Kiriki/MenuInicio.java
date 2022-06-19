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
	Label lblMenuPrincipal = new Label ("MEN� PRINCIPAL", 1); //El 1 para que salga centrado
	Panel pnlBotonera = new Panel(); //Panel para botonera principal
	Panel pnlBotoneraSalir = new Panel(); //Panel para bot�n Salir
	Button btnNuevaPartida = new Button ("Nueva Partida");
	Button btnRanking = new Button ("Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	
	Panel pnlEspacioIzquierdo = new Panel();
	Panel pnlEspacioDerecho = new Panel();
	Label lblEspacioIzquierdo = new Label("\t\t\t\t");
	Label lblEspacioDerecho = new Label("\t\t\t\t");
	
	public MenuInicio()
	{
		this.setTitle("Kiriki"); //T�tulo
		this.setBackground(Color.YELLOW); //Color del fondo del Frame
		this.setLayout(new BorderLayout()); //Layout del Frame
		this.
		
		pnlBotonera.setLayout(new GridLayout(4,1)); //Layout del Panel
		pnlBotonera.add(lblMenuPrincipal); //A�adir Bot�n a Panel
		pnlBotonera.add(btnNuevaPartida); //A�adir Bot�n a Panel 	
		pnlBotonera.add(btnRanking); //A�adir Bot�n a Panel
		pnlBotonera.add(btnAyuda); //A�adir Bot�n a Panel
		this.add(pnlBotonera, "Center"); //A�adir Panel a Frame
		pnlBotoneraSalir.add(btnSalir); //A�adir Bot�n a Panel
		this.add(pnlBotoneraSalir, "South"); //A�adir Panel a Frame
		pnlEspacioIzquierdo.add(lblEspacioIzquierdo);
		this.add(pnlEspacioIzquierdo, "West");
		pnlEspacioDerecho.add(lblEspacioDerecho);
		this.add(pnlEspacioDerecho, "East");
		
		this.setSize(400,200); //Tama�o de Frame
		this.setLocationRelativeTo(null); //Centrar la ventana
		this.setResizable(false); //Evitar redimensionado
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
