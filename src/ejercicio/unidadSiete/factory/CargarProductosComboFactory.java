package ejercicio.unidadSiete.factory;

import java.util.ArrayList;
import java.util.List;

import ejercicio.unidadSiete.combo.Combo;
import ejercicio.unidadSiete.combo.ComboPumperFeliz;
import ejercicio.unidadSiete.combo.ComboPumperGrande;
import ejercicio.unidadSiete.combo.ComboPumperMediano;
import ejercicio.unidadSiete.producto.Producto;
import ejercicio.unidadSiete.util.Utils;

public class CargarProductosComboFactory {

	Utils util = Utils.getSingletonInstance();
	List<Producto> listaProductos = null;
	
	public List<Producto> obtenerProductosDelCombo(Combo combo, Boolean jugueteHombre, Boolean opcionalCenteno, Boolean opcionalCondimentos,
			Boolean opcionalIngredientes, Boolean opcionalCerveza, Boolean conGaseosaGrande, Boolean conAgua) {

		if (combo instanceof ComboPumperFeliz) {
			getProductosPumperFeliz(opcionalCenteno, conAgua, jugueteHombre);
		} else if (combo instanceof ComboPumperMediano) {
			getProductosPumperMediano();
		} else if (combo instanceof ComboPumperGrande) {
			getProductosPumperGrande(conGaseosaGrande, opcionalCondimentos);
		} else {
			getProductosPumperSuper(opcionalCerveza, opcionalIngredientes);
		}

		return listaProductos;
	}

	private void getProductosPumperFeliz(Boolean opcionalCenteno, Boolean conAgua, Boolean jugueteHombre) {
		listaProductos = new ArrayList<Producto>();
		
		listaProductos.add(new Producto(util.HAMBURGESA, 1));
		listaProductos.add(new Producto(util.KETCHUP, 1));
		listaProductos.add(new Producto(util.MAYONESA, 1));
		listaProductos.add(new Producto(util.QUESO, 1));
		
		if (opcionalCenteno) {
			listaProductos.add(new Producto(util.PAN_CON_CENTENO, 1));
		}else {
			listaProductos.add(new Producto(util.PAN_SIN_CENTENO, 1));
		}
		
		if (jugueteHombre) {
			listaProductos.add(new Producto(util.JUGUETE_HOMBRE, 1));
		}else {
			listaProductos.add(new Producto(util.JUGUETE_MUJER, 1));
		}
		
		if (conAgua) {
			listaProductos.add(new Producto(util.AGUA_SIN_GAS, 1));
		}else {
			listaProductos.add(new Producto(util.GASEOSA_CHICA, 1));
		}
	}

	private void getProductosPumperMediano() {
		listaProductos = new ArrayList<Producto>();
		
		listaProductos.add(new Producto(util.HAMBURGESA, 2));
		listaProductos.add(new Producto(util.QUESO, 2));
		listaProductos.add(new Producto(util.PAPAS_MEDIANAS, 1));
		listaProductos.add(new Producto(util.CEBOLLA, 1));
		listaProductos.add(new Producto(util.MAYONESA, 1));
		listaProductos.add(new Producto(util.KETCHUP, 1));
		listaProductos.add(new Producto(util.GASEOSA_GINI, 1));
		listaProductos.add(new Producto(util.GASEOSA_MEDIANA, 1));
		listaProductos.add(new Producto(util.PAN_CON_CENTENO, 1));
	}

	private void getProductosPumperGrande(Boolean gaseosaGrande, Boolean opcionalCondimentos) {
		listaProductos = new ArrayList<Producto>();
		
		listaProductos.add(new Producto(util.PAPAS_GRANDES, 1));
		listaProductos.add(new Producto(util.HAMBURGESA, 3));
		listaProductos.add(new Producto(util.QUESO, 4));
		if (opcionalCondimentos) {
			listaProductos.add(new Producto(util.MAYONESA, 1));
			listaProductos.add(new Producto(util.KETCHUP, 1));
		}
		
		if (gaseosaGrande) {
			listaProductos.add(new Producto(util.GASEOSA_GRANDE, 1));
		}else {
			listaProductos.add(new Producto(util.GASEOSA_MEDIANA, 1));
		}
		
		listaProductos.add(new Producto(util.PAN_CON_CENTENO, 1));
	}

	private void getProductosPumperSuper(Boolean opcionalCerveza, Boolean opcionalIngredientes) {
		listaProductos = new ArrayList<Producto>();
		
		listaProductos.add(new Producto(util.HAMBURGESA, 5));
		listaProductos.add(new Producto(util.HUEVO, 1));
		listaProductos.add(new Producto(util.PAN_CON_CENTENO, 1));
		listaProductos.add(new Producto(util.PAPAS_SUPER, 1));
		
		if (opcionalIngredientes) {
			listaProductos.add(new Producto(util.QUESO, 4));
			listaProductos.add(new Producto(util.MAYONESA, 1));
			listaProductos.add(new Producto(util.KETCHUP, 1));
			listaProductos.add(new Producto(util.CEBOLLA, 1));
			listaProductos.add(new Producto(util.HUEVO, 1));
		}
		
		if (opcionalCerveza) {
			listaProductos.add(new Producto(util.CERVEZA, 1));
		}else {
			listaProductos.add(new Producto(util.GASEOSA_GRANDE, 1));
		}
		
		listaProductos.add(new Producto(util.GASEOSA_GINI, 2));
	}

}
