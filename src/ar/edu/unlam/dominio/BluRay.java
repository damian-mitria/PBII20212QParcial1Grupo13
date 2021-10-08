package ar.edu.unlam.dominio;

public class BluRay extends Pelicula {
	
	public BluRay(String nombre, Integer codigo, TipoDeGenero tipoDeGenero) {
        super(nombre, codigo, tipoDeGenero);
        super.setPirateable(false);
        super.setTipoDeFormato(TipoDeFormato.BLU_RAY);
    }

    @Override
    public void gastarse() {

        if (super.getContadorUso() < 7) {
            super.contadorUso++;
        } else {
            super.setIsGastada(true);
        }
    }
}
