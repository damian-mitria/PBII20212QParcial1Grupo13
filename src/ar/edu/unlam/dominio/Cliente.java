package ar.edu.unlam.dominio;

public abstract class Cliente {

	private String nombre;
	private Integer dni;
	private Boolean esSocio;
	private TipoDeSocio tipoSocio;
	protected Integer puntos;
	private String eMail;
	private String buzonDeMensajes;

	public Cliente(String nombre, Integer dni) {
		this.nombre = nombre;
		this.dni = dni;
		this.puntos = 0;
		this.eMail = "";
	}

	public Boolean registrarMail(String mail) {
		Boolean esValido = false;

		for (int i = 0; i < mail.length(); i++) {
			if ((int) mail.charAt(i) == 64) {
				esValido = true;
				this.seteMail(mail);
				break;
			}
		}
		return esValido;
	}

	// un cliente puede depositarse dinero pero el dinero se convierte en puntos del
	// video club... la ventaja de ser socio es que al socio el monto que deposita
	// se le duplica mientras que al no socio le queda igual
	public void depositarPuntos(Integer cantidad) {
		if (cantidad > 0) {
			this.puntos += cantidad;
		}

	}

	public void descontarPuntos(Integer cantidad) {
		this.puntos -= cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public Boolean getEsSocio() {
		return esSocio;
	}

	public void setEsSocio(Boolean esSocio) {
		this.esSocio = esSocio;
	}

	public TipoDeSocio getTipoSocio() {
		return tipoSocio;
	}

	public void setTipoSocio(TipoDeSocio tipoSocio) {
		this.tipoSocio = tipoSocio;
	}

	public Integer getPuntos() {
		return puntos;
	}

	public void setPuntos(Integer puntos) {
		this.puntos = puntos;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", dni=" + dni + ", esSocio=" + esSocio + ", tipoSocio=" + tipoSocio + "]";
	}

	public String getBuzonDeMensajes() {
		return buzonDeMensajes;
	}

	public void setBuzonDeMensajes(String mensaje) {
		this.buzonDeMensajes = mensaje;
	}

}
