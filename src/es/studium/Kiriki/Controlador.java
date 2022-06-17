package es.studium.Kiriki;

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

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object botonPulsado = arg0.getSource();
		if(botonPulsado.equals(vmi.btnSalir))
		{
			System.exit(0);
		}
		else if(botonPulsado.equals(vmi.btnRanking)) //Ejecutar al pulsar el bot�n "Ranking"
		{
			vr = new Ranking(); //Crear el Objeto vr, antes declarado
			vr.addWindowListener(this); //A�adir listener para poder cerrar la ventana
			vr.btnVolver.addActionListener(this); //A�adir listener al bot�n "Volver"
			vr.MostrarRanking(); //Muestra la ventana
		}
		else if(botonPulsado.equals(vr.btnVolver)) //Gestionar el bot�n "Volver" de la nueva ventana Ranking
		{
			vr.OcultarRanking();
		}
		else if(botonPulsado.equals(vmi.btnNuevaPartida)) //Al pulsar el bot�n "Partida Nueva", mostrar el di�logo para pedir el n�mero de jugadores mediante un desplegable
		{
			vnp.pedirNumeroJugadores.addWindowListener(this);
			vnp.btnContinuar.addActionListener(this);
			vnp.MostrarDialogNumeroJugadores();
		}
		else if(botonPulsado.equals(vnp.btnContinuar)) //Si ha pulsado el bot�n "Continuar" del di�logo anterior, se llama al m�todo que prepara el siguiente di�logo pas�ndole como par�metro el n�mero de jugadores
		{
			if(!vnp.choNumeroJugadores.getSelectedItem().equals("Elegir n�mero de jugadores...")) //Este m�todo prepara el contenido del di�logo en funci�n de este valor pasado y muestra dicho di�logo
			{
				vnp.pedirNombresJugadores.addWindowListener(this);
				vnp.btnComenzarPartida.addActionListener(this);
				vnp.PrepararDialogNombresJugadores(Integer.parseInt(vnp.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(vnp.btnComenzarPartida)) //Si ha pulsado el bot�n "Comenzar Partida" del di�logo anterior, ya una vez escritos los nombres de los jugadores
		{
			if((!vnp.txfNombre1.getText().equals("")) //Si se queda alg�n nombre en blanco no se puede comenzar la partida
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
		else if(vnp.pedirNumeroJugadores.isActive()) //Cerrar ventana PartidaNueva pidiendo n�mero jugadores
		{
			this.vnp.pedirNumeroJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			this.vnp.btnContinuar.removeActionListener(this); //Eliminar Listener Bot�n
			this.vnp.OcultarDialogNumeroJugadores();
		}
		else if(vnp.pedirNombresJugadores.isActive()) //Cerrar ventana PartidaNueva pidiendo nombres jugadores
		{
			vnp.pedirNombresJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			vnp.btnComenzarPartida.removeActionListener(this); //Eliminar Listener Bot�n
			vnp.choNumeroJugadores.select(0); //Reseteamos el desplegable
			//vnp.removeAll();
			vnp.OcultarDialogNombresJugadores();
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