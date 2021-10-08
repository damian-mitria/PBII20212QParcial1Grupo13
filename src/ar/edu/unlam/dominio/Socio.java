package ar.edu.unlam.dominio;

public class Socio extends Cliente {

	
	public Socio(String nombre, Integer dni) {
        super(nombre, dni);
        super.setTipoSocio(TipoDeSocio.SOCIO);
        super.setEsSocio(true);
    }

    @Override
    public void depositarPuntos(Integer cantidad) {
        if (cantidad > 0) {
            super.puntos += cantidad * 2;
        }

    }
}
