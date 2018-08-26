package ejercicio.unidadSiete.bd;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ejercicio.unidadSiete.datosContacto.DatosContacto;
import ejercicio.unidadSiete.producto.ExistenciaEnStock;
import ejercicio.unidadSiete.util.Utils;
import ejercicio.unidadSiete.venta.Venta;

public class PumperDAO {
	
	private static Connection conexion = null;
	Utils util = Utils.getSingletonInstance();
	
	private Map<String, Double> unidadDeCadaIngrediente = new HashMap<String, Double>();
	
	public PumperDAO() {
		conexion = BDFactory.getConexion();
	}
	
	
	/**
	 *  Metodo que borra las tablas si existen, crea las tablas y llena el stock inicial
	 */
	public void asignarStockInicialYUnidadDeCadaProducto() {
		try {
			Statement consulta = null;
			
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
			
			// Drop de la tabla PRODUCTO
		    DatabaseMetaData metadata = conexion.getMetaData();
		    ResultSet resultado = metadata.getTables(null, null, "producto", null);
		    if (resultado.next()) {
				String dropTableProductoSQL = "drop table producto cascade constraints";		
				consulta = conexion.createStatement();
				consulta.execute(dropTableProductoSQL);
				consulta.close();
		    }
			 
			// Create de la tabla PRODUCTO
		    String createTableProductoSQL = "CREATE TABLE producto " +
					    					"(idProducto INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " + 
				    						"  nombre VARCHAR(50) NOT NULL, " + 
			    							"  cantidad NUMERIC(6, 2), " + 
			    							"  CONSTRAINT primary_key PRIMARY KEY (idProducto))";
			consulta = conexion.createStatement();
			consulta.execute(createTableProductoSQL);
			consulta.close();
			 
			// Inserts para stock inicial de la tabla PRODUCTO
			String insertStockInicialSQL = "insert into producto (nombre, cantidad) values ('" + util.JUGUETE_MUJER + "', 100), " +
																							"('" + util.JUGUETE_HOMBRE + "', 100), " +
																							"('" + util.HAMBURGESA + "', 50), " +
																							"('" + util.QUESO + "', 50), " +
																							"('" + util.CEBOLLA + "', 50), " +
																							"('" + util.HUEVO + "', 50), " +
																							"('" + util.MAYONESA + "', 50), " +
																							"('" + util.KETCHUP + "', 50), " +
																							"('" + util.PAN_CON_CENTENO + "', 50), " +
																							"('" + util.PAN_SIN_CENTENO + "', 50), " +
																							"('gaseosa', 50), " +
																							"('" + util.GASEOSA_GINI + "', 50), " +
																							"('" + util.AGUA_SIN_GAS + "', 50), " +
																							"('" + util.CERVEZA + "', 50), " +
																							"('papas', 50)";		
			consulta = conexion.createStatement();
			consulta.execute(insertStockInicialSQL);
			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createTableContacto() {
		Statement consulta = null;
		
		try {
			// Create de la tabla DATOS CONTACTO
			String createTableContactoSQL = "CREATE TABLE producto " +
					"(idContacto INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " + 
					"  nombre VARCHAR(150) NOT NULL, " +
					"  apellido VARCHAR(150) NOT NULL, " +
					"  fechaNacimiento VARCHAR(8) NOT NULL, " +
					"  telefono VARCHAR(10), " +
					"  CONSTRAINT primary_key PRIMARY KEY (idContacto))";
			
			consulta = conexion.createStatement();
			consulta.execute(createTableContactoSQL);
			consulta.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 *  Metodos que descuentan ingredientes del combo
	 */
	public boolean insertarContacto(DatosContacto datos) {
		try {
			Statement consulta = conexion.createStatement();
			
			StringBuilder sb = new StringBuilder();
			
			if (!datos.getNroTelefono().equals("")) {
				sb.append("insert into producto (nombre, apellido, fechaNacimiento, telefono) values");
			}else {
				sb.append("insert into producto (nombre, apellido, fechaNacimiento) values");
			}
			sb.append("(");
			sb.append("'"+datos.getNombre()+"',");
			sb.append("'"+datos.getApellido()+"',");
			sb.append("'"+datos.getFechaNacimiento()+"',");
			if (!datos.getNroTelefono().equals("")) {
				sb.append("'"+datos.getNroTelefono()+"'");
			}
			
			ResultSet resultado = consulta.executeQuery(sb.toString());
			
			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");
				
				cantidad -= unidadDeCadaIngrediente.get(util.JUGUETE_HOMBRE);
				
				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.JUGUETE_HOMBRE + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			
			
			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean descontarJugueteMujer() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.JUGUETE_MUJER + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.JUGUETE_MUJER);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.JUGUETE_MUJER + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	

	public boolean descontarJugueteVaron() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.JUGUETE_HOMBRE + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.JUGUETE_HOMBRE);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.JUGUETE_HOMBRE + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	public boolean descontarHamburguesa() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.HAMBURGESA + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.HAMBURGESA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.HAMBURGESA + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarQueso() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.QUESO + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.QUESO);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.QUESO + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarCebolla() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.CEBOLLA + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.CEBOLLA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.CEBOLLA + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarHuevo() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.HUEVO + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.HUEVO);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.HUEVO + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarMayonesa() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.MAYONESA + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.MAYONESA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.MAYONESA + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarKetchup() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.KETCHUP + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.KETCHUP);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.KETCHUP + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarPanConCenteno() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.PAN_CON_CENTENO + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.PAN_CON_CENTENO);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.PAN_CON_CENTENO + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarPanSinCenteno() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.PAN_SIN_CENTENO + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.PAN_SIN_CENTENO);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.PAN_SIN_CENTENO + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarGaseosaChica() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='gaseosa'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.GASEOSA_CHICA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='gaseosa'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarGaseosaMediana() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='gaseosa'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.GASEOSA_MEDIANA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='gaseosa'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarGaseosaGrande() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='gaseosa'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.GASEOSA_GRANDE);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='gaseosa'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarGaseosaGini() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.GASEOSA_GINI + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.GASEOSA_GINI);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.GASEOSA_GINI + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarAguaSinGas() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.AGUA_SIN_GAS + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.AGUA_SIN_GAS);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.AGUA_SIN_GAS + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarCerveza() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='" + util.CERVEZA + "'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.CERVEZA);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='" + util.CERVEZA + "'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarPapasMedianas() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='papas'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.PAPAS_MEDIANAS);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='papas'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarPapasGrandes() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='papas'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.PAPAS_GRANDES);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='papas'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	public boolean descontarPapasSuper() {
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectCantidadSQL = "SELECT cantidad FROM producto WHERE nombre='papas'";
			
			ResultSet resultado = consulta.executeQuery(selectCantidadSQL);

			if (resultado.next()) {
				Double cantidad = resultado.getDouble("cantidad");

				cantidad -= unidadDeCadaIngrediente.get(util.PAPAS_SUPER);

				Statement subConsulta = conexion.createStatement();
				String updateSQL = "UPDATE producto SET cantidad=" + cantidad + " WHERE nombre='papas'";
				subConsulta.execute(updateSQL);
				subConsulta.close();
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}
	
	/*
	 * Venta
	 * 
	 * TODO: Cambiar Object por combo
	 */
	public boolean registrarVenta(Venta venta) {
		return true;
	}
	
	/*
	 * Reportes
	 * 
	 * TODO: reemplazar Object por algo mejor
	 */
	public List<ExistenciaEnStock> consultarStockParcial() {
		List<ExistenciaEnStock> respuesta = null;
		
		try {
			Statement consulta = conexion.createStatement();
		    
			String selectStockParcialSQL = "SELECT * FROM producto";
			
			ResultSet resultado = consulta.executeQuery(selectStockParcialSQL);

			respuesta = new ArrayList<ExistenciaEnStock>();
			while (resultado.next()) {
				ExistenciaEnStock existenciaEnStock = new ExistenciaEnStock();
				
				existenciaEnStock.setNombre(resultado.getString("nombre"));
				existenciaEnStock.setCantidad(resultado.getDouble("cantidad"));
				
				respuesta.add(existenciaEnStock);
			}			

			consulta.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return respuesta;
	}
	
	public List<Venta> consultarVentas() {
		return null;
	}
	
}
