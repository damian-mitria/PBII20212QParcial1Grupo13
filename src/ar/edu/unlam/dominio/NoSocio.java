package ar.edu.unlam.dominio;

public class NoSocio extends Cliente {

	
	public NoSocio(String nombre, Integer dni) {
        super(nombre, dni);
        super.setTipoSocio(TipoDeSocio.NO_SOCIO);
        super.setEsSocio(false);
    }
	
	
}
