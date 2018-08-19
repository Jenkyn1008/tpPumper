package ejercicio.unidadSiete.puesto;

public class Puesto {
	
	private Integer nroPuesto;
	private Boolean emiteFactura;
	
	public Puesto(Integer nroPuesto, Boolean emiteFactura) {
		this.nroPuesto = nroPuesto;
		this.emiteFactura = emiteFactura;
	}

	public Integer getNroPuesto() {
		return nroPuesto;
	}

	public void setNroPuesto(Integer nroPuesto) {
		this.nroPuesto = nroPuesto;
	}

	public Boolean getEmiteFactura() {
		return emiteFactura;
	}

	public void setEmiteFactura(Boolean emiteFactura) {
		this.emiteFactura = emiteFactura;
	}

}
