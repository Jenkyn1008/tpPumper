package ejercicio.unidadSiete.combo;

import java.math.BigDecimal;
import java.util.List;

import ejercicio.unidadSiete.producto.Producto;

public class Combo {
	
	private BigDecimal precio;
	private Boolean tieneJuguete;
	List<Producto> productosCombos;
	
	public Combo(BigDecimal precio, Boolean tieneJuguete) {
		super();
		this.precio = precio;
		this.tieneJuguete = tieneJuguete;
	}

	public BigDecimal getPrecio() {
		return precio;
	}

	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}

	public Boolean getTieneJuguete() {
		return tieneJuguete;
	}

	public void setTieneJuguete(Boolean tieneJuguete) {
		this.tieneJuguete = tieneJuguete;
	}

	public List<Producto> getProductosCombos() {
		return productosCombos;
	}

	public void setProductosCombos(List<Producto> productosCombos) {
		this.productosCombos = productosCombos;
	}
}
