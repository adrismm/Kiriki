package es.studium.Kiriki;


import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;



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
	
	
	Toolkit herramientas;
	Image fondo;
	
	public MenuInicio()
	{
		this.setTitle("Kiriki"); //T�tulo
		//this.setBackground(Color.YELLOW); //Color del fondo del Frame
		this.setLayout(new FlowLayout()); //Layout del Frame
		
		pnlBotonera.setLayout(new GridLayout(4,1)); //Layout del Panel
		pnlBotonera.add(lblMenuPrincipal); //A�adir Bot�n a Panel
		pnlBotonera.add(btnNuevaPartida); //A�adir Bot�n a Panel 	
		pnlBotonera.add(btnRanking); //A�adir Bot�n a Panel
		pnlBotonera.add(btnAyuda); //A�adir Bot�n a Panel
		this.add(pnlBotonera, "Center"); //A�adir Panel a Frame
		pnlBotoneraSalir.add(btnSalir); //A�adir Bot�n a Panel
		this.add(pnlBotoneraSalir, "South"); //A�adir Panel a Frame
		
		//agregar imagen de fondo al menu de inicio, además, de colocarla y redimensionarla
		herramientas = getToolkit();
		fondo = herramientas.getImage("fondoMenu.jpg").getScaledInstance(390, 240, Image.SCALE_AREA_AVERAGING);
		pnlBotonera.setPreferredSize(new Dimension(200,100)); //redimensionar el panel
		pnlBotoneraSalir.setPreferredSize(new Dimension(200,30)); //redimensionar el panel
		
		//cambiar color al panel botonera
		Color myColor = new Color(0, 128, 0);
		pnlBotonera.setBackground(myColor);
		//cambiar color al panel botoneraSAlir
		Color myColor2 = new Color(60, 179, 113);
		pnlBotoneraSalir.setBackground(myColor2);
		
		this.setSize(400,200); //Tama�o de Frame
		this.setLocationRelativeTo(null); //Centrar la ventana
		this.setResizable(false); //Evitar redimensionado
		
		MostrarInicio(); //Mostrarlo
	}
	
	
	public void paint(Graphics g)
	{
		g.drawImage(fondo, 6, 25, this);
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
