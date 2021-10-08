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
	
    public Pelicula buscarPeliculaPorCodigo(Integer codigo) {
		Pelicula peliculaEncontrada = null;
		for (int i = 0; i < listaDePeliculas.length; i++) {
			if (listaDePeliculas[i] != null && listaDePeliculas[i].getDisponible() == true) {
				if (listaDePeliculas[i].getCodigo().equals(codigo)) {
					peliculaEncontrada = listaDePeliculas[i];
					break;
				}
			}

		}
		return peliculaEncontrada;
	}

	public Cliente buscarClientePorDni(Integer dni) {
		Cliente clienteEncontrado = null;
		for (int i = 0; i < listaDeClientes.length; i++) {
			if (listaDeClientes[i] != null) {
				if (listaDeClientes[i].getDni().equals(dni)) {
					clienteEncontrado = listaDeClientes[i];
					break;
				}
			}

		}

		return clienteEncontrado;
	}

	public Boolean alquilarPelicula(Integer codigoDePelicula, Integer dni) {
		Boolean sePuedeAlquilar = false;
		Pelicula pelicula = buscarPeliculaPorCodigo(codigoDePelicula);
		Cliente cliente = buscarClientePorDni(dni);
		if (cliente.getEsSocio() == true && cliente != null && pelicula.getIsGastada() == false && pelicula != null) {
			Transaccion alquiler = new Transaccion(pelicula, cliente, TipoDeTransaccion.ALQUILER);
			for (int i = 0; i < listaDeAlquieres.length; i++) {
				if (listaDeAlquieres[i] == null) {
					listaDeAlquieres[i] = alquiler;
					pelicula.setDisponible(false);
					pelicula.gastarse();
					sePuedeAlquilar = true;
					break;
				}
			}
		}
		return sePuedeAlquilar;
	}
}
