package ejercicio.unidadSiete.Factura;

import ejercicio.unidadSiete.combo.Combo;
import ejercicio.unidadSiete.combo.ComboPumperFeliz;
import ejercicio.unidadSiete.combo.ComboPumperGrande;
import ejercicio.unidadSiete.combo.ComboPumperMediano;

public class Factura {
	private Combo combo;
		
	public Factura(Combo combo) {
		super();
		this.combo = combo;
	}

	public Combo getCombo() {
		return combo;
	}

	public void setCombo(Combo combo) {
		this.combo = combo;
	}

	public String getDetalleFactura(Combo combo) {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("**************************************");
		sb.append("************ TICKET FISCAL ***********");
		sb.append("************** PUMPERNIK *************");
		sb.append("**************************************");
		sb.append("**************************************");
		sb.append("DESCRIPCION--------------------TOTAL  ");
		if (combo instanceof ComboPumperFeliz) {
			sb.append("Pumper FELIZ" + "-------------" +  combo.getPrecio().toString());
		}else if (combo instanceof ComboPumperMediano) {
			sb.append("Pumper MEDIANO" + "-------------" + combo.getPrecio().toString());
		}else if (combo instanceof ComboPumperGrande) {
			sb.append("Pumper GRANDE" + "-------------" + combo.getPrecio().toString());
		}else {
			sb.append("Pumper SUPER" + "-------------" + combo.getPrecio().toString());
		}
		
		sb.append("**************************************");
		sb.append("**************************************");
		
		return sb.toString();
	}


}
