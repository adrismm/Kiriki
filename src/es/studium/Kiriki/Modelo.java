package es.studium.Kiriki;

import java.sql.Connection;
import java.util.Random;

public class Modelo
{
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
	
	public Connection conectar()
	{
		return null;
	}
	
	public String consultarRanking()
	{
		// "SELECT * FROM jugadores ORDER BY puntos ASC";
		return null;
	}
	
	public void insertarJugador(String jugador, int puntos)
	{
		// INSERT INTO ...
	}
	
	public void desconectar(Connection c)
	{
		
	}
}
