package ejercicio.unidadSiete.util;

import ejercicio.unidadSiete.Factura.Factura;
import ejercicio.unidadSiete.combo.Combo;

public class Utils {

	private static Utils utils;
	public final String JUGUETE_HOMBRE = "jugueteVaron";
	public final String JUGUETE_MUJER = "jugueteMujer";
	public final String HAMBURGESA = "hamburguesa";
	public final String QUESO = "queso";
	public final String CEBOLLA = "cebolla";
	public final String HUEVO = "huevo";
	public final String MAYONESA = "mayonesa";
	public final String KETCHUP = "ketchup";
	public final String PAN_CON_CENTENO = "panConCenteno";
	public final String PAN_SIN_CENTENO = "panSinCenteno";
	public final String GASEOSA_CHICA = "gaseosaChica";
	public final String GASEOSA_MEDIANA = "gaseosaMediana";
	public final String GASEOSA_GRANDE = "gaseosaGrande";
	public final String GASEOSA_GINI = "gaseosaGini";
	public final String AGUA_SIN_GAS = "aguaSinGas";
	public final String CERVEZA = "cerveza";
	public final String PAPAS_MEDIANAS = "papasMedianas";
	public final String PAPAS_GRANDES = "papasGrandes";
	public final String PAPAS_SUPER = "papasSuper";

	public static Utils getSingletonInstance() {
		if (utils == null) {
			utils = new Utils();
		}
		return utils;
	}
	
	public String obtenerFactura(Combo combo) {
		return new Factura(combo).getDetalleFactura(combo);
	}
}
