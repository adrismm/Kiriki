package es.studium.Kiriki;

public class Principal
{
	public static void main(String[] args)
	{
		Modelo modelo = new Modelo();
		MenuInicio vistaMenuInicio = new MenuInicio();
		NuevaPartida vistaNuevaPartida = new NuevaPartida();
		Ranking vistaRanking = new Ranking();
		Jugando vistaJugando = new Jugando(int, "", "", "", "");
		new Controlador (modelo, vistaMenuInicio, vistaNuevaPartida, vistaRanking, vistaJugando);
	}
}
