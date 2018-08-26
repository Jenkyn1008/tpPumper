package ejercicio.unidadSiete.venta;

import ejercicio.unidadSiete.Caja.Caja;
import ejercicio.unidadSiete.combo.Combo;
import ejercicio.unidadSiete.datosContacto.DatosContacto;

public class Venta {
	private Combo combo;
	private DatosContacto datosContacto;
	private Caja cajaAsignada;
	
	public Venta(Combo combo, DatosContacto datosContacto, Caja cajaAsignada) {
		super();
		this.combo = combo;
		this.datosContacto = datosContacto;
		this.cajaAsignada = cajaAsignada;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public DatosContacto getDatosContacto() {
		return datosContacto;
	}

	public void setDatosContacto(DatosContacto datosContacto) {
		this.datosContacto = datosContacto;
	}

	public Caja getCajaAsignada() {
		return cajaAsignada;
	}

	public void setCajaAsignada(Caja cajaAsignada) {
		this.cajaAsignada = cajaAsignada;
	}
	
}
