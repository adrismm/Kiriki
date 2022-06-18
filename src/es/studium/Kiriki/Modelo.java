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
			// Indicamos la dirección donde se encuentra la base de datos. 
			String url = "jdbc:mysql://localhost:3306/juegoKiriki"; 
			// Introducimos el usuario por medio del cual se hará la conexión 
			String login = "root";
			// Indicamos la contraseña que tiene dicha base de datos. 
			String password = "Holacaracola"; 
			String sentencia = "";
			Connection connection = null; 
			Statement statement = null; 
			ResultSet rs = null;

		// Conexion con la base de datos
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
							System.out.println("Se ha producido un error al cargar el Driver");
						}
					// Establecer la conexión con la base de datos, juegoKiriki
					try
						{
							connection = DriverManager.getConnection(url, login, password);
						}
					
					catch (SQLException sqle)
						{
							System.out.println("Se ha producido un error al conectar con la base de datos: " + sqle.getMessage());
						}
					
					return connection;
				}
		
		//RANKING DE LOS JUGADORES 
			public  String puntosGuardados(Connection connection)
				{
					String ranking = "";
					sentencia = "SELECT nombreJugador, puntosJugador FROM Jugadores ORDER BY puntosJugador desc;";
					ResultSet rs = null;
						
						try
							{
								statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY); 
								rs = statement.executeQuery(sentencia); 
								
								while(rs.next())
									{
										ranking = ranking + rs.getString("nombreJugador")+ "/" +rs.getInt("puntosJugador")+ "/";
									}
							}
					catch (SQLException sqle)
						{
							System.out.println(sqle.getMessage());
						}
					
					return ranking;
				}
	Random rnd = new Random();
	
	public Modelo()
	{
		
	}
	
	public int tirada()
	{
		int t;
		t = rnd.nextInt(6) + 1; // 0-5
		return (t);
	}
	
	
	//insertar jugaroes que han ganado en la base de datos junto a sus puntos
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
	//desconectar de la base de datos
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
