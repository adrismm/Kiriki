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
	Modelo modelo;
	MenuInicio vistaMenuInicio;
	NuevaPartida vistaNuevaPartida; // Partida nueva
	Ranking vistaRanking; // Top 10 jugadores
	Jugando vistaJugando; // Tapete de juego
	
	int turno = 1;
	int tiradaDado1;
	int tiradaDado2;
	int voltearCubilete;


	
	
	String valorTirada;
	String valorAnunciado;
	
	boolean controlTurno = true;
	boolean controlMentira = false;
	
	Connection conexion = null;
	
	public Controlador(Modelo m, MenuInicio vmi, NuevaPartida vnp, Ranking vr, Jugando vj)
	{
		this.modelo = m;
		this.vistaMenuInicio = vmi;
		this.vistaNuevaPartida = vnp;
		this.vistaRanking = vr;
		this.vistaJugando = vj;
		
		vmi.addWindowListener(this);
		vmi.btnNuevaPartida.addActionListener(this);	
		vmi.btnRanking.addActionListener(this);
		vmi.btnSalir.addActionListener(this);
		vmi.btnAyuda.addActionListener(this);
		vmi.addMouseListener(this);
		
		vr.addWindowListener(this);
		vr.btnVolver.addActionListener(this);
		
		vnp.pedirNumeroJugadores.addWindowListener(this);
		vnp.btnContinuar.addActionListener(this);
		vnp.pedirNombresJugadores.addWindowListener(this);
		vnp.btnComenzarPartida.addActionListener(this);
		vnp.dlgMensajeFaltanNombres.addWindowListener(this);
	
		vj.dlgMensajeComienzoPartida.addWindowListener(this);
		vj.dlgMensajeValorTirada.addWindowListener(this);
		vj.dlgMensajeValorAnunciado.addWindowListener(this);
		vj.dlgMensajeValorAceptado.addWindowListener(this);
		vj.dlgMensajeValorRechazado.addWindowListener(this);
		vj.dlgMensajeKiriki.addWindowListener(this);
		vj.dlgMensajeFinPartida.addWindowListener(this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object botonPulsado = arg0.getSource();
		
		if(botonPulsado.equals(this.vistaMenuInicio.btnSalir))
		{
			System.exit(0);
		}
		if(botonPulsado.equals(this.vistaMenuInicio.btnRanking)) //Ejecutar al pulsar el botón Ranking
		{
			this.vistaRanking.MostrarRanking(); //Muestra la ventana
			this.vistaMenuInicio.OcultarInicio();
			
			conexion = this.modelo.conectar();// Conectar con la base de datos
			//this.vistaRanking.listadoJugadores.append(consulta);
			
			String cogerRanking = this.modelo.mejoresJugadores(conexion);
			String[] completarRanking = cogerRanking.split("/");
			
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
		
		//Lanzar ventana de AYUDA al pulsar el botón de ayuda 
		if(botonPulsado.equals(this.vistaMenuInicio.btnAyuda))
			{
				this.modelo.ayuda(conexion);
				this.modelo.desconectar(conexion);
			}
		
		else if(botonPulsado.equals(this.vistaRanking.btnVolver)) //Gestionar el botón "Volver" de la nueva ventana Ranking
		{
			this.vistaRanking.OcultarRanking();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(botonPulsado.equals(this.vistaMenuInicio.btnNuevaPartida)) //Al pulsar el bot�n "Nueva Partida", mostrar el di�logo para pedir el n�mero de jugadores mediante un desplegable
		{
			this.vistaNuevaPartida.MostrarDialogNumeroJugadores();
			this.vistaMenuInicio.OcultarInicio();
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnContinuar)) //Si ha pulsado el bot�n "Continuar" del di�logo anterior, se llama al m�todo que prepara el siguiente di�logo pas�ndole como par�metro el n�mero de jugadores
		{
			if(!this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem().equals("Elegir número de jugadores...")) //Este m�todo prepara el contenido del di�logo en funci�n de este valor pasado y muestra dicho di�logo
			{
				this.vistaNuevaPartida.PrepararDialogNombresJugadores(Integer.parseInt(this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnComenzarPartida)) //Si ha pulsado el botón "Comenzar Partida" del diálogo anterior, ya una vez escritos los nombres de los jugadores
		{			
			if((this.vistaNuevaPartida.numJugadores == 4) && (!this.vistaNuevaPartida.txfNombre1.getText().equals("")) //Si se queda alg�n nombre en blanco no se puede comenzar la partida
				&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre3.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre4.getText().equals("")))
			{
					this.vistaJugando = new Jugando(4, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), this.vistaNuevaPartida.txfNombre4.getText());
					this.vistaJugando.addWindowListener(this);
					this.vistaJugando.addMouseListener(this);
					this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
					this.vistaJugando.dlgMensajeTurno.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
					this.vistaJugando.btnAnunciarValor.addActionListener(this);
					this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
					this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
					this.vistaJugando.btnAceptarValor.addActionListener(this);
					this.vistaJugando.btnRechazarValor.addActionListener(this);
					this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				 	this.vistaJugando.MostrarJugando();
					this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
					this.vistaNuevaPartida.OcultarDialogNombresJugadores();
					this.vistaJugando.lblMensajeComienzoPartida.setText("¡La partida ha comenzado!");
					this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
					this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
					this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
					this.vistaJugando.btnMensajeKiriki.addActionListener(this);
					this.vistaJugando.btnMensajeTurno.addActionListener(this);
					this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
					this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
					this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
					this.vistaJugando.vidasJugador1 = 3;
					this.vistaJugando.vidasJugador2 = 3;
					this.vistaJugando.vidasJugador3 = 3;
					this.vistaJugando.vidasJugador4 = 3;

			}
			else if((this.vistaNuevaPartida.numJugadores == 3) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre3.getText().equals("")))
			{
				this.vistaJugando = new Jugando(3, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), "");
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
				this.vistaJugando.dlgMensajeTurno.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.btnAnunciarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
				this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("¡La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
				this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
				this.vistaJugando.btnMensajeKiriki.addActionListener(this);
				this.vistaJugando.btnMensajeTurno.addActionListener(this);
				this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
				this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
				this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
				this.vistaJugando.vidasJugador1 = 3;
				this.vistaJugando.vidasJugador2 = 3;
				this.vistaJugando.vidasJugador3 = 3;
				
			
				
			}
			else if((this.vistaNuevaPartida.numJugadores == 2) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals("")))
			{
				this.vistaJugando = new Jugando(2, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), "", "");
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeComienzoPartida.addWindowListener(this);
				this.vistaJugando.dlgMensajeTurno.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.btnAnunciarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAnunciado.addWindowListener(this);
				this.vistaJugando.dlgMensajeKiriki.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("�La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				this.vistaJugando.btnMensajeComienzoPartida.addActionListener(this);
				this.vistaJugando.btnMensajeFinPartida.addActionListener(this);
				this.vistaJugando.btnMensajeKiriki.addActionListener(this);
				this.vistaJugando.btnMensajeTurno.addActionListener(this);
				this.vistaJugando.btnMensajeValorRechazado.addActionListener(this);
				this.vistaJugando.btnMensajeValorAnunciado.addActionListener(this);
				this.vistaJugando.btnMensajeValorAceptado.addActionListener(this);
				this.vistaJugando.vidasJugador1 = 3;
				this.vistaJugando.vidasJugador2 = 3;
				
				
			}
			else
			{
				this.vistaNuevaPartida.MensajeErrorFaltanNombres();
			}
		}
		
		if(botonPulsado.equals(this.vistaJugando.btnMensajeComienzoPartida))
		{
			this.vistaJugando.dlgMensajeComienzoPartida.setVisible(false);
		}
		
		else if(botonPulsado.equals(this.vistaJugando.btnAnunciarValor))
		{

			if(!this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().equals(null)) // if(chkgrValorTirada.isSelected())
			{
				this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
				this.vistaJugando.mostrarValorAnunciado(this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().getLabel());
				this.vistaJugando.dlgMensajeValorAnunciado.setVisible(true);
			}
			
			this.vistaJugando.cambiarCubilete(0);
			this.vistaJugando.recuperarCubilete(0);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorAnunciado))
		{
			this.vistaJugando.dlgMensajeValorAnunciado.setVisible(false);

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
			turno = turno + 1;
			this.vistaJugando.actualizarTurno(turno);
			this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador1 + ", ¿intentarás superar la tirada anterior o destaparás los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnAceptarValor))
		{
			controlTurno = true;
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
			this.vistaJugando.lblMensajeValorAceptado.setText("Intenta superar la tirada, vuelve a tirar los dados ¡Suerte!");
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(true);
			this.vistaJugando.chkgrValorTirada.setSelectedCheckbox(null);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnRechazarValor))
		{
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
			this.vistaJugando.lblMensajeValorRechazado.setText("Y la tirada anterior es realmente un/a... " + valorTirada); //this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
			this.vistaJugando.dlgMensajeValorRechazado.setVisible(true);
			this.vistaJugando.cargarDados();
			this.vistaJugando.mostrarDadoCubiletes(tiradaDado1, tiradaDado2);
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
			valorAnunciado = this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().getLabel();
			controlMentira = this.modelo.compararValores(valorTirada, valorAnunciado);
			
			if(controlMentira == true)
			{
				this.vistaJugando.lblMensajeValorFalso.setText("¡El jugador anterior ha mentido y pierde 1 vida!"); // jugadorAnterior ha mentido
				this.vistaJugando.lblMensajeValorFalso1.setText("Tú sigues la partida, vuelve a tirar"); // jugadorAnterior ha mentido
				this.vistaJugando.lblMensajeValorVerdadero.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero1.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setVisible(true);
				this.vistaJugando.lblMensajeValorFalso1.setVisible(true);
				controlTurno = true;
				turno = turno -1; 
				if(turno == 1)
				{
					this.vistaJugando.vidasJugador1 --;
				}
				else if(turno == 2)
				{
					this.vistaJugando.vidasJugador2 --;
				}
				else if(turno == 3)
				{
					this.vistaJugando.vidasJugador3 --;
				}
				else if(turno == 4)
				{
					this.vistaJugando.vidasJugador4 --;
				}
			}
			else if(controlMentira == false)
			{
				this.vistaJugando.lblMensajeValorVerdadero.setText("¡El jugador anterior decía la verdad y tú pierdes 1 vida!"); // jugadorAnterior + jugadorActual
				this.vistaJugando.lblMensajeValorVerdadero1.setText("El siguiente jugador continua la partida, él tira"); // jugadorAnterior + jugadorActual
				this.vistaJugando.lblMensajeValorVerdadero.setVisible(true);
				this.vistaJugando.lblMensajeValorVerdadero1.setVisible(true);
				this.vistaJugando.lblMensajeValorFalso.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso1.setVisible(false);
				this.vistaJugando.chkgrValorTirada.setSelectedCheckbox(null);
				controlTurno = true;
				if(turno == 1)
				{
					this.vistaJugando.vidasJugador1 --;
				}
				else if(turno == 2)
				{
					this.vistaJugando.vidasJugador2 --;
				}
				else if(turno == 3)
				{
					this.vistaJugando.vidasJugador3 --;
				}
				else if(turno == 4)
				{
					this.vistaJugando.vidasJugador4 --;
				}
			}
		}
		
		//turno jugador 1
			//comiezo de la ronda
		if(this.vistaJugando.dlgMensajeValorTirada.isActive() && (turno == 1) && (controlTurno == true))
			{
				turno = 2;
				this.vistaJugando.actualizarTurno(2);
				System.out.println("tu puta madre 1");
			}
		// cuando vienes de un turno de otro jugador y tienes que decidir si levantar o volver a tirar
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorRechazado))
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
			turno = turno + 1;
			this.vistaJugando.actualizarTurno(turno);
			
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeValorAceptado))
		{
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(false);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnMensajeKiriki))
		{
			System.out.println("hola");
			this.vistaJugando.dlgMensajeKiriki.setVisible(false);
			turno = turno + 1;
			if(turno == 1)
			{
				this.vistaJugando.vidasJugador1 --;
			}
			else if(turno == 2)
			{
				System.out.println("hola2");
				this.vistaJugando.vidasJugador2 --;
			}
			else if(turno == 3)
			{
				this.vistaJugando.vidasJugador3 --;
			}
			else if(turno == 4)
			{
				this.vistaJugando.vidasJugador4 --;
			}
			
			if(this.vistaJugando.numJugadores == 4)
			{
				this.modelo.finPartida(this.vistaJugando.vidasJugador1, this.vistaJugando.vidasJugador2, this.vistaJugando.vidasJugador3, this.vistaJugando.vidasJugador4);
				this.vistaJugando.lblMensajeFinPartida.setText("La partida ha terminado y ha ganado: ");
				this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
			}
			
			turno = turno + 1;
			
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
			this.vistaJugando.actualizarTurno(turno);
		}
		
		
		
		
	}

		//turno jugador 1
		/*
		if(this.vistaJugando.dlgMensajeValorTirada.isActive() && (turno == 1) && (controlTurno == false))
		{
			
			if(controlMentira == true) //si levantas compara los valores
				{
					this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
					System.out.println("tu puta madre 3");
					if(this.vistaJugando.numJugadores == 4)
						{
							this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador4 + " ha mentido y pierda 1 vida!");
							this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
						}
					else if(this.vistaJugando.numJugadores == 3)
						{
							this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador3 + " ha mentido y pierda 1 vida!");
							this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
						}
					else if(this.vistaJugando.numJugadores == 2)
						{
						System.out.println("tu puta madre 4");
							this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador2 + " ha mentido y pierda 1 vida!");
							this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
						}
					
					controlTurno = true;
					turno = 1;
				}
			else 
				{
					System.out.println("tu puta madre 5");
					this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
					this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador4 + " decía la verdad y " + this.vistaJugando.jugador1 + " pierde 1 vida!");
					this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
							
					controlTurno = true;
					turno = 2;
					this.vistaJugando.actualizarTurno(2);
				}
				
		}
		*/
		/*
		//turno jugador 2
		if(this.vistaJugando.dlgMensajeValorTirada.isActive() && (turno == 2) && (controlTurno == true))
			{
				controlTurno = false;
				tiradaDado1 = this.modelo.tirada();
				tiradaDado2 = this.modelo.tirada();
				valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
				this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
				this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
				this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
				this.vistaJugando.cambiarCubilete(1);
				this.vistaJugando.recuperarCubilete(1);
				this.vistaJugando.sonidoDados();
					
				controlTurno = false;
				turno = 3;
				this.vistaJugando.actualizarTurno(3);
				System.out.println("tu puta madre 6");
			}
		
		if(((turno == 2) && (controlTurno == false)))
			{
				boolean controlMentira = false;
				this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador2 + ", ¿intentarás superar la tirada anterior o destapar�s los dados?");
				this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
				System.out.println("tu puta madre 7");
				if(controlMentira == true)
					{
						this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
						this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador1 + " ha mentido y pierda 1 vida!");
						
						
						controlTurno = true;
						turno = 2;
						System.out.println("tu puta madre 8");
					}
				else
					{
						this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
						this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador1 + " dec�a la verdad y " + this.vistaJugando.jugador2 + " pierde 1 vida!");
						
						
						controlTurno = true;
						turno = 3;
						this.vistaJugando.actualizarTurno(3);
						System.out.println("tu puta madre 9");
					}
			}
		
	}*/

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
		if(this.vistaRanking.isActive()) //Cerrar ventana Ranking
		{
			this.vistaRanking.OcultarRanking();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(this.vistaNuevaPartida.pedirNumeroJugadores.isActive()) //Cerrar ventana NuevaPartida pidiendo n�mero jugadores
		{
			this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
			this.vistaMenuInicio.MostrarInicio(); 
		}
		else if(this.vistaNuevaPartida.pedirNombresJugadores.isActive()) //Cerrar ventana NuevaPartida pidiendo nombres jugadores
		{
			this.vistaNuevaPartida.choNumeroJugadores.select(0); //Reseteamos el desplegable
			this.vistaNuevaPartida.removeAll();
			this.vistaNuevaPartida.OcultarDialogNombresJugadores();
		}
		else if(this.vistaNuevaPartida.dlgMensajeFaltanNombres.isActive())
		{
			this.vistaNuevaPartida.OcultarMensajeError();
			this.vistaNuevaPartida.txfNombre1.requestFocus();
		}
		else if(this.vistaJugando.isActive()) // this.vistaJugando != null
		{
			this.vistaJugando.OcultarJugando();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(this.vistaJugando.dlgMensajeComienzoPartida.isActive())
		{
			this.vistaJugando.dlgMensajeComienzoPartida.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeTurno.isActive())
		{
			this.vistaJugando.dlgMensajeTurno.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorTirada.isActive())
		{
			this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorAnunciado.isActive())
		{
			this.vistaJugando.dlgMensajeValorAnunciado.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorRecibido.isActive())
		{
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorAceptado.isActive())
		{
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorRechazado.isActive())
		{
			this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
		}
		
		/* else if(this.vistaJugando.dlgMensajeFinPartida.isActive())
		{
			this.vistaJugando.dlgMensajeFinPartida.setVisible(false);
			
			// Reinicio
			vidasActualesJugador1 = 10;
			vidasActualesJugador2 = 10;
			this.vistaJugando.resetearContadores();
			turno = 0;
			this.vistaJugando.esconderDados();
			this.modelo.agitar(cubilete);
		}
		else if(this.vistaJugando.dlgMensajeRonda.isActive())
		{
			this.vistaJugando.dlgMensajeRonda.setvisible(false);
		} */
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

	@Override
	public void mouseClicked(MouseEvent click)
	{
		int x = click.getX();
		int y = click.getY();
		
		// Turno Jugador 1
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
		/*
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 1) && (controlTurno == false))
		{
			boolean controlMentira = false;
			this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador1 + ", �intentar�s superar la tirada anterior o destapar�s los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
			
			if(controlMentira == true)
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				
				if(this.vistaJugando.numJugadores == 4)
				{
					this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador4 + " ha mentido y pierda 1 vida!");
					this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				}
				else if(this.vistaJugando.numJugadores == 3)
				{
					this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador3 + " ha mentido y pierda 1 vida!");
					this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				}
				else if(this.vistaJugando.numJugadores == 2)
				{
					this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador2 + " ha mentido y pierda 1 vida!");
					this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				}
				
				controlTurno = true;
				turno = 1;
				System.out.println("estoy haciendo lo que me da la gana 2");
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador4 + " dec�a la verdad y " + this.vistaJugando.jugador1 + " pierde 1 vida!");
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
				
				controlTurno = true;
				turno = 2;
				this.vistaJugando.actualizarTurno(2);
				System.out.println("estoy haciendo lo que me da la gana 3");
			}
			
		}
		
		// Turno jugador 2
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 2) && (controlTurno == true))
		{
			controlTurno = false;
			tiradaDado1 = this.modelo.tirada();
			tiradaDado2 = this.modelo.tirada();
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
			this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
			this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
			this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
			this.vistaJugando.cambiarCubilete(1);
			this.vistaJugando.recuperarCubilete(1);
			this.vistaJugando.sonidoDados();
				
			controlTurno = false;
			turno = 3;
			if(turno == 3)
			{
				this.vistaJugando.actualizarTurno(3);
			}
		}
		
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 2) && (controlTurno == false))
		{
			boolean controlMentira = false;
			this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador2 + ", �intentar�s superar la tirada anterior o destapar�s los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
			
			if(controlMentira == true)
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador1 + " ha mentido y pierda 1 vida!");
				this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				
				controlTurno = true;
				turno = 2;
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador1 + " dec�a la verdad y " + this.vistaJugando.jugador2 + " pierde 1 vida!");
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
				
				controlTurno = true;
				turno = 3;
				this.vistaJugando.actualizarTurno(3);
			}
		}
		
		// Turno jugador 3
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 3) && (controlTurno == true))
		{
			controlTurno = false;
			tiradaDado1 = this.modelo.tirada();
			tiradaDado2 = this.modelo.tirada();
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
			this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
			this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
			this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
			this.vistaJugando.cambiarCubilete(1);
			this.vistaJugando.recuperarCubilete(1);
			this.vistaJugando.sonidoDados();
				
			controlTurno = false;
			turno = 4;
			if(turno == 4)
			{
				this.vistaJugando.actualizarTurno(4);
			}
		}
		
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 3) && (controlTurno == false))
		{
			boolean controlMentira = false;
			this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador3 + ", �intentar�s superar la tirada anterior o destapar�s los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
			
			if(controlMentira == true)
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador2 + " ha mentido y pierda 1 vida!");
				this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				
				controlTurno = true;
				turno = 3;
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador2 + " dec�a la verdad y " + this.vistaJugando.jugador3 + " pierde 1 vida!");
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
				
				controlTurno = true;
				turno = 4;
				this.vistaJugando.actualizarTurno(4);
			}
		}
		
		// Turno jugador 4
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 4) && (controlTurno == true))
		{
			controlTurno = false;
			tiradaDado1 = this.modelo.tirada();
			tiradaDado2 = this.modelo.tirada();
			valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
			this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
			this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
			this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
			this.vistaJugando.cambiarCubilete(1);
			this.vistaJugando.recuperarCubilete(1);
			this.vistaJugando.sonidoDados();
				
			controlTurno = false;
			turno = 1;
			if(turno == 1)
			{
				this.vistaJugando.actualizarTurno(1);
			}
		}
		
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 4) && (controlTurno == false))
		{
			boolean controlMentira = false;
			this.vistaJugando.lblMensajeValorRecibido.setText(this.vistaJugando.jugador4 + ", �intentar�s superar la tirada anterior o destapar�s los dados?");
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(true);
			
			if(controlMentira == true)
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setText(this.vistaJugando.jugador3 + " ha mentido y pierda 1 vida!");
				this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
				
				controlTurno = true;
				turno = 4;
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador3 + " dec�a la verdad y " + this.vistaJugando.jugador4 + " pierde 1 vida!");
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
				
				controlTurno = true;
				turno = 1;
				this.vistaJugando.actualizarTurno(1);
			}
		}
		*/
	/*	if((vidasJugador1 > 0) && (vidasJugador2 = 0))
		{
			this.vistaJugando.lblMensajeFinPartida.setText(jugador1 + "ha ganado!"); // Gana el jugador 1
			this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
		}
		else if((vidasJugador2 > 0) && (vidasJugador1 = 0))
		{
			this.vistaJugando.lblMensajeFinPartida.setText(jugador2 + "ha ganado!"); // Gana el jugador 2
			this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
		}
		else if((vidasJugador3 > 0) && (vidasJugador1 = 0) && (vidasJugador2 = 0))
		{
			this.vistaJugando.lblMensajeFinPartida.setText(jugador3 + "ha ganado!"); // Gana el jugador 3
			this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
		}
		else if((vidasJugador4 > 0) && (vidasJugador1 = 0) && (vidasJugador2 = 0) && (vidasJugador3 = 0))
		{
			this.vistaJugando.lblMensajeFinPartida.setText(jugador4 + "ha ganado!"); // Gana el jugador 4
			this.vistaJugando.dlgMensajeFinPartida.setVisible(true);
		} */
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