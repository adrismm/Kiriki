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
	
	// Ventana menu principal
	Label lblMenuPrincipal = new Label ("MENU PRINCIPAL", 1); // 1 para que salga centrado
	Panel pnlBotonera = new Panel(); // Panel para botonera principal
	Panel pnlBotoneraSalir = new Panel(); // Panel para boton Salir
	Button btnNuevaPartida = new Button ("Nueva Partida");
	Button btnRanking = new Button ("Ranking");
	Button btnAyuda = new Button("Ayuda");
	Button btnSalir = new Button("Salir");
	
	Color myColor = new Color(0, 128, 0);
	
	Toolkit herramientas;
	Image fondo;
	
	public MenuInicio()
	{
		this.setTitle("Kiriki"); // Titulo
		this.setLayout(new FlowLayout()); // Layout del Frame
		
		pnlBotonera.setLayout(new GridLayout(4,1)); // Layout del Panel
		pnlBotonera.add(lblMenuPrincipal); // Aniadir Boton a Panel
		pnlBotonera.add(btnNuevaPartida); // Aniadir Boton a Panel 	
		pnlBotonera.add(btnRanking); // Aniadir Boton a Panel
		pnlBotonera.add(btnAyuda); // Aniadir Boton a Panel
		this.add(pnlBotonera, "Center"); // Aniadir Panel a Frame
		pnlBotoneraSalir.add(btnSalir); // Aniadir Boton a Panel
		this.add(pnlBotoneraSalir, "South"); // Aniadir Panel a Frame
		
		// Agregar imagen de fondo al menu de inicio, ademas, de colocarla y redimensionarla
		herramientas = getToolkit();
		fondo = herramientas.getImage("fondoMenu.jpg").getScaledInstance(390, 240, Image.SCALE_AREA_AVERAGING);
		pnlBotonera.setPreferredSize(new Dimension(200,100)); //redimensionar el panel
		pnlBotoneraSalir.setPreferredSize(new Dimension(200,30)); //redimensionar el panel
		
		// Color del fondo de los paneles de las botoneras
		pnlBotonera.setBackground(myColor);
		pnlBotoneraSalir.setBackground(myColor);
		
		this.setSize(400,200); // Tamanio de Frame
		this.setLocationRelativeTo(null); // Centrar la ventana
		this.setResizable(false); // Evitar redimensionado
		
		MostrarInicio(); // Mostrarlo
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
