package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.dominio.BluRay;
import ar.edu.unlam.dominio.Cliente;
import ar.edu.unlam.dominio.Dvd;
import ar.edu.unlam.dominio.NoSocio;
import ar.edu.unlam.dominio.Pelicula;
import ar.edu.unlam.dominio.Socio;
import ar.edu.unlam.dominio.TipoDeGenero;
import ar.edu.unlam.dominio.Vhs;
import ar.edu.unlam.dominio.VideoClub;

public class TestVideoClub {

	VideoClub videoClub;

	Pelicula batman;
	Pelicula laLLamada;
	Pelicula scaryMovie;

	Pelicula rapidoYFurioso;
	Pelicula elConjuro;
	Pelicula tontoYReTonto;

	Cliente martin;
	Cliente pedro;

	@Before
	public void before() {
		videoClub = new VideoClub("Brujas");

		batman = new Vhs("Batman", 1, TipoDeGenero.ACCION);
		laLLamada = new Dvd("laLLamada", 2, TipoDeGenero.TERROR);
		scaryMovie = new BluRay("scaryMovie", 3, TipoDeGenero.COMEDIA);

		rapidoYFurioso = new Vhs("RapidoYFurioso", 4, TipoDeGenero.ACCION);
		elConjuro = new Dvd("ElConjuro", 5, TipoDeGenero.TERROR);
		tontoYReTonto = new BluRay("TontoYReTonto", 6, TipoDeGenero.COMEDIA);

		martin = new Socio("Martin", 1111);
		pedro = new NoSocio("Pedro", 4444);

	}

	@Test
	public void queUnaPeliculaDigaSiEsPirateableONoPruebaUno() {

		String valorEsperado = "ERROR! No soy piratable";

		assertEquals(valorEsperado, batman.decirSiEsPirateable());
	}

	@Test
	public void queUnaPeliculaDigaSiEsPirateableONoPruebaDos() {

		String valorEsperado = "Soy pirateable";

		assertEquals(valorEsperado, laLLamada.decirSiEsPirateable());
	}

	@Test
	public void queElVideoClubPuedaRegistarQueUnSocioPuedaAlquilarUnaPelicula() {

		videoClub.agregarCliente(martin);
		videoClub.agregarPelicula(batman);

		assertTrue(videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni()));
	}

	@Test
	public void queElVideoClubPuedaRegistarQueUnNoSocioNoPuedaAlquilarUnaPelicula() {

		videoClub.agregarCliente(pedro);
		videoClub.agregarPelicula(batman);

		assertFalse(videoClub.alquilarPelicula(batman.getCodigo(), pedro.getDni()));
	}

	@Test
	public void queSeDevuelvaCorrectamenteUnaPelicualAlquilada() {

		videoClub.agregarCliente(martin);
		videoClub.agregarPelicula(batman);
		videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
		Integer idPrestamo = videoClub.decirCodigoDeLaTransaccion(martin.getDni());

		assertTrue(videoClub.recepcionarPeliculaAlquilada(idPrestamo));

	}

}
