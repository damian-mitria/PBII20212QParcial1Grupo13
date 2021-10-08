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

	// Aca estamos aplicando descuento a los socios en la compra de una pelicula
	public Boolean venderPelicula(Integer codigoDePelicula, Integer dni) {
		Boolean sePuedeVender = false;
		Pelicula pelicula = buscarPeliculaPorCodigo(codigoDePelicula);
		Cliente cliente = buscarClientePorDni(dni);
		Transaccion venta = null;
		if (cliente != null && pelicula != null) {
			if (cliente.getEsSocio() && cliente.getPuntos() >= 10) {
				venta = new Transaccion(pelicula, cliente, TipoDeTransaccion.VENTA);
				cliente.descontarPuntos(10);
				for (int i = 0; i < listaDeVentas.length; i++) {
					if (listaDeVentas[i] == null) {
						listaDeVentas[i] = venta;
						break;
					}
				}
				for (int j = 0; j < listaDePeliculas.length; j++) {
					if (listaDePeliculas[j] != null) {
						if (listaDePeliculas[j].equals(pelicula))
							listaDePeliculas[j] = null;
						break;
					}
				}
				sePuedeVender = true;
			}
			if (cliente.getEsSocio() == false && cliente.getPuntos() >= 20) {
				venta = new Transaccion(pelicula, cliente, TipoDeTransaccion.VENTA);
				cliente.descontarPuntos(20);
				for (int i = 0; i < listaDeVentas.length; i++) {
					if (listaDeVentas[i] == null) {
						listaDeVentas[i] = venta;
						break;
					}
				}
				for (int j = 0; j < listaDePeliculas.length; j++) {
					if (listaDePeliculas[j] != null) {
						if (listaDePeliculas[j].equals(pelicula))
							listaDePeliculas[j] = null;
						break;
					}
				}
				sePuedeVender = true;
			}

		}

		return sePuedeVender;

	}
	
	public Integer decirCodigoDeLaTransaccion(Integer dni) {
        Integer codigo = 0;
        for (int i = 0; i < listaDeAlquieres.length; i++) {
            if (listaDeAlquieres[i] != null && listaDeAlquieres[i].getCliente().getDni().equals(dni)) {
                codigo = listaDeAlquieres[i].getId();
                break;
            }

        }
        return codigo;
    }

}
