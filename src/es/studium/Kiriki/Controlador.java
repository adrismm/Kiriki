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
	
	public Controlador(Modelo m, MenuInicio vmi, NuevaPartida vnp, Ranking vr, Ayuda va, Jugando vj)
	{
		vmi.addWindowListener(this); // Damos funcionalidad a los botones de las ventanas
		vmi.btnSalir.addActionListener(this);
		vmi.btnRanking.addActionListener(this);
		vmi.btnNuevaPartida.addActionListener(this);
	}
	
	// Di�logos Nueva Partida
	public void PrepararDialogNombresJugadores(int numero) //M�todo que prepara el di�logo que nos pregunta por los nombres de los jugadores, cuya cantidad vendr� definida por lo que se haya elegido en el cuadro del di�logo despegable
	{
		vnp.pedirNombresJugadores.setBackground(Color.YELLOW); //Color del fondo del Dialog
		vnp.pedirNombresJugadores.setLayout(new FlowLayout()); //Layout del Dialog
		vnp.pedirNombresJugadores.setSize(240,200); //Tama�o del Dialog
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
		this.MostrarDialogNombresJugadores(); //Llamamos al m�todo que muestra el di�logo
	}
	
	public void MostrarDialogNombresJugadores() //M�todo que muestra el di�logo que pregunta los nombres de los jugadores
	{
		vnp.pedirNombresJugadores.setVisible(true);
	}
		
	public void OcultarDialogNombresJugadores() //M�todo que oculta el di�logo anterior
	{
		vnp.pedirNombresJugadores.setVisible(false);
	}
	
	public void MostrarDialogNumeroJugadores() //M�todo para mostrar di�logo que nos preguntar� por el n�mero de jugadores de la partida
	{
		vnp.pedirNumeroJugadores.setVisible(true);
	}
	
	public void OcultarDialogNumeroJugadores() //M�todo para ocultar el di�logo anterior
	{
		vnp.pedirNumeroJugadores.setVisible(false);
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
			MostrarDialogNumeroJugadores();
		}
		else if(botonPulsado.equals(vnp.btnContinuar)) //Si ha pulsado el bot�n "Continuar" del di�logo anterior, se llama al m�todo que prepara el siguiente di�logo pas�ndole como par�metro el n�mero de jugadores
		{
			if(!vnp.choNumeroJugadores.getSelectedItem().equals("Elegir n�mero de jugadores...")) //Este m�todo prepara el contenido del di�logo en funci�n de este valor pasado y muestra dicho di�logo
			{
				vnp.pedirNombresJugadores.addWindowListener(this);
				vnp.btnComenzarPartida.addActionListener(this);
				PrepararDialogNombresJugadores(Integer.parseInt(vnp.choNumeroJugadores.getSelectedItem()));
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
			this.OcultarDialogNumeroJugadores();
		}
		else if(vnp.pedirNombresJugadores.isActive()) //Cerrar ventana PartidaNueva pidiendo nombres jugadores
		{
			vnp.pedirNombresJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			vnp.btnComenzarPartida.removeActionListener(this); //Eliminar Listener Bot�n
			vnp.choNumeroJugadores.select(0); //Reseteamos el desplegable
			//removeAll();
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