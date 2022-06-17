package es.studium.Kiriki;

public class Principal
{
	public static void main(String[] args)
	{
		Modelo modelo = new Modelo();
		MenuInicio menuInicio = new MenuInicio();
		NuevaPartida nuevaPartida = new NuevaPartida();
		Ranking ranking = new Ranking();
		Ayuda ayuda = new Ayuda();
		Jugando jugando = new Jugando();
		new Controlador (modelo, menuInicio, nuevaPartida, ranking, ayuda, jugando);
	}
}
