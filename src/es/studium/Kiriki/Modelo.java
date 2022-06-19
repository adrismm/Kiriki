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
		t = rnd.nextInt(2); // 0-5
		return (t);
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
}
