package ejercicio.unidadSiete.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import ejercicio.unidadSiete.Caja.Caja;
import ejercicio.unidadSiete.Caja.CajaCinco;
import ejercicio.unidadSiete.Caja.CajaCuatro;
import ejercicio.unidadSiete.Caja.CajaDos;
import ejercicio.unidadSiete.Caja.CajaSeis;
import ejercicio.unidadSiete.Caja.CajaTres;
import ejercicio.unidadSiete.Caja.CajaUno;
import ejercicio.unidadSiete.Factura.Factura;
import ejercicio.unidadSiete.combo.Combo;
import ejercicio.unidadSiete.combo.ComboPumperFeliz;
import ejercicio.unidadSiete.combo.ComboPumperGrande;
import ejercicio.unidadSiete.combo.ComboPumperMediano;
import ejercicio.unidadSiete.venta.Venta;

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
	
	public void grabarVentasEnArchivo (List<Venta> ventas) {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("C:\\detalleVentas"+dateToString(new Date())+".txt");
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			for (Venta venta : ventas) {
				if (venta.getCombo() instanceof ComboPumperFeliz) {
					bfwriter.write(" Pumper Feliz: " + "$" + venta.getCombo().getPrecio() + "\n");
				}else if (venta.getCombo() instanceof ComboPumperMediano) {
					bfwriter.write(" Pumper Mediano: " + "$" + venta.getCombo().getPrecio() + "\n");
				}else if (venta.getCombo() instanceof ComboPumperGrande) {
					bfwriter.write(" Pumper Grande: " + "$" + venta.getCombo().getPrecio() + "\n");
				}else {
					bfwriter.write(" Pumper Super: " + "$" + venta.getCombo().getPrecio() + "\n");
				}
			}
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void grabarPedidoRenovacionStockEnArchivo () {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("C:\\pumper\\compras\\compra"+dateToString(new Date())+".txt");
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write("SE DEBE RENOVAR STOCK");
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public Caja asignarCaja (int auxiliar) {
		if (auxiliar == 1) {
			return new CajaUno();
		}else if (auxiliar == 2) {
			return new CajaDos();
		}else if (auxiliar == 3) {
			return new CajaUno();
		}else if (auxiliar == 4) {
			return new CajaTres();
		}else if (auxiliar == 5) {
			return new CajaUno();
		}else if (auxiliar == 6) {
			return new CajaCuatro();
		}else if (auxiliar == 7) {
			return new CajaCinco();
		}else if (auxiliar == 8) {
			return new CajaCuatro();
		}else if (auxiliar == 9) {
			return new CajaSeis();
		}else {
			return new CajaCuatro();
		}
	}
	
	private String dateToString (Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		return formatter.format(date);
	}
	
}
