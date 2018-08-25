package ejercicio.unidadSiete.Caja;

public class Caja {
	
	private Boolean emiteFactura;
	
	public Boolean getEmiteFactura() {
		return emiteFactura;
	}

	public void setEmiteFactura(Boolean emiteFactura) {
		this.emiteFactura = emiteFactura;
	}

	public Caja(Boolean emiteFactura) {
		this.emiteFactura = emiteFactura;
	}
}
