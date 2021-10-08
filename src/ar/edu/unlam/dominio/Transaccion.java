package ar.edu.unlam.dominio;

public class Transaccion {
	private Pelicula pelicula;
	private Cliente cliente;
	private Integer id;
	private TipoDeTransaccion tipoDeTransaccion;

	public Transaccion(Pelicula pelicula, Cliente cliente, TipoDeTransaccion tipoDeTransaccion) {

		this.pelicula = pelicula;
		this.cliente = cliente;
		this.tipoDeTransaccion = tipoDeTransaccion;
		this.setId((int) Math.random() * 1000);
	}

	public Pelicula getPelicula() {
		return pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TipoDeTransaccion getTipoDeTransaccion() {
		return tipoDeTransaccion;
	}

	public void setTipoDeTransaccion(TipoDeTransaccion tipoDeTransaccion) {
		this.tipoDeTransaccion = tipoDeTransaccion;
	}

	@Override
	public String toString() {
		return "Transaccion [pelicula=" + pelicula + ", cliente=" + cliente + ", id=" + id + ", tipoDeTransaccion="
				+ tipoDeTransaccion + "]";
	}
}
