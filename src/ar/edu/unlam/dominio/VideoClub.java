package ar.edu.unlam.dominio;

public class VideoClub {
	private String nombre;
    private Pelicula[] listaDePeliculas;
    private Cliente[] listaDeClientes;
    private Transaccion[] listaDeAlquieres;
    private Transaccion[] listaDeVentas;

    public VideoClub(String nombre) {
        this.nombre = nombre;
        this.listaDePeliculas = new Pelicula[100];
        this.listaDeClientes = new Cliente[100];
        this.listaDeAlquieres = new Transaccion[100];
        this.listaDeVentas = new Transaccion[100];
    }

    public Boolean agregarCliente(Cliente cliente) {
        Boolean seAgrego = false;
        for (int i = 0; i < this.listaDeClientes.length; i++) {
            if (this.listaDeClientes[i] == null) {
                this.listaDeClientes[i] = cliente;
                seAgrego = true;
                break;
            }
        }
        return seAgrego;
    }

    public Boolean agregarPelicula(Pelicula pelicula) {
        Boolean seAgrego = false;
        for (int i = 0; i < this.listaDePeliculas.length; i++) {
            if (this.listaDePeliculas[i] == null) {
                this.listaDePeliculas[i] = pelicula;
                seAgrego = true;
                break;
            }
        }
        return seAgrego;
    }
	
}
