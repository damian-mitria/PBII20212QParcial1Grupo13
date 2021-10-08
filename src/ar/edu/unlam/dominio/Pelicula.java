package ar.edu.unlam.dominio;

public abstract class Pelicula {
	private String nombre;
	private Integer codigo;
	private Boolean disponible;
	private Boolean pirateable;
	private TipoDeFormato tipoDeFormato;
	private TipoDeGenero tipoDeGenero;
	
	protected Integer contadorUso;
	private Boolean isGastada;
	
	public Pelicula(String nombre, Integer codigo, TipoDeGenero tipoDeGenero) {
		this.nombre = nombre;
		this.codigo = codigo;
		this.disponible = true;
		this.tipoDeGenero = tipoDeGenero;
		this.contadorUso = 0;
		this.isGastada = false;
	}
	
	public String decirSiEsPirateable() {
		String mensaje = "";
		if(this.pirateable) {
			mensaje = "Soy pirateable";
		}
		else {
			mensaje = "ERROR! No soy piratable";
		}
		return mensaje;
	}
	
	public abstract void gastarse();
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(Boolean disponible) {
		this.disponible = disponible;
	}

	public Boolean getPirateable() {
		return pirateable;
	}

	public void setPirateable(Boolean pirateable) {
		this.pirateable = pirateable;
	}

	public TipoDeFormato getTipoDeFormato() {
		return tipoDeFormato;
	}

	public void setTipoDeFormato(TipoDeFormato tipoDeFormato) {
		this.tipoDeFormato = tipoDeFormato;
	}

	public TipoDeGenero getTipoDeGenero() {
		return tipoDeGenero;
	}

	public void setTipoDeGenero(TipoDeGenero tipoDeGenero) {
		this.tipoDeGenero = tipoDeGenero;
	}

	public Integer getContadorUso() {
		return contadorUso;
	}

	public void setContadorUso(Integer contadorUso) {
		this.contadorUso = contadorUso;
	}

	public Boolean getIsGastada() {
		return isGastada;
	}

	public void setIsGastada(Boolean gastada) {
		this.isGastada = gastada;
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", codigo=" + codigo + ", disponible=" + disponible + ", pirateable="
				+ pirateable + ", tipoDeFormato=" + tipoDeFormato + ", tipoDeGenero=" + tipoDeGenero + ", contadorUso="
				+ contadorUso + ", gastada=" + isGastada + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pelicula other = (Pelicula) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
