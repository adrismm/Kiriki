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
	
	int numJugadores;
	int turno = 1;
	int tiradaDado1;
	int tiradaDado2;
	String valorTirada;
	int tiradasAmarillo = 0;
	int tiradasAzul = 0;
	int tiradasVerde = 0;
	int tiradasRojo = 0;
	boolean controlTurno = false;
	int valorAnunciado;
	
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
		vj.dlgMensajeValorRecibido.addWindowListener(this);
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
					this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				 	this.vistaJugando.MostrarJugando();
					this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
					this.vistaNuevaPartida.OcultarDialogNombresJugadores();
					

			}
			else if((this.vistaNuevaPartida.numJugadores == 3) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre3.getText().equals("")))
			{
				this.vistaJugando = new Jugando(3, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), "");
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				
			}
			else if((this.vistaNuevaPartida.numJugadores == 2) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals("")))
			{
				this.vistaJugando = new Jugando(2, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), "", "");
				this.vistaJugando.addWindowListener(this);
				this.vistaJugando.addMouseListener(this);
				this.vistaJugando.dlgMensajeValorTirada.addWindowListener(this);
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
				this.vistaNuevaPartida.OcultarDialogNombresJugadores();
				
			}
			else
			{
				this.vistaNuevaPartida.MensajeErrorFaltanNombres();
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
			System.out.println("Cierrate joder");
		}
		else if(this.vistaJugando.dlgMensajeValorTirada.isActive())
		{
			this.vistaJugando.dlgMensajeValorTirada.setVisible(false);
			this.vistaJugando.MostrarJugando();
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
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 1))
			{
				controlTurno = false;
				tiradaDado1 = this.modelo.tirada();
				tiradaDado2 = this.modelo.tirada();
				valorTirada = this.modelo.calcularValorTirada(tiradaDado1, tiradaDado2);
				this.vistaJugando.lblMensajeValorTirada.setText("Has obtenido un/a: " + valorTirada);
				this.vistaJugando.lblMensajeAnunciarValor.setText("Elige uno de los siguientes valores a anunciar: ");
				this.vistaJugando.dlgMensajeValorTirada.setVisible(true);
				this.vistaJugando.cargarDados();
				this.vistaJugando.mostrarDadoCubiletes(tiradaDado1, tiradaDado2);
				//turno = 2;
				if(turno == 2)
				{
					this.vistaJugando.actualizarTurno(+1);
				}
				System.out.println("Jugador 2 �destapas el cubilete o superas la tirada?");
			}
		
		if(click.getSource().equals(this.vistaJugando) && (x>320 && x<520) && (y>190 && y<390) && (turno == 1))
		{
			if(controlTurno == false)
			{
				boolean controlMentira = false;
				System.out.println("El jugador 1 cree que el jugador anterior miente y destapa el cubilete");
				for(int i = 0; i < tiradaDado1; i++)
				{
					if(valorAnunciado != tiradaDado1 + tiradaDado2)
					{
						controlMentira = true;
					}
				}
				
				if(controlMentira == false)
				{
					
				}
			}
		}
		
		// Pulsamos sobre el cubilete
	/*	if((x >= 33) && (x <= 73) && (y >= 217) && (y <= 277))
		{
			tirada = this.modelo.tirada(); // agitar cubilete, realizar tirada dados
			this.vistaJugando.mostrarTirada(tirada); //Mostrar tirada dados
			turno = 1;
			
			if(true) // Analizar tirada
			{
				switch(turno) // Actualizar posici�n
				{
					case 1:
						this.vistaJugando.xAmarilla = this.vistaJugando.xAmarilla + tirada * 38;
						tiradasAmarillo++;
						
						if((this.vistaJugando.xAmarilla >= 444) && (this.vistaJugando.yAmarilla <= 38))
						{
							System.out.println("GANA AMARILLA!");
						}
						else if((this.vistaJugando.xAmarilla == 290) && (this.vistaJugando.yAmarilla == 380))
						{
							this.vistaJugando.xAmarilla = 176;
							this.vistaJugando.yAmarilla = 266;
						}
						else if(this.vistaJugando.xAmarilla > 420)
						{
							this.vistaJugando.xAmarilla = 140;
							this.vistaJugando.yAmarilla = this.vistaJugando.yAmarilla - 38;
						}
						break;
					case 2:
						this.vistaJugando.xAzul = this.vistaJugando.xAzul + tirada * 38;
						tiradasAzul++;
						
						if((this.vistaJugando.xAzul >= 444) && (this.vistaJugando.yAzul <= 38))
						{
							System.out.println("GANA AZUL!");
						}
						else if((this.vistaJugando.xAzul == 290) && (this.vistaJugando.yAzul == 380))
						{
							this.vistaJugando.xAzul = 176;
							this.vistaJugando.yAzul = 266;
						}
						else if(this.vistaJugando.xAzul > 420)
						{
							this.vistaJugando.xAzul = 140;
							this.vistaJugando.yAzul = this.vistaJugando.yAzul - 38;
						}
						break;
					case 3:
						this.vistaJugando.xVerde = this.vistaJugando.xVerde + tirada * 38;
						tiradasVerde++;
						
						if((this.vistaJugando.xVerde >= 444) && (this.vistaJugando.yVerde <= 38))
						{
							System.out.println("GANA VERDE!");
						}
						else if((this.vistaJugando.xVerde == 290) && (this.vistaJugando.yVerde == 380))
						{
							this.vistaJugando.xVerde = 176;
							this.vistaJugando.yVerde = 266;
						}
						else if(this.vistaJugando.xVerde > 420)
						{
							this.vistaJugando.xVerde = 140;
							this.vistaJugando.yVerde = this.vistaJugando.yVerde - 38;
						}
						break;
					case 4:
						this.vistaJugando.xRoja = this.vistaJugando.xRoja + tirada * 38;
						tiradasRojo++;
						
						if((this.vistaJugando.xRoja >= 444) && (this.vistaJugando.yRoja <= 38))
						{
							System.out.println("GANA ROJA!");
						}
						else if((this.vistaJugando.xRoja == 290) && (this.vistaJugando.yRoja == 380))
						{
							this.vistaJugando.xRoja = 176;
							this.vistaJugando.yRoja = 266;
						}
						else if(this.vistaJugando.xRoja > 420)
						{
							this.vistaJugando.xRoja = 140;
							this.vistaJugando.yRoja = this.vistaJugando.yRoja - 38;
						}
						break;
				}
				
				System.out.println("Amarilla: " + this.vistaJugando.xAmarilla + "-" + this.vistaJugando.yAmarilla);
				System.out.println("Azul: " + this.vistaJugando.xAzul + "-" + this.vistaJugando.yAzul);
				System.out.println("Verde: " + this.vistaJugando.xVerde + "-" + this.vistaJugando.yVerde);
				System.out.println("Roja: " + this.vistaJugando.xRoja + "-" + this.vistaJugando.yRoja);
				turno++;
				
				if(turno > numJugadores)
				{
					turno = 1;
				}
				
				this.vistaJugando.actualizarTurno(turno);
			} 
		} */
		
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
		// TODO Auto-generated method stub
		
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