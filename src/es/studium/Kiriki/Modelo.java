package es.studium.Kiriki;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class Modelo

{
	//CONEXI�N CON LA BASE DE DATOS
	
		//Drivers 
		String driver = "com.mysql.cj.jdbc.Driver";
		// Indicamos la direcci�n donde se encuentra la base de datos. 
		String url = "jdbc:mysql://localhost:3306/juegoKiriki"; 
		// Introducimos el usuario por medio del cual se har� la conexi�n 
		String login = "root";
		// Indicamos la contrase�a que tiene dicha base de datos. 
		String password = "Holacaracola"; 
		String sentencia = "";
		Connection connection = null; 
		Statement statement = null; 
		ResultSet rs = null;

		// Conexi�n con la base de datos
		public Connection conectar()
			{
				// Cargar los controladores para el acceso a la base de datos.
				try
					{
						Class.forName(driver); 
						System.out.println("La conexion es correcta");
					}
				catch (ClassNotFoundException cnfe)
					{
						System.out.println("Se ha producido un error al cargar el Driver"); // Error 1-
					}
				// Establecer la conexi�n con la base de datos, juegoKiriki
				try
					{
						connection = DriverManager.getConnection(url, login, password);
					}
					
				catch (SQLException sqle)
					{
						System.out.println("Se ha producido un error al conectar con la base de datos: " + sqle.getMessage()); // Error 2-
					}
					
				return connection;
			}
		
		//Desconectar de la base de datos
		public void desconectar(Connection c)
			{
				try
				{
					if(connection!=null)
					{
						connection.close();
						System.out.println("Se cerro la conexion");
					}
				}
			catch (SQLException error)
				{
					System.out.println(error.getMessage());
				}
			}
		
		// Ranking de los jugadores
		public  String mejoresJugadores(Connection connection)
			{
				String ranking = "";
				sentencia = "SELECT nombreJugador, puntosJugador FROM Jugadores ORDER BY puntosJugador DESC LIMIT 10;"; // idJugador
				ResultSet rs = null;
						
					try
						{
							statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
							rs = statement.executeQuery(sentencia); 
								
							while(rs.next())
								{
									ranking = ranking + rs.getString("nombreJugador")+ "/" + rs.getInt("puntosJugador") + "/";
								}
						}
				catch (SQLException sqle)
					{
						System.out.println(sqle.getMessage()); // sqle.printStackTrace
					}
					
				return (ranking);
			}
	
	// M�todos adicionales
	Random rnd = new Random();
	
	public Modelo()
	{
		
	}
	
	public int tirada()
	{
		int t;
		t = 1 + rnd.nextInt(6);
		return (t);
	}
	
	public String calcularValorTirada(int tirada1, int tirada2)
	{
		String valorTirada;
		valorTirada = String.valueOf(tirada1 + tirada2);
		
		if(tirada1 == tirada2)
		{
			if(tirada1 == 1)
			{
				valorTirada = "Pareja de Negras";
				return(valorTirada);
			}
			else if(tirada1 == 2)
			{
				valorTirada = "Pareja de Rojas";
				return(valorTirada);
			}
			else if(tirada1 == 3)
			{
				valorTirada = "Pareja de Jotas";
				return(valorTirada);
			}
			else if(tirada1 == 4)
			{
				valorTirada = "Pareja de Reinas";
				return(valorTirada);
			}
			else if(tirada1 == 5)
			{
				valorTirada = "Pareja de Reyes";
				return(valorTirada);
			}
			else if(tirada1 == 6)
			{
				valorTirada = "Pareja de Ases";
				return(valorTirada);
			}
		}
		else if((tirada1 + tirada2) == 3)
		{
			valorTirada = "¡Kiriki!";
			return(valorTirada);
		}
		else if((tirada1 + tirada2) == 11)
		{
			valorTirada = "Ladrillazo";
			return(valorTirada);
		}
		
		return(valorTirada);
	}
	
	public boolean compararValores (String valorTirada, String valorAnunciado)
	{
		boolean controlMentira;
		controlMentira = false;
		
		int valorCadena1 = 0;
		int valorCadena2 = 0;
		
		if(valorTirada.equals("4") || 
				valorTirada.equals("5") || 
				valorTirada.equals("6") || 
				valorTirada.equals("7") || 
				valorTirada.equals("8") || 
				valorTirada.equals("9") || 
				valorTirada.equals("10"))
		{
				valorCadena1 = Integer.parseInt(valorTirada);
		}
		else if(valorTirada.equals("Ladrillazo"))
		{
			valorCadena1 = 11;
		}
		else if(valorTirada.equals("Pareja de Negras"))
		{
			valorCadena1 = 12;
		}
		else if(valorTirada.equals("Pareja de Rojas"))
		{
			valorCadena1 = 13;
		}
		else if(valorTirada.equals("Pareja de Jotas"))
		{
			valorCadena1 = 14;
		}
		else if(valorTirada.equals("Pareja de Reinas"))
		{
			valorCadena1 = 15;
		}
		else if(valorTirada.equals("Pareja de Reyes"))
		{
			valorCadena1 = 16;
		}
		else if(valorTirada.equals("Pareja de Ases"))
		{
			valorCadena1 = 17;
		}
		
		if(valorAnunciado.equals("4") || 
				valorAnunciado.equals("5") || 
				valorAnunciado.equals("6") || 
				valorAnunciado.equals("7") || 
				valorAnunciado.equals("8") || 
				valorAnunciado.equals("9") || 
				valorAnunciado.equals("10"))
		{
				valorCadena2 = Integer.parseInt(valorAnunciado);
		}
		else if(valorAnunciado.equals("Ladrillazo"))
		{
			valorCadena2 = 11;
		}
		else if(valorAnunciado.equals("Pareja de Negras"))
		{
			valorCadena2 = 12;
		}
		else if(valorAnunciado.equals("Pareja de Rojas"))
		{
			valorCadena2 = 13;
		}
		else if(valorAnunciado.equals("Pareja de Jotas"))
		{
			valorCadena2 = 14;
		}
		else if(valorAnunciado.equals("Pareja de Reinas"))
		{
			valorCadena2 = 15;
		}
		else if(valorAnunciado.equals("Pareja de Reyes"))
		{
			valorCadena2 = 16;
		}
		else if(valorAnunciado.equals("Pareja de Ases"))
		{
			valorCadena2 = 17;
		}
		
		if(valorCadena1 != valorCadena2)
		{
			controlMentira = true;
			return(controlMentira);
		}
		else if(valorCadena1 == valorCadena2)
		{
			controlMentira = false;
			return(controlMentira);
		} 
		return (controlMentira);
	}
	
	//Insertar jugadores que han ganado en la base de datos junto a sus puntos
	public void insertarJugador(String jugador, int puntos)
	{
		Statement statement = null; 
		sentencia = "INSERT INTO Jugadores (nombreJugador, puntosJugador) VALUES ('"+jugador+"', '"+puntos+"');";
		
		try
			{
				statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				statement.executeUpdate(sentencia);
			}
		catch (SQLException sqle)
			{
				System.out.println(sqle.getMessage());
				
			}
	}
	
	//Programa ayuda del juego
	public void ayuda (Connection connection)
	{
		try
			{
				Runtime.getRuntime().exec("hh.exe Ayuda.chm");
			}
		catch (IOException e)
			{
				e.printStackTrace();
			}
	}
	public int finPartida(int vidaJug1, int vidaJug2, int vidaJug3, int vidaJug4)
	{
		int vidasGanador;
		vidasGanador = 0; 
		
		if((vidaJug1 != 0) && (vidaJug2 == 0) && (vidaJug3 == 0) && (vidaJug4 == 0))
		{
			vidasGanador = vidaJug1;
			return vidasGanador;
		}
		else if((vidaJug1 == 0) && (vidaJug2 != 0) && (vidaJug3 == 0) && (vidaJug4 == 0))
		{
			vidasGanador = vidaJug2;
			return vidasGanador;
		}
		else if((vidaJug1 == 0) && (vidaJug2 == 0) && (vidaJug3 != 0) && (vidaJug4 == 0))
		{
			vidasGanador = vidaJug3;
			return vidasGanador;
		}
		else if((vidaJug1 == 0) && (vidaJug2 == 0) && (vidaJug3 == 0) && (vidaJug4 != 0))
		{
			vidasGanador = vidaJug4;
			return vidasGanador;
		}
		return (vidasGanador);
	}
}
