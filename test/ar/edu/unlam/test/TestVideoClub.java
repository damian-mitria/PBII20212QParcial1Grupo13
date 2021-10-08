package ar.edu.unlam.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.unlam.dominio.Socio;
import ar.edu.unlam.dominio.VideoClub;

public class TestVideoClub {

	@Test
	public void queSePuedaAgregarUnClienteALaLista() {
		VideoClub videoClub = new VideoClub("Movies");
		Socio cliente1 = new Socio("Celeste", 4200000);
		
		assertTrue(videoClub.agregarCliente(cliente1));
		
		}

}
