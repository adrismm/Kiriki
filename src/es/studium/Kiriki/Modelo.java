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
	//CONEXIÓN CON LA BASE DE DATOS
	
		//Drivers 
		String driver = "com.mysql.cj.jdbc.Driver";
		// Indicamos la direcciï¿½n donde se encuentra la base de datos. 
		String url = "jdbc:mysql://localhost:3306/juegoKiriki"; 
		// Introducimos el usuario por medio del cual se harï¿½ la conexiï¿½n 
		String login = "root";
		// Indicamos la contraseï¿½a que tiene dicha base de datos. 
		String password = "Holacaracola"; 
		String sentencia = "";
		Connection connection = null; 
		Statement statement = null; 
		ResultSet rs = null;

		// Conexiï¿½n con la base de datos
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
				// Establecer la conexiï¿½n con la base de datos, juegoKiriki
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
	
	// Mï¿½todos adicionales
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
	
/*	public boolean compararValores (String valorTirada, String valorAnunciado)
	{
		boolean controlMentira;
		controlMentira = false;
		if(valorTirada != )
		int valorCadena1 = Integer.parseInt(valorTirada);
		int valorCadena2 = Integer.parseInt(valorAnunciado);
		
		if(valorTirada.equals("Pareja de Negras"))
		{
			valorCadena1 = 11;
		}
		else if(valorAnunciado.equals("Pareja de Negras"))
		{
			valorCadena2 = 11;
		}
		else if(valorTirada.equals("Pareja de Rojas"))
		{
			valorCadena1 = 12;
		}
		else if(valorAnunciado.equals("Pareja de Rojas"))
		{
			valorCadena2 = 12;
		}
		else if(valorTirada.equals("Pareja de Jotas"))
		{
			valorCadena1 = 13;
		}
		else if(valorAnunciado.equals("Pareja de Jotas"))
		{
			valorCadena2 = 13;
		}
		else if(valorTirada.equals("Pareja de Reinas"))
		{
			valorCadena1 = 14;
		}
		else if(valorAnunciado.equals("Pareja de Reinas"))
		{
			valorCadena2 = 14;
		}
		else if(valorTirada.equals("Pareja de Reyes"))
		{
			valorCadena1 = 15;
		}
		else if(valorAnunciado.equals("Pareja de Reyes"))
		{
			valorCadena2 = 15;
		}
		else if(valorTirada.equals("Pareja de Ases"))
		{
			valorCadena1 = 16;
		}
		else if(valorAnunciado.equals("Pareja de Ases"))
		{
			valorCadena2 = 16;
		}
			
		
		String cuatro = "4";
		int numCuatro = Integer.parseInt(cuatro);
		String cinco = "5";
		int numCinco = Integer.parseInt(cinco);
		String seis = "6";
		int numSeis = Integer.parseInt(seis);
		String siete = "7";
		int numSiete = Integer.parseInt(siete);
		String ocho = "8";
		int numOcho = Integer.parseInt(ocho);
		String nueve = "9";
		int numNueve = Integer.parseInt(nueve);
		String diez = "10";
		int numDiez = Integer.parseInt(diez);
		String ladrillazo = "11";
		int numLadrillazo = Integer.parseInt(ladrillazo);
		String negras = "12";
		int parejaNegras = Integer.parseInt(negras);
		String rojas = "13";
		int parejaRojas = Integer.parseInt(rojas);
		String jotas = "14";
		int parejaJotas = Integer.parseInt(jotas);
		String reinas = "15";
		int parejaReinas = Integer.parseInt(reinas);
		String reyes = "16";
		int parejaReyes = Integer.parseInt(reyes);
		String ases = "17";
		int parejaAses = Integer.parseInt(ases);
		
		if((valorAnunciado == cuatro) && (valorTirada > cuatro)
		{
			controlMentira = true;
			return(controlMentira);
		}
		else if(valorAnunciado >= valorTirada)
		{
			return(controlMentira);
		}
		
		return(controlMentira);
	} */
	
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
}
