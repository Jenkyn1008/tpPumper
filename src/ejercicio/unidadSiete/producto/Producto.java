package ejercicio.unidadSiete.producto;

public class Producto {
	
	private String tipoProducto;
	private Integer cantidad;
	
	public Producto(String tipoProducto, Integer cantidad) {
		super();
		this.tipoProducto = tipoProducto;
		this.cantidad = cantidad;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
}
