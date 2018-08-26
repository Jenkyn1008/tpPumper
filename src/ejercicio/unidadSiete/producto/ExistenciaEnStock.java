package ejercicio.unidadSiete.producto;

public class ExistenciaEnStock {
	private String nombre;
	private Double cantidad;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getCantidad() {
		return cantidad;
	}

	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "\nExistenciaEnStock [nombre=" + nombre + ", cantidad=" + cantidad + "]";
	}
}
