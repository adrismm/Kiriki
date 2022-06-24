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
		vj.dlgMensajeValorVerdadero.addWindowListener(this);
		vj.dlgMensajeValorFalso.addWindowListener(this);
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
		
		else if(botonPulsado.equals(this.vistaRanking.btnVolver)) //Gestionar el bot�n "Volver" de la nueva ventana Ranking
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
			if(!this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem().equals("Elegir n�mero de jugadores...")) //Este m�todo prepara el contenido del di�logo en funci�n de este valor pasado y muestra dicho di�logo
			{
				this.vistaNuevaPartida.PrepararDialogNombresJugadores(Integer.parseInt(this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnComenzarPartida)) //Si ha pulsado el bot�n "Comenzar Partida" del di�logo anterior, ya una vez escritos los nombres de los jugadores
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
					this.vistaJugando.btnAceptarValor.addActionListener(this);
					this.vistaJugando.btnRechazarValor.addActionListener(this);
					this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorVerdadero.addWindowListener(this);
					this.vistaJugando.dlgMensajeValorFalso.addWindowListener(this);
				 	this.vistaJugando.MostrarJugando();
					this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
					this.vistaNuevaPartida.OcultarDialogNombresJugadores();
					this.vistaJugando.lblMensajeComienzoPartida.setText("�La partida ha comenzado!");
					this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
					

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
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorVerdadero.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorFalso.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("�La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				
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
				this.vistaJugando.dlgMensajeValorRecibido.addWindowListener(this);
				this.vistaJugando.btnAceptarValor.addActionListener(this);
				this.vistaJugando.btnRechazarValor.addActionListener(this);
				this.vistaJugando.dlgMensajeValorAceptado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorRechazado.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorVerdadero.addWindowListener(this);
				this.vistaJugando.dlgMensajeValorFalso.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				this.vistaJugando.lblMensajeComienzoPartida.setText("�La partida ha comenzado!");
				this.vistaJugando.dlgMensajeComienzoPartida.setVisible(true);
				
			}
			else
			{
				this.vistaNuevaPartida.MensajeErrorFaltanNombres();
			}
		}
		else if(botonPulsado.equals(this.vistaJugando.btnAnunciarValor))
		{
			if(!this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().equals(null)) // if(chkgrValorTirada.isSelected())
			{
				this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
				this.vistaJugando.mostrarValorAnunciado(this.vistaJugando.chkgrValorTirada.getSelectedCheckbox().getLabel());
			}
			
			if(this.vistaJugando.turnoJugador == 1)
			{
				this.vistaJugando.actualizarTurno(2);
			}
			else if(this.vistaJugando.turnoJugador == 2)
			{
				this.vistaJugando.actualizarTurno(3);
			}
			else if(this.vistaJugando.turnoJugador == 3)
			{
				this.vistaJugando.actualizarTurno(4);
			}
			else if(this.vistaJugando.turnoJugador == 4)
			{
				this.vistaJugando.actualizarTurno(1);
			}
			
			this.vistaJugando.cambiarCubilete(0);
			this.vistaJugando.recuperarCubilete(0);
		}
		else if(botonPulsado.equals(this.vistaJugando.btnAceptarValor))
		{
			controlTurno = true;
			this.vistaJugando.dlgMensajeValorRecibido.setVisible(false);
			this.vistaJugando.lblMensajeValorAceptado.setText("Intenta superar la tirada, vuelve a tirar los dados �Suerte!");
			this.vistaJugando.dlgMensajeValorAceptado.setVisible(true);
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
			this.modelo.compararValores(valorTirada, valorAnunciado);
			
			/*if(controlMentira == true)
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorFalso.setText("�El jugador anterior ha mentido y pierda 1 vida!"); // jugadorAnterior ha mentido
				this.vistaJugando.dlgMensajeValorFalso.setVisible(true);
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText("�El jugador anterior dec�a la verdad y t� pierdes 1 vida!"); // jugadorAnterior + jugadorActual
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
			}*/
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
		else if(this.vistaJugando.dlgMensajeValorVerdadero.isActive())
		{
			this.vistaJugando.dlgMensajeValorVerdadero.setVisible(false);
		}
		else if(this.vistaJugando.dlgMensajeValorFalso.isActive())
		{
			this.vistaJugando.dlgMensajeValorFalso.setVisible(false);
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
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 1) && (controlTurno == true))
		{
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
			turno = 2;
			if(turno == 2)
			{
				this.vistaJugando.actualizarTurno(2);
			}
		}
		
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
			}
			else
			{
				this.vistaJugando.dlgMensajeValorRechazado.setVisible(false);
				this.vistaJugando.lblMensajeValorVerdadero.setText(this.vistaJugando.jugador4 + " dec�a la verdad y " + this.vistaJugando.jugador1 + " pierde 1 vida!");
				this.vistaJugando.dlgMensajeValorVerdadero.setVisible(true);
				
				controlTurno = true;
				turno = 2;
				this.vistaJugando.actualizarTurno(2);
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