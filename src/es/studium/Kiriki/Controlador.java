package es.studium.Kiriki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;

public class Controlador implements WindowListener, ActionListener, MouseListener
{
	Modelo modelo; // Base de datos ranking y otras operaciones
	MenuInicio vistaMenuInicio; // Menu principal
	NuevaPartida vistaNuevaPartida; // Partida nueva
	Ranking vistaRanking; // Top 10 jugadores
	Jugando vistaJugando; // Tapete de juego
	
	int turno = 1; // Contador con el que actualizaremos los turnos
	int tiradaDado1; // Entero que almacena el valor de la tirada del dado 1
	int tiradaDado2; // Entero que almacena el valor de la tirada del dado 2

	String valorTirada; // Cadena para almacenar el valor asociado al resultado de la tirada real
	String valorAnunciado; // Cadena para almacenar el valor asociado al resultado anunciado por el jugador
	
	boolean controlTurno = true; // Boolean de control para dejar a un jugador realizar una tirada o no
	boolean controlMentira = false; // Boolean de control segun un jugador haya mentido o no
	
	int[] vidasGanador = new int[4]; // Array para almacenar las vidas actuales de los jugadores y comprobar si hay un ganador
	
	Connection conexion = null; // Conectar con la base de datos
	
	// Constructor
	public Controlador(Modelo m, MenuInicio vmi, NuevaPartida vnp, Ranking vr, Jugando vj)
	{
		this.modelo = m;
		this.vistaMenuInicio = vmi;
		this.vistaNuevaPartida = vnp;
		this.vistaRanking = vr;
		this.vistaJugando = vj;
		
		// Listeners ventana Menu Principal
		vmi.addWindowListener(this);
		vmi.btnNuevaPartida.addActionListener(this);	
		vmi.btnRanking.addActionListener(this);
		vmi.btnSalir.addActionListener(this);
		vmi.btnAyuda.addActionListener(this);
		vmi.addMouseListener(this);
		
		// Listeners ventana Ranking
		vr.addWindowListener(this);
		vr.btnVolver.addActionListener(this);
		
		// Listeners ventana Crear Partida
		vnp.pedirNumeroJugadores.addWindowListener(this);
		vnp.btnContinuar.addActionListener(this);
		vnp.pedirNombresJugadores.addWindowListener(this);
		vnp.btnComenzarPartida.addActionListener(this);
		vnp.dlgMensajeFaltanNombres.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object botonPulsado = arg0.getSource();
		
		if(botonPulsado.equals(this.vistaMenuInicio.btnSalir))
		{
			System.exit(0);
		}
		if(botonPulsado.equals(this.vistaMenuInicio.btnRanking)) // Ejecutar al pulsar el boton Ranking
		{
			this.vistaRanking.MostrarRanking(); // Muestra la ventana
			this.vistaMenuInicio.OcultarInicio();
			
			conexion = this.modelo.conectar(); // Conectar con la base de datos
			//this.vistaRanking.listadoJugadores.append(consulta);
			
			String cogerRanking = this.modelo.mejoresJugadores(conexion);
			String[] completarRanking = cogerRanking.split("/");
			
			// Adaptar espacios ranking segun numero de jugadores ganadores
			if(completarRanking.length >=2 && completarRanking.length <4)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
				}
			else if(completarRanking.length >=4 && completarRanking.length <6)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
				}
			else if(completarRanking.length >=6 && completarRanking.length <8)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
				}
			else if(completarRanking.length >=8 && completarRanking.length <10)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
				}
			else if(completarRanking.length >=10 && completarRanking.length <12)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
				}
			else if(completarRanking.length >=12 && completarRanking.length <14)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
					this.vistaRanking.lblJugador6.setText(completarRanking[10]);
					this.vistaRanking.lblPuntos6.setText(completarRanking[11]);
				}
			else if(completarRanking.length >=14 && completarRanking.length <16)
				{	
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
					this.vistaRanking.lblJugador6.setText(completarRanking[10]);
					this.vistaRanking.lblPuntos6.setText(completarRanking[11]);
					this.vistaRanking.lblJugador7.setText(completarRanking[12]);
					this.vistaRanking.lblPuntos7.setText(completarRanking[13]);
				}
			else if(completarRanking.length >=16 && completarRanking.length <18)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
					this.vistaRanking.lblJugador6.setText(completarRanking[10]);
					this.vistaRanking.lblPuntos6.setText(completarRanking[11]);
					this.vistaRanking.lblJugador7.setText(completarRanking[12]);
					this.vistaRanking.lblPuntos7.setText(completarRanking[13]);
					this.vistaRanking.lblJugador8.setText(completarRanking[14]);
					this.vistaRanking.lblPuntos8.setText(completarRanking[15]);
				}
			else if(completarRanking.length >=18 && completarRanking.length <20)
				{
					this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
					this.vistaRanking.lblJugador6.setText(completarRanking[10]);
					this.vistaRanking.lblPuntos6.setText(completarRanking[11]);
					this.vistaRanking.lblJugador7.setText(completarRanking[12]);
					this.vistaRanking.lblPuntos7.setText(completarRanking[13]);
					this.vistaRanking.lblJugador8.setText(completarRanking[14]);
					this.vistaRanking.lblPuntos8.setText(completarRanking[15]);
					this.vistaRanking.lblJugador9.setText(completarRanking[16]);
					this.vistaRanking.lblPuntos9.setText(completarRanking[17]);
				}
			else if(completarRanking.length >=20)
				{	this.vistaRanking.lblJugador1.setText(completarRanking[0]);
					this.vistaRanking.lblPuntos1.setText(completarRanking[1]);
					this.vistaRanking.lblJugador2.setText(completarRanking[2]);
					this.vistaRanking.lblPuntos2.setText(completarRanking[3]);
					this.vistaRanking.lblJugador3.setText(completarRanking[4]);
					this.vistaRanking.lblPuntos3.setText(completarRanking[5]);
					this.vistaRanking.lblJugador4.setText(completarRanking[6]);
					this.vistaRanking.lblPuntos4.setText(completarRanking[7]);
					this.vistaRanking.lblJugador5.setText(completarRanking[8]);
					this.vistaRanking.lblPuntos5.setText(completarRanking[9]);
					this.vistaRanking.lblJugador6.setText(completarRanking[10]);
					this.vistaRanking.lblPuntos6.setText(completarRanking[11]);
					this.vistaRanking.lblJugador7.setText(completarRanking[12]);
					this.vistaRanking.lblPuntos7.setText(completarRanking[13]);
					this.vistaRanking.lblJugador8.setText(completarRanking[14]);
					this.vistaRanking.lblPuntos8.setText(completarRanking[15]);
					this.vistaRanking.lblJugador9.setText(completarRanking[16]);
					this.vistaRanking.lblPuntos9.setText(completarRanking[17]);
					this.vistaRanking.lblJugador10.setText(completarRanking[18]);
					this.vistaRanking.lblPuntos10.setText(completarRanking[19]);
				}
			
			this.modelo.desconectar(conexion);
		}
		
		// Lanzar ventana de AYUDA al pulsar el boton de ayuda 
		if(botonPulsado.equals(this.vistaMenuInicio.btnAyuda))
			{
				this.modelo.ayuda(conexion);
				this.modelo.desconectar(conexion);
			}
		else if(botonPulsado.equals(this.vistaRanking.btnVolver)) // Gestionar el boton "Volver" de la nueva ventana Ranking
		{
			this.vistaRanking.OcultarRanking();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(botonPulsado.equals(this.vistaMenuInicio.btnNuevaPartida)) // Al pulsar el boton "Nueva Partida", mostrar el dialogo para pedir el numero de jugadores mediante un desplegable
		{
			this.vistaNuevaPartida.MostrarDialogNumeroJugadores();
			this.vistaMenuInicio.OcultarInicio();
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnContinuar)) // Si ha pulsado el boton "Continuar" del dialogo anterior, llama al metodo que obtiene el numero de jugadores
		{
			if(!this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem().equals("Elegir numero de jugadores...")) // Este metodo prepara el contenido del dialogo y lo muestra
			{
				this.vistaNuevaPartida.PrepararDialogNombresJugadores(Integer.parseInt(this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnComenzarPartida)) // Si ha pulsado el boton "Comenzar Partida" del dialogo anterior, ya una vez escritos los nombres de los jugadores
		{			
			if((this.vistaNuevaPartida.numJugadores == 4) && (!this.vistaNuevaPartida.txfNombre1.getText().equals("")) // Se crea la partida, obteniendo los nombres de los jugadores y segun cuantos sean
				&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre3.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre4.getText().equals("")))
			{	
				// Creamos un objeto de la clase "Jugando" con los nombres de los jugadores como parametros
				this.vistaJugando = new Jugando(4, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), this.vistaNuevaPartida.txfNombre4.getText());
				
				// Mostramos la ventana de juego, ocultamos el resto y un mensaje de aviso al comenzar la partida creada
			 	this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				
				// Aniadimos los listeners correspondiente al frame, los dialogos y botones
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.btnAnunciarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
				this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
				this.vistaJugando.btnMensajeKiriki.addActionListener(this);
				this.vistaJugando.dlgMensajeFinPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
				
				// Llamamos a este metodo para fijar las vidas con la que comienzan los jugadores
				this.vistaJugando.resetearVidas();
			}
			else if((this.vistaNuevaPartida.numJugadores == 3) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre3.getText().equals("")))
			{
				// Creamos un objeto de la clase "Jugando" con los nombres de los jugadores como parametros
				this.vistaJugando = new Jugando(3, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), "");
				
				// Mostramos la ventana de juego, ocultamos el resto y un mensaje de aviso al comenzar la partida creada
			 	this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				
				// Aniadimos los listeners correspondiente al frame, los dialogos y botones
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.btnAnunciarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
				this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
				this.vistaJugando.btnMensajeKiriki.addActionListener(this);
				this.vistaJugando.dlgMensajeFinPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
				
				// Llamamos a este metodo para fijar las vidas con la que comienzan los jugadores
				this.vistaJugando.resetearVidas();
			}
			else if((this.vistaNuevaPartida.numJugadores == 2) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals("")))
			{
				// Creamos un objeto de la clase "Jugando" con los nombres de los jugadores como parametros
				this.vistaJugando = new Jugando(2, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), "", "");
				
				// Mostramos la ventana de juego, ocultamos el resto y un mensaje de aviso al comenzar la partida creada
			 	this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				
				// Aniadimos los listeners correspondiente al frame, los dialogos y botones
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.btnAnunciarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
				this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
				this.vistaJugando.btnMensajeKiriki.addActionListener(this);
				this.vistaJugando.dlgMensajeFinPartida.addWindowListener(this);
				this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
				
				// Llamamos a este metodo para fijar las vidas con la que comienzan los jugadores
				this.vistaJugando.resetearVidas();
			}
			else
			{
				this.vistaNuevaPartida.MensajeErrorFaltanNombres(); // Mostramos un error si se deja algun nombre en blanco
			}
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeComienzoPartida)) // Si pulsamos el boton del aviso de partida comenzada, ocultamos el mensaje
		{
			this.vistaJugando.dlgMensajeComienzoPartida.setVisible(false);
		}	
		else if(botonPulsado.equals(this.vistaJugando.btnAnunciarValor)) // Si pulsamos el boton y hemos elegido un valor que anunciar en las casillas, anunciamos ese valor elegido
		{
			if(!this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().equals(null))
			{
				this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
				this.vistaJugando.mostrarValorAnunciado(this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().getLabel());
				this.vistaJugando.dlgMensajeValorAnunciado.setVisible(true);
			}
			
			this.vistaJugando.cambiarCubilete(0); // Tapamos los dados tirados con el cubilete justo antes de pasarselo al siguiente jugador
			this.vistaJugando.recuperarCubilete(0);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorAnunciado)) // Aviso para todos los jugadores del valor anunciado por el jugador que ha tirado los dados
		{
			this.vistaJugando.dlgMensajeValorAnunciado.setVisible(false); // Ocultamos el mensaje del aviso del valor anunciado

			if((this.vistaJugando.numJugadores == 4) && ((turno == 5) || (turno == 8))) // Con el contador de turnos nos aseguramos de que se reinicie segun el numero de jugadores
			{
				turno = 1;
			}
			else if((this.vistaJugando.numJugadores == 3) && ((turno == 4) || (turno == 6)))
			{
				turno = 1;
			}
			else if((this.vistaJugando.numJugadores == 2) && ((turno == 3) || (turno == 4)))
			{
				turno = 1;
			}
			
			turno = turno + 1;
			this.vistaJugando.actualizarTurno(turno); // Actualizamos el turno y pasamos al siguiente jugador que recibe la tirada y el valor anunciado
			
			this.vistaJugando.lblMensajeValorRecibido.setText("Intentas superar la tirada anterior o destapas los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnAceptarValor)) // Si pulsamos en el boton "Aceptar", decidimos volver a tirar e intentar superar el valor anunciado por el jugador anterior
		{
			controlTurno = true; // Habilitamos que se pueda realizar una tirada
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
			this.vistaJugando.lblMensajeValorAceptado.setText("Suerte superando la tirada! Vuelve a tirar los dados");
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(true);
			this.vistaJugando.chkgrValorTirada.setSelectedCheckbox(null); // Limpiamos las casillas elegidas por el jugador anterior al anunciar el valor de su tirada
		}
		else if(botonPulsado.equals(this.vistaJugando.btnRechazarValor)) // Si pulsamos el boton "Rechazar", decidimos levantar el cubilete y comprobar si el jugador anterior miente o no
		{
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
			this.vistaJugando.lblMensajeValorRechazado.setText("Y la tirada anterior era realmente un/a... " + valorTirada); // Mostramos el valor de la tirada real
			this.vistaJugando.dlgMensajeValorRechazado.setVisible(true);
			
			this.vistaJugando.cargarDados();
			this.vistaJugando.mostrarDadoCubiletes(tiradaDado1, tiradaDado2); // Mostramos a todos las imagenes de la tirada real
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2); // Almacenamos el valor de la tirada real
			valorAnunciado = this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().getLabel(); // Almacenamos el valor anunciado por el jugador anterior
			controlMentira = this.modelo.compararValores(valorTirada, valorAnunciado); // Comparamos los valores asociados a la tirada y al valor anunciado, para saber si el jugador anterior ha mentido o no
			
			if(controlMentira == true) // En caso de que haya mentido, el metodo "compararValores" nos devuelve: true
			{
				this.vistaJugando.lblMensajeValorFalso.setText("El jugador anterior ha mentido y pierde 1 vida!");
				this.vistaJugando.lblMensajeValorFalso1.setText("Tu continuas la partida, vuelve a tirar los dados");
				this.vistaJugando.lblMensajeValorVerdadero.setVisible(false); // Mostramos un mensaje u otro en el dialogo, segun si ha mentido o no
				this.vistaJugando.lblMensajeValorVerdadero1.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setVisible(true);
				this.vistaJugando.lblMensajeValorFalso1.setVisible(true);
				
				controlTurno = true; // Habilitamos que se pueda realizar una tirada
				turno = turno -1; // Actualizamos el contador para el jugador anterior
				
				if(turno == 1) // Ademas el jugador anterior que ha mentido pierde una vida
				{
					this.vistaJugando.quitarVidasJugador1();
				}
				else if(turno == 2)
				{
					this.vistaJugando.quitarVidasJugador2();
				}
				else if(turno == 3)
				{
					this.vistaJugando.quitarVidasJugador3();
				}
				else if(turno == 4)
				{
					this.vistaJugando.quitarVidasJugador4();
				}
				
				// Comprobamos si hay algun ganador despues de actualizar las vidas
				if(this.vistaJugando.numJugadores == 4)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, this.vistaJugando.vidasJugador4);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[2] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[3] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador4 + " con " + this.vistaJugando.vidasJugador4 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
				else if(this.vistaJugando.numJugadores == 3)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, 0);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[2] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
				else if(this.vistaJugando.numJugadores == 2)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, 0, 0);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
			}
			else if(controlMentira == false) // En caso de que no haya mentido, el metodo "compararValores" nos devuelve: false
			{
				this.vistaJugando.lblMensajeValorVerdadero.setText("El jugador anterior ha dicho la verdad y tu pierdes 1 vida!");
				this.vistaJugando.lblMensajeValorVerdadero1.setText("El siguiente jugador continua la partida, que el vuelva a tirar los dados");
				this.vistaJugando.lblMensajeValorVerdadero.setVisible(true); // Mostramos un mensaje u otro en el dialogo, segun si ha mentido o no
				this.vistaJugando.lblMensajeValorVerdadero1.setVisible(true);
				this.vistaJugando.lblMensajeValorFalso.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso1.setVisible(false);
				
				this.vistaJugando.chkgrValorTirada.setSelectedCheckbox(null); // Limpiamos las casillas elegidas por el jugador anterior al anunciar el valor de su tirada
				controlTurno = true; // Habilitamos que se pueda realizar una tirada
				
				if(turno == 1) // Ademas el jugador anterior que ha dicho la verdad hace que el jugador actual pierda una vida
				{
					this.vistaJugando.quitarVidasJugador1();
				}
				else if(turno == 2)
				{
					this.vistaJugando.quitarVidasJugador2();
				}
				else if(turno == 3)
				{
					this.vistaJugando.quitarVidasJugador3();
				}
				else if(turno == 4)
				{
					this.vistaJugando.quitarVidasJugador4();
				}
				
				// Comprobamos si hay algun ganador despues de actualizar las vidas
				if(this.vistaJugando.numJugadores == 4)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, this.vistaJugando.vidasJugador4);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[2] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[3] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador4 + " con " + this.vistaJugando.vidasJugador4 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
				else if(this.vistaJugando.numJugadores == 3)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, 0);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[2] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
				else if(this.vistaJugando.numJugadores == 2)
				{
					vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, 0, 0);
					
					if(vidasGanador[0] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
					else if(vidasGanador[1] != 0)
					{
						this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
						this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
					}
				}
			}
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorRechazado)) // Si pulsamos este boton, se ocultan los dados destapados y se actualiza el turno, y el jugador que le corresponda puede realizar una tirada
		{
			this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
			
			if((controlMentira == false) && (this.vistaJugando.numJugadores == 4) && (turno == 4))
			{
				turno = 0;
			}
			else if((controlMentira == false) && (this.vistaJugando.numJugadores == 3) && (turno == 3))
			{
				turno = 0;
			}
			else if((controlMentira == false) && (this.vistaJugando.numJugadores == 2) && (turno == 2))
			{
				turno = 0;
			}
			
			this.vistaJugando.mostrarDadoCubiletes(0, 0);
			
			turno = turno + 1;
			this.vistaJugando.actualizarTurno(turno);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorAceptado)) // Si pulsamos este boton, ocultamos el mensaje y el jugador actual puede realizar una tirada e intentar superar la anterior
		{
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(false);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeKiriki)) // En caso de que nos salga el valor especial "Kiriki" en una tirada, automaticamente el siguiente jugador pierde 1 vida y su turno
		{
			this.vistaJugando.dlgMensajeKiriki.setVisible(false);
			turno = turno + 1; // Actualizamos el contador para el siguiente jugador
			
			if(turno == 1) // El siguiente jugador pierde 1 vida
			{
				this.vistaJugando.quitarVidasJugador1();
			}
			else if(turno == 2)
			{
				this.vistaJugando.quitarVidasJugador2();
			}
			else if(turno == 3)
			{
				this.vistaJugando.quitarVidasJugador3();
			}
			else if(turno == 4)
			{
				this.vistaJugando.quitarVidasJugador4();
			}
			
			// Comprobamos si hay algun ganador despues de actualizar las vidas
			if(this.vistaJugando.numJugadores == 4)
			{
				vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, this.vistaJugando.vidasJugador4);
				
				if(vidasGanador[0] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[1] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[2] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[3] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador4 + " con " + this.vistaJugando.vidasJugador4 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
			}
			else if(this.vistaJugando.numJugadores == 3)
			{
				vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, 0);
				
				if(vidasGanador[0] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[1] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[2] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador3 + " con " + this.vistaJugando.vidasJugador3 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
			}
			else if(this.vistaJugando.numJugadores == 2)
			{
				vidasGanador = this.modelo.comprobarVidas(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, 0, 0);
				
				if(vidasGanador[0] != 0)
				{
					System.out.println("Aloh");
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador1 + " con " + this.vistaJugando.vidasJugador1 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
				else if(vidasGanador[1] != 0)
				{
					this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: " + this.vistaJugando.jugador2 + " con " + this.vistaJugando.vidasJugador2 + " vidas.");
					this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
				}
			}
			
			turno = turno + 1; // Actualizamos de nuevo el contador de turnos, para saltar el turno del jugador que ha perdido la vida
			
			if((this.vistaJugando.numJugadores == 4) && ((turno == 5) || (turno == 8)))
			{
				turno = 1;
			}
			else if((this.vistaJugando.numJugadores == 3) && ((turno == 4) || (turno == 6)))
			{
				turno = 1;
			}
			else if((this.vistaJugando.numJugadores == 2) && ((turno == 3) || (turno == 4)))
			{
				turno = 1;
			}
			
			this.vistaJugando.actualizarTurno(turno); // Actualizamos el turno, comprobando si tiene que volver al primer jugador o no
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeFinPartida)) // En caso de que haya un ganador, se muestra un mensaje de finalizacion, si pulsamos el boton salimos de la partida
		{
			this.vistaJugando.dlgMensajeFinPartida.setVisible(false);
			
			// Reiniciamos contadores
			this.vistaJugando.resetearVidas();
			turno = 1;
			
			this.vistaJugando.OcultarJugando();
			this.vistaMenuInicio.MostrarInicio(); // Volvemos a mostrar el "Menu Principal"
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
		if(this.vistaRanking.isActive()) // Cerrar ventana Ranking
		{
			this.vistaRanking.OcultarRanking();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(this.vistaNuevaPartida.pedirNumeroJugadores.isActive()) // Cerrar ventana NuevaPartida pidiendo numero jugadores
		{
			this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
			this.vistaMenuInicio.MostrarInicio(); 
		}
		else if(this.vistaNuevaPartida.pedirNombresJugadores.isActive()) // Cerrar ventana NuevaPartida pidiendo nombres jugadores
		{
			this.vistaNuevaPartida.choNumeroJugadores.select(0); // Reseteamos el desplegable
			this.vistaNuevaPartida.removeAll();
			this.vistaNuevaPartida.OcultarDialogNombresJugadores();
		}
		else if(this.vistaNuevaPartida.dlgMensajeFaltanNombres.isActive()) // Cerrar mensaje error nombre en blanco
		{
			this.vistaNuevaPartida.OcultarMensajeError();
			this.vistaNuevaPartida.txfNombre1.requestFocus();
		}
		else if(this.vistaJugando.isActive()) // Ocultar menu y mostrar tapete de juego
		{
			this.vistaJugando.OcultarJugando();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(this.vistaJugando.dlgMensajeComienzoPartida.isActive()) // Cerrar aviso partida empezada
		{
			this.vistaJugando.dlgMensajeComienzoPartida.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorTirada.isActive()) // Cerrar aviso valor tirada real
		{
			this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorAnunciado.isActive()) // Cerrar aviso valor anunciado
		{
			this.vistaJugando.dlgMensajeValorAnunciado.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorRecibido.isActive()) // Cerrar aviso superar tirada o levantar cubilete
		{
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorAceptado.isActive()) // Cerrar aviso superar tirada
		{
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorRechazado.isActive()) // Cerrar aviso cubilete levantado
		{
			this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeFinPartida.isActive()) // Cerrar aviso partida finalizada
		{
			this.vistaJugando.dlgMensajeFinPartida.setVisible(false);
		}
		else // Cerrar programa desde el menu
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

	@Override
	public void mouseClicked(MouseEvent click)
	{
		int x = click.getX();
		int y = click.getY();
		
		// Lanzamiento de dados al hacer click en la imagen del cubilete
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (controlTurno == true))
		{			
			tiradaDado1 = this.modelo.tirada();
			tiradaDado2 = this.modelo.tirada();
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);

			if(valorTirada.equals("Kiriki"))
			{
				this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
				this.vistaJugando.lblMensajeKiriki.setText("¡Kiriki! El siguiente jugador pierde una vida automáticamente");
				this.vistaJugando.dlgMensajeKiriki.setVisible(true);
			}
			else
			{
				this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
				this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
				this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
				this.vistaJugando.cambiarCubilete(1);
				this.vistaJugando.recuperarCubilete(1);
				this.vistaJugando.sonidoDados();
				controlTurno = false;
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{			
		
		
	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
	}
}