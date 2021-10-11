package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import ar.edu.unlam.dominio.*;



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
	public void queSePuedaAgregarUnClienteALaListaDeClientesDelVideoClub() {
		
		assertTrue(videoClub.agregarCliente(martin));
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
	
	//acordarse que cuando se crea un cliente (ya sea socio o NoSocio) no tiene puntos
		@Test
		public void queUnSocioNoPuedaComprarUnaPeliculaPorQueNoTienePuntosSuficientesParaEfectuarLaCompra() { 
			
			videoClub.agregarCliente(martin);
			videoClub.agregarPelicula(batman);
			
			assertFalse(videoClub.venderPelicula(batman.getCodigo(), martin.getDni()));
		}

		
		@Test
		public void queUnSocioPuedaComprarUnaPeliculaPorQueHaceUnDepositoDePuntos() { 
			
			videoClub.agregarCliente(martin);
			videoClub.agregarPelicula(batman);
			martin.depositarPuntos(5); // cargo el minimo necesario para comprar un pelicula
			
			assertTrue(videoClub.venderPelicula(batman.getCodigo(), martin.getDni()));
		}
		
		@Test
		public void queUnNoSocioHagaUnDepositoDePuntosCorrectamente() {
			
			videoClub.agregarCliente(pedro);
			pedro.depositarPuntos(50);
			Integer valorEsperado = 50;
			
			assertEquals(valorEsperado, pedro.getPuntos());
			
		}
		
		@Test
		public void queUnClienteDelVideoClubRegistreUnMailValidoCorrectamenteYVerificamosElMail() {
			
			videoClub.agregarCliente(martin);
			assertTrue(martin.registrarMail("martin@gmail.com"));
			
			String mailEsperado = "martin@gmail.com";
			assertEquals(mailEsperado, martin.geteMail());
			
		}
		
		@Test
		public void queElVideoClubPubliciteEstrenosATodosClientes() {
			videoClub.agregarCliente(martin);
			martin.registrarMail("martin@gmail.com");
			videoClub.agregarCliente(pedro);
			pedro.registrarMail("pedro@gmail.com");
			
			Cliente veronica = new NoSocio("Veronica", 568945);
			veronica.registrarMail("veronica@gmail.com");
			videoClub.agregarCliente(veronica);
			
			videoClub.enviarMensajeALosCliente("Llegaron los estrenos de Octubre!");
			
			String mensajeEsperado = "Llegaron los estrenos de Octubre!";
			
			assertEquals(mensajeEsperado, martin.getBuzonDeMensajes());
			assertEquals(mensajeEsperado, pedro.getBuzonDeMensajes());
			assertEquals(mensajeEsperado, veronica.getBuzonDeMensajes());
			
		}
		
		@Test
		public void queUnaPeliculaCuenteUnUsoAlAlquilarse() {
			
			videoClub.agregarCliente(martin);
			videoClub.agregarPelicula(batman);
			
			videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
			
			Integer valorEsperado = 1;
			
			assertEquals(valorEsperado, batman.getContadorUso());
			
		}
		
		@Test
		public void queUnaPeliculaSeGasteYNoPuedaVolverAAlquilarse() { // batman es un vhs y se gasta a los 3 usos
			videoClub.agregarCliente(martin);
			videoClub.agregarPelicula(batman);
			
			videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
			Integer idPrestamo = videoClub.decirCodigoDeLaTransaccion(martin.getDni());
			videoClub.recepcionarPeliculaAlquilada(idPrestamo);
			
			videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
			Integer idPrestamo2 = videoClub.decirCodigoDeLaTransaccion(martin.getDni());
			videoClub.recepcionarPeliculaAlquilada(idPrestamo2);
			
			videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
			Integer idPrestamo3 = videoClub.decirCodigoDeLaTransaccion(martin.getDni());
			videoClub.recepcionarPeliculaAlquilada(idPrestamo3);
			
			Boolean valorEsperado = true;
			assertEquals(valorEsperado, batman.getIsGastada());
			
			assertFalse(videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni())); // aca ya no se puede alquilar porque la pelicula esta gastada
		}

		@Test
        public void queNoSeAgreguenClientesDeMas() {
			
            videoClub.agregarCliente(martin);
            videoClub.agregarCliente(pedro);
            
            Cliente juan = new Socio("Juan", 123);
            Cliente damian = new NoSocio("Damian", 456);
            Cliente celeste = new Socio("Celeste", 789);
            Cliente andy = new Socio("Andy", 012);
            
            videoClub.agregarCliente(juan);
            videoClub.agregarCliente(damian);
            videoClub.agregarCliente(celeste);

            assertFalse(videoClub.agregarCliente(andy));
        }

        @Test 
        public void queNoSeAgreguenPeliculasDeMas() {
        	
            videoClub.agregarPelicula(batman);
            videoClub.agregarPelicula(laLLamada);
            videoClub.agregarPelicula(scaryMovie);
            videoClub.agregarPelicula(rapidoYFurioso);
            videoClub.agregarPelicula(elConjuro);

            assertFalse(videoClub.agregarPelicula(tontoYReTonto));
        }
        
        @Test
    	public void queSePuedaAgregarUnaPeliculaALaListaDePeliculasDelVideoClub() {
    		assertTrue(videoClub.agregarPelicula(batman));
    	}
    	@Test
    	public void queSePuedaBuscarUnaPeliculaPorCodigo() {
    		videoClub.agregarPelicula(batman);
    		assertEquals(batman, videoClub.buscarPeliculaPorCodigo(batman.getCodigo()));
    	}
    	@Test
    	public void queSePuedaBuscarUnClientePorDni() {
    		videoClub.agregarCliente(martin);
    		assertEquals(martin, videoClub.buscarClientePorDni(martin.getDni()));
    	}
    	@Test
    	public void queSePuedaBuscarUnaPeliculaPorCodigoSOLOsiEstaDisponible() { //porque si no disponible significa que esta alquilada
    		videoClub.agregarPelicula(batman);
    		videoClub.agregarCliente(martin);
    		videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
    		//no se puede buscar ya que no esta disponible, observacion
    		assertEquals(null, videoClub.buscarPeliculaPorCodigo(batman.getCodigo()));
    	}
    	
    	@Test
    	public void queSePuedaDecirElCodigoDeLaTrasaccion() {
    		videoClub.agregarPelicula(batman);
    		videoClub.agregarCliente(martin);
    		videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
    		Transaccion ventaTransaccion = new Transaccion(batman, martin, TipoDeTransaccion.VENTA);
    		Integer valorEsperado= ventaTransaccion.getId();
    		
    		assertEquals(valorEsperado, videoClub.decirCodigoDeLaTransaccion(ventaTransaccion.getId()));
    	}
    	
    	@Test
    	public void queNoSeDevuelvaCorrectamenteUnaPelicualAlquiladaSiEstaDisponible() {

    		videoClub.agregarCliente(martin);
    		videoClub.agregarPelicula(batman);
    		videoClub.alquilarPelicula(batman.getCodigo(), martin.getDni());
    		Integer idPrestamo = videoClub.decirCodigoDeLaTransaccion(martin.getDni());
    		batman.setDisponible(true);
    		assertFalse(videoClub.recepcionarPeliculaAlquilada(idPrestamo));
    	}
		
//    	Integrantes GRUPO 13: 
//    	
//    	-Aguirre, Celeste Melina (DNI 42101140)
//    	-Argañaraz, Alan Gabriel (DNI 43.873.058)
//    	-Manchini, Juan Manuel (DNI 42.997.249)
//    	-Mitria, Damian Nahuel (DNI 34.277.101)

		
}
