package ejercicio.unidadSiete.venta;

import ejercicio.unidadSiete.combo.Combo;
import ejercicio.unidadSiete.datosContacto.DatosContacto;

public class Venta {
	private Combo combo;
	private DatosContacto datosContacto;
	
	public Venta(Combo combo, DatosContacto datosContacto) {
		super();
		this.combo = combo;
		this.datosContacto = datosContacto;
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
}
