package es.studium.Kiriki;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener
{
	MenuInicio vmi; //Declaramos objetos de las clases
	NuevaPartida vnp;
	Ranking vr;
	Ayuda va;
	Jugando vj;
	
	public Controlador(Modelo m, MenuInicio vm, NuevaPartida vn, Ranking vr, Ayuda va, Jugando vj)
	{
		vm.addWindowListener(this); // Damos funcionalidad a los botones de las ventanas
		vm.btnSalir.addActionListener(this);
		vm.btnRanking.addActionListener(this);
		vm.btnNuevaPartida.addActionListener(this);
	}
	
	public void MostrarDialogNumeroJugadores() //Método para mostrar diálogo que nos preguntará por el número de jugadores de la partida
	{
		vnp.pedirNumeroJugadores.setVisible(true);
	}
	
	public void OcultarDialogNumeroJugadores() //Método para ocultar el diálogo anterior
	{
		vnp.pedirNumeroJugadores.setVisible(false);
	}
	
	public void PrepararDialogNombresJugadores(int numero) //Método que prepara el diálogo que nos pregunta por los nombres de los jugadores, cuya cantidad vendrá definida por lo que se haya elegido en el cuadro del diálogo despegable
	{
		vnp.pedirNombresJugadores.setBackground(Color.YELLOW); //Color del fondo del Dialog
		vnp.pedirNombresJugadores.setLayout(new FlowLayout()); //Layout del Dialog
		vnp.pedirNombresJugadores.setSize(240,200); //Tamaño del Dialog
		vnp.pedirNombresJugadores.setLocationRelativeTo(null); //Centrar el Dialog
		vnp.pedirNombresJugadores.setResizable(false); //Evitar redimensionado
		
		//Preguntamos los nombres
		//Jugador 1, siempre existe
		vnp.pedirNombresJugadores.add(vnp.lblEtiqueta1);
		vnp.txfNombre1.selectAll(); //Reseteamos los cuadros de texto
		vnp.txfNombre1.setText("");
		vnp.pedirNombresJugadores.add(vnp.txfNombre1);
		//Jugador 2, siempre existe
		vnp.pedirNombresJugadores.add(vnp.lblEtiqueta2);
		vnp.txfNombre2.selectAll(); //Reseteamos los cuadros de texto
		vnp.txfNombre2.setText("");
		vnp.pedirNombresJugadores.add(vnp.txfNombre2);
		
		//Si existe Jugador 3
		if(numero == 3)
		{
			vnp.pedirNombresJugadores.add(vnp.lblEtiqueta3);
			vnp.txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			vnp.txfNombre3.setText("");
			vnp.pedirNombresJugadores.add(vnp.txfNombre3);
		}
		else
		{
			vnp.pedirNombresJugadores.remove(vnp.lblEtiqueta3);
			vnp.txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			vnp.txfNombre3.setText("");
			vnp.pedirNombresJugadores.remove(vnp.txfNombre3);
		}
		if(numero == 4)
		{
			vnp.pedirNombresJugadores.add(vnp.lblEtiqueta3);
			vnp.txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			vnp.txfNombre3.setText("");
			vnp.pedirNombresJugadores.add(vnp.txfNombre3);
			vnp.pedirNombresJugadores.add(vnp.lblEtiqueta4);
			vnp.txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			vnp.txfNombre3.setText("");
			vnp.pedirNombresJugadores.add(vnp.txfNombre4);
		}
		else
		{
			vnp.pedirNombresJugadores.remove(vnp.lblEtiqueta4);
			vnp.txfNombre3.selectAll(); //Reseteamos los cuadros de texto
			vnp.txfNombre3.setText("");
			vnp.pedirNombresJugadores.remove(vnp.txfNombre4);
		}
		
		vnp.pedirNombresJugadores.add(vnp.btnComenzarPartida);
		this.MostrarDialogNombresJugadores(); //Llamamos al método que muestra el diálogo
	}
	
	public void MostrarDialogNombresJugadores() //Método que muestra el diálogo que pregunta los nombres de los jugadores
	{
		vnp.pedirNombresJugadores.setVisible(true);
	}
		
	public void OcultarDialogNombresJugadores() //Método que oculta el diálogo anterior
	{
		vnp.pedirNombresJugadores.setVisible(false);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object botonPulsado = arg0.getSource();
		if(botonPulsado.equals(vmi.btnSalir))
		{
			System.exit(0);
		}
		else if(botonPulsado.equals(vmi.btnRanking)) //Ejecutar al pulsar el botón "Ranking"
		{
			vr = new Ranking(); //Crear el Objeto vr, antes declarado
			vr.addWindowListener(this); //Añadir listener para poder cerrar la ventana
			vr.btnVolver.addActionListener(this); //Añadir listener al botón "Volver"
			vr.MostrarRanking(); //Muestra la ventana
		}
		else if(botonPulsado.equals(vr.btnVolver)) //Gestionar el botón "Volver" de la nueva ventana Ranking
		{
			vr.OcultarRanking();
		}
		else if(botonPulsado.equals(vmi.btnNuevaPartida)) //Al pulsar el botón "Partida Nueva", mostrar el diálogo para pedir el número de jugadores mediante un desplegable
		{
			vnp.pedirNumeroJugadores.addWindowListener(this);
			vnp.btnContinuar.addActionListener(this);
			MostrarDialogNumeroJugadores();
		}
		else if(botonPulsado.equals(vnp.btnContinuar)) //Si ha pulsado el botón "Continuar" del diálogo anterior, se llama al método que prepara el siguiente diálogo pasándole como parámetro el número de jugadores
		{
			if(!vnp.choNumeroJugadores.getSelectedItem().equals("Elegir número de jugadores...")) //Este método prepara el contenido del diálogo en función de este valor pasado y muestra dicho diálogo
			{
				vnp.pedirNombresJugadores.addWindowListener(this);
				vnp.btnComenzarPartida.addActionListener(this);
				PrepararDialogNombresJugadores(Integer.parseInt(vnp.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(vnp.btnComenzarPartida)) //Si ha pulsado el botón "Comenzar Partida" del diálogo anterior, ya una vez escritos los nombres de los jugadores
		{
			if((!vnp.txfNombre1.getText().equals("")) //Si se queda algún nombre en blanco no se puede comenzar la partida
				&& (!vnp.txfNombre2.getText().equals(""))
				&& (!vnp.txfNombre3.getText().equals(""))
				&& (!vnp.txfNombre4.getText().equals("")))
			{
				System.out.println("Comienza la partida..."); //Si todo es correcto, de momento, simplemente mostraremos por consola el mensaje "Comienza la partida..."
			}
			else
			{
				vnp.txfNombre1.requestFocus();
			}
		}
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent arg0)
	{
		if(vr.isActive()) //Cerrar ventana Ranking
		{
			vr.OcultarRanking();
		}
		else if(vnp.pedirNumeroJugadores.isActive()) //Cerrar ventana PartidaNueva pidiendo número jugadores
		{
			this.vnp.pedirNumeroJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			this.vnp.btnContinuar.removeActionListener(this); //Eliminar Listener Botón
			this.OcultarDialogNumeroJugadores();
		}
		else if(vnp.pedirNombresJugadores.isActive()) //Cerrar ventana PartidaNueva pidiendo nombres jugadores
		{
			vnp.pedirNombresJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			vnp.btnComenzarPartida.removeActionListener(this); //Eliminar Listener Botón
			vnp.choNumeroJugadores.select(0); //Reseteamos el desplegable
			removeAll();
			OcultarDialogNombresJugadores();
		}
		else
		{
			System.exit(0);
		}
	}

	@Override
	public void windowDeactivated(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
	
}