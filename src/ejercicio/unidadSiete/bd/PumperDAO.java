package ejercicio.unidadSiete.bd;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import ejercicio.unidadSiete.util.Utils;

public class PumperDAO {
	
	private static Connection conexion = null;
	Utils util = Utils.getSingletonInstance();
	
	private Map<String, Double> unidadDeCadaIngrediente = new HashMap<String, Double>();
	
	public PumperDAO() {
		conexion = BDFactory.getConnection();
	}
	
	
	/**
	 *  Metodo que borra las tablas si existen, crea las tablas y llena el stock inicial
	 */
	public void asignarStockInicialYUnidadDeCadaProducto() {
		unidadDeCadaIngrediente.put(util.JUGUETE_MUJER, 1d);
		unidadDeCadaIngrediente.put(util.JUGUETE_HOMBRE, 1d);
		unidadDeCadaIngrediente.put(util.HAMBURGESA, 0.1d);
		unidadDeCadaIngrediente.put(util.QUESO, 0.02d);
		unidadDeCadaIngrediente.put(util.CEBOLLA, 0.02d);
		unidadDeCadaIngrediente.put(util.HUEVO, 0.02d);
		unidadDeCadaIngrediente.put(util.MAYONESA, 0.005d);
		unidadDeCadaIngrediente.put(util.KETCHUP, 0.005d);
		unidadDeCadaIngrediente.put(util.PAN_CON_CENTENO, 0.05d);
		unidadDeCadaIngrediente.put(util.PAN_SIN_CENTENO, 0.05d);
		unidadDeCadaIngrediente.put(util.GASEOSA_CHICA, ((0.75d)/12));
		unidadDeCadaIngrediente.put(util.GASEOSA_MEDIANA, ((1d)/12));
		unidadDeCadaIngrediente.put(util.GASEOSA_GRANDE, ((1.25d)/12));
		unidadDeCadaIngrediente.put(util.GASEOSA_GINI, 1d);
		unidadDeCadaIngrediente.put(util.AGUA_SIN_GAS, ((1d)/12));
		unidadDeCadaIngrediente.put(util.CERVEZA, ((1d)/12));
		unidadDeCadaIngrediente.put(util.PAPAS_MEDIANAS, 0.15d);
		unidadDeCadaIngrediente.put(util.PAPAS_GRANDES, 0.2d);
		unidadDeCadaIngrediente.put(util.PAPAS_SUPER, 0.25d);
		
		String dropSQL = "drop table producto cascade constraints";		
		 
		String createSQL = "create table producto (" + 
				"id integer not null generated always as identity (start with 1, increment by 1), " + 
				"nombre varchar(50) not null, " +
				"cantidad number(6,2) not null, " +
				"constraint primary_key primary key (id))";		
		 
		String insertSQL = "insert into producto (nombre, cantidad) values ('jugueteMujer', 100); " +
							"insert into producto (nombre, cantidad) values ('jugueteVaron', 100); " +
							"insert into producto (nombre, cantidad) values ('hamburguesa', 50); " +
							"insert into producto (nombre, cantidad) values ('queso', 50); " +
							"insert into producto (nombre, cantidad) values ('cebolla', 50); " +
							"insert into producto (nombre, cantidad) values ('huevo', 50); " +
							"insert into producto (nombre, cantidad) values ('mayonesa', 50); " +
							"insert into producto (nombre, cantidad) values ('ketchup', 50); " +
							"insert into producto (nombre, cantidad) values ('panConCenteno', 50); " +
							"insert into producto (nombre, cantidad) values ('panSinCenteno', 50); " +
							"insert into producto (nombre, cantidad) values ('gaseosaChica', 50); " +
							"insert into producto (nombre, cantidad) values ('gaseosaMediana', 50); " +
							"insert into producto (nombre, cantidad) values ('gaseosaGrande', 50); " +
							"insert into producto (nombre, cantidad) values ('gaseosaGini', 50); " +
							"insert into producto (nombre, cantidad) values ('aguaSinGas', 50); " +
							"insert into producto (nombre, cantidad) values ('cerveza', 50); " +
							"insert into producto (nombre, cantidad) values ('papasMedianas', 50); " +
							"insert into producto (nombre, cantidad) values ('papasGrandes', 50); " +
							"insert into producto (nombre, cantidad) values ('papasSuper', 50); ";		
	}
	
	/*
	 *  Metodos que descuentan ingredientes del combo
	 */
	public boolean descontarJugueteMujer() {
		return true;
	}

	public boolean descontarJugueteVaron() {
		return true;
	}
	
	public boolean descontarHamburguesa() {
		return true;
	}

	public boolean descontarQueso() {
		return true;
	}

	public boolean descontarCebolla() {
		return true;
	}

	public boolean descontarHuevo() {
		return true;
	}

	public boolean descontarPanConCenteno() {
		return true;
	}

	public boolean descontarPanSinCenteno() {
		return true;
	}

	public boolean descontarGaseosaMediana() {
		return true;
	}

	public boolean descontarGaseosaGrande() {
		return true;
	}

	public boolean descontarAguaSinGas() {
		return true;
	}

	public boolean descontarCerveza() {
		return true;
	}

	public boolean descontarPapasMedianas() {
		return true;
	}

	public boolean descontarPapasGrandes() {
		return true;
	}

	public boolean descontarPapasSuper() {
		return true;
	}
	
	/*
	 * Venta
	 * 
	 * TODO: Cambiar Object por combo
	 */
	public boolean registrarVenta(Object combo) {
		return true;
	}
	
	/*
	 * Reportes
	 * 
	 * TODO: reemplazar Object por algo mejor
	 */
	public Object consultarStockParcial() {
		return null;
	}
	
	public Object consultarVentas() {
		return null;
	}
	
}
