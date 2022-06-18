package es.studium.Kiriki;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controlador implements WindowListener, ActionListener, MouseListener
{
	Modelo modelo;
	MenuInicio vistaMenuInicio;
	NuevaPartida vistaNuevaPartida; // Partida nueva
	Ranking vistaRanking; // Top 10 jugadores
	Jugando vistaJugando; // Tapete de juego
	
	Ayuda va;
	
	int numJugadores;
	int turno = 1;
	int tirada;
	int tiradasAmarillo = 0;
	int tiradasAzul = 0;
	int tiradasVerde = 0;
	int tiradasRojo = 0;
	
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
		
		vr.addWindowListener(this);
		vr.btnVolver.addActionListener(this);
		
		vnp.pedirNumeroJugadores.addWindowListener(this);
		vnp.btnContinuar.addActionListener(this);
		vnp.pedirNombresJugadores.addWindowListener(this);
		vnp.btnComenzarPartida.addActionListener(this);
		
		this.vistaJugando.addWindowListener(this);
		this.vistaJugando.addMouseListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		Object botonPulsado = arg0.getSource();
		
		if(botonPulsado.equals(this.vistaMenuInicio.btnSalir))
		{
			System.exit(0);
		}
		else if(botonPulsado.equals(this.vistaMenuInicio.btnRanking)) //Ejecutar al pulsar el botón "Ranking"
		{
			this.vistaRanking.MostrarRanking(); //Muestra la ventana
			this.vistaMenuInicio.OcultarInicio(); 
		}
		else if(botonPulsado.equals(this.vistaRanking.btnVolver)) //Gestionar el botón "Volver" de la nueva ventana Ranking
		{
			this.vistaRanking.OcultarRanking();
			this.vistaMenuInicio.MostrarInicio();
		}
		else if(botonPulsado.equals(this.vistaMenuInicio.btnNuevaPartida)) //Al pulsar el botón "Nueva Partida", mostrar el diálogo para pedir el número de jugadores mediante un desplegable
		{
			this.vistaNuevaPartida.MostrarDialogNumeroJugadores();
			this.vistaMenuInicio.OcultarInicio();
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnContinuar)) //Si ha pulsado el botón "Continuar" del diálogo anterior, se llama al método que prepara el siguiente diálogo pasándole como parámetro el número de jugadores
		{
			if(!this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem().equals("Elegir número de jugadores...")) //Este método prepara el contenido del diálogo en función de este valor pasado y muestra dicho diálogo
			{
				this.vistaNuevaPartida.PrepararDialogNombresJugadores(Integer.parseInt(this.vistaNuevaPartida.choNumeroJugadores.getSelectedItem()));
			}
		}
		else if(botonPulsado.equals(this.vistaNuevaPartida.btnComenzarPartida)) //Si ha pulsado el botón "Comenzar Partida" del diálogo anterior, ya una vez escritos los nombres de los jugadores
		{
			if((numJugadores == 4) && (!this.vistaNuevaPartida.txfNombre1.getText().equals("")) //Si se queda algún nombre en blanco no se puede comenzar la partida
				&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre3.getText().equals(""))
				&& (!this.vistaNuevaPartida.txfNombre4.getText().equals("")))
			{
				 this.vistaJugando = new Jugando(4, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), this.vistaNuevaPartida.txfNombre4.getText());
				 this.vistaJugando.MostrarJugando();
				 this.vistaNuevaPartida.OcultarNuevaPartida();
			}
			else if((numJugadores == 3) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre3.getText().equals("")))
			{
				this.vistaJugando = new Jugando(3, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), this.vistaNuevaPartida.txfNombre3.getText(), "");
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarNuevaPartida();
			}
			else if((numJugadores == 2) && (!this.vistaNuevaPartida.txfNombre1.getText().equals(""))
					&& (!this.vistaNuevaPartida.txfNombre2.getText().equals("")))
			{
				this.vistaJugando = new Jugando(2, this.vistaNuevaPartida.txfNombre1.getText(), this.vistaNuevaPartida.txfNombre2.getText(), "", "");
				this.vistaJugando.MostrarJugando();
				this.vistaNuevaPartida.OcultarNuevaPartida();
			}
			else
			{
				this.vistaNuevaPartida.txfNombre1.requestFocus();
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
		else if(this.vistaNuevaPartida.pedirNumeroJugadores.isActive()) //Cerrar ventana NuevaPartida pidiendo número jugadores
		{
			this.vistaNuevaPartida.pedirNumeroJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			this.vistaNuevaPartida.btnContinuar.removeActionListener(this); //Eliminar Listener Botón
			this.vistaNuevaPartida.OcultarDialogNumeroJugadores();
		}
		else if(this.vistaNuevaPartida.pedirNombresJugadores.isActive()) //Cerrar ventana NuevaPartida pidiendo nombres jugadores
		{
			this.vistaNuevaPartida.pedirNombresJugadores.removeWindowListener(this); //Eliminar Listener Dialog
			this.vistaNuevaPartida.btnComenzarPartida.removeActionListener(this); //Eliminar Listener Botón
			this.vistaNuevaPartida.choNumeroJugadores.select(0); //Reseteamos el desplegable
			this.vistaNuevaPartida.removeAll();
			this.vistaNuevaPartida.OcultarDialogNombresJugadores();
		}
		else if((this.vistaJugando != null) && (this.vistaJugando.isActive()))
		{
			this.vistaJugando.removeWindowListener(this);
			this.vistaJugando.removeMouseListener(this);
			this.vistaJugando.OcultarJugando();
			this.vistaNuevaPartida.OcultarDialogNombresJugadores();
			this.vistaMenuInicio.MostrarInicio();
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

	@Override
	public void mouseClicked(MouseEvent click)
	{
		int x = click.getX();
		int y = click.getY();
		
		// Pulsamos sobre el cubilete
		if((x >= 33) && (x <= 73) && (y >= 217) && (y <= 277))
		{
			tirada = this.modelo.tirada();
			this.vistaJugando.mostrarTirada(tirada);
			
			if(true) // Analizar tirada
			{
				switch(turno) // Actualizar posición
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
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub
		
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