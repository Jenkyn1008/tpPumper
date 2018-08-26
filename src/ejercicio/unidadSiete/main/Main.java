package ejercicio.unidadSiete.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ejercicio.unidadSiete.Caja.CajaCuatro;
import ejercicio.unidadSiete.Caja.CajaUno;
import ejercicio.unidadSiete.Factura.Factura;
import ejercicio.unidadSiete.combo.ComboPumperFeliz;
import ejercicio.unidadSiete.combo.ComboPumperGrande;
import ejercicio.unidadSiete.combo.ComboPumperMediano;
import ejercicio.unidadSiete.combo.ComboPumperSuper;
import ejercicio.unidadSiete.datosContacto.DatosContacto;
import ejercicio.unidadSiete.factory.CargarProductosComboFactory;
import ejercicio.unidadSiete.producto.ExistenciaEnStock;
import ejercicio.unidadSiete.service.PumperServiceImpl;
import ejercicio.unidadSiete.util.Utils;
import ejercicio.unidadSiete.venta.Venta;

public class Main {

	public static void main(String[] args) {
		PumperServiceImpl service = PumperServiceImpl.getSingletonInstance();
		CargarProductosComboFactory factoryCombo = new CargarProductosComboFactory();
		Utils util = Utils.getSingletonInstance();
		Integer aux = 0;
		Scanner teclado = new Scanner(System.in);
		int auxCombos = 0;
		List<Venta> ventas = new ArrayList<Venta>();
		int auxCajas = 0;
		service.asignarStockInicialYUnidadDeCadaProducto();
		service.createTableContacto();
		
		do {
		
			System.out.println("Para ingresar tipo de combo: 1");
			System.out.println("Para ver stock parcial: 2");
			System.out.println("Reporte Parcial de ventas (Solo Jefe de ventas): 3");
			System.out.println("Cerrar Sistema: 4");
			System.out.print("Ingrese opcion: ");
			Integer opcionEntrada = teclado.nextInt();
			aux = opcionEntrada;
			
			if (opcionEntrada == 1) {
				System.out.println("Combo Pumper Feliz 1");
				System.out.println("Combo Pumper Mediano 2");
				System.out.println("Combo Pumper Grande 3");
				System.out.println("Combo Pumper Super 4");
				System.out.print("Ingrese opcion: ");
				Integer opcionEntradaCombo = teclado.nextInt();
				auxCombos++;
				auxCajas++;
				
				System.out.print("Ingrese nombre para promocion: ");
				String nombre = teclado.next();
				System.out.print("Ingrese apellido para promocion: ");
				String apellido = teclado.next();
				System.out.print("Ingrese fecha nacimiento ddmmaaa: ");
				String fechaNacimiento = teclado.next();
				System.out.print("Ingrese telefono (opcional): ");
				String tel = teclado.next();
				
				DatosContacto datos = new DatosContacto(nombre, apellido, fechaNacimiento);
				
				if (!tel.equals("")) {
					datos.setNroTelefono(tel);
				}
				
				service.insertarContacto(datos);
				
				if (opcionEntradaCombo == 1) { // COMBO FELIZ
					
					ComboPumperFeliz combo = new ComboPumperFeliz();
					System.out.print("Para ingresar genero juguete ingrese 1 Hombre / 2 Mujer: ");
					Integer opcionGeneroJuguete = teclado.nextInt();
					System.out.print("Para ingresar tipo de pan ingrese 1 c/centeno / 2 s/centeno: ");
					Integer opcionTipoPan = teclado.nextInt();
					System.out.print("Para ingresar tipo de bebida ingrese 1 c/agua / 2 c/gaseosa: ");
					Integer opcionTipoBebida = teclado.nextInt();
					
					Boolean jugueteHombre = Boolean.FALSE;
					Boolean opcionalCenteno = Boolean.FALSE;
					Boolean conAgua = Boolean.FALSE;
					
					if (opcionGeneroJuguete == 1) {
						jugueteHombre = Boolean.TRUE;
					}
					
					if (opcionTipoPan == 1) {
						opcionalCenteno = Boolean.TRUE;
					}
					
					if (opcionTipoBebida == 1) {
						conAgua = Boolean.TRUE;
					}
					
					combo.setProductosCombos(factoryCombo.obtenerProductosDelCombo(combo, jugueteHombre, opcionalCenteno, null, null, null, null, conAgua));
					Venta venta = new Venta(combo, datos, util.asignarCaja(auxCajas));
					service.descontarProductosDelStock(venta.getCombo().getProductosCombos());
					
					if (util.asignarCaja(auxCajas) instanceof CajaUno || util.asignarCaja(auxCajas) instanceof CajaCuatro) {
						Factura factura  = new Factura(combo);
						factura.getDetalleFactura(combo);
					}
					
				}else if (opcionEntradaCombo == 2) {// COMBO MEDIANO
					
					ComboPumperMediano combo = new ComboPumperMediano();
					combo.setProductosCombos(factoryCombo.obtenerProductosDelCombo(combo, null, null, null, null, null, null, null));
					Venta venta = new Venta(combo, datos, util.asignarCaja(auxCajas));
					service.descontarProductosDelStock(venta.getCombo().getProductosCombos());
					
					if (util.asignarCaja(auxCajas) instanceof CajaUno || util.asignarCaja(auxCajas) instanceof CajaCuatro) {
						Factura factura  = new Factura(combo);
						factura.getDetalleFactura(combo);
					}
					
				}else if (opcionEntradaCombo == 3) { // COMBO GRANDE
					
					ComboPumperGrande combo = new ComboPumperGrande();
					Boolean gaseosaGrande = Boolean.FALSE;
					Boolean condimentos = Boolean.FALSE;
					
					System.out.print("Para ingresar si el cliente desea gaseosa grande o mediana ingrese 1 Mediana / 2 Grande: ");
					Integer opcionGaseosaGrande = teclado.nextInt();
					System.out.print("Para ingresar si el cliente desea condimentos o NO ingrese 1 c/condimentos / 2 s/condimentos: ");
					Integer opcionConSinCondimento = teclado.nextInt();
					
					if (opcionGaseosaGrande == 2) {
						gaseosaGrande = Boolean.TRUE;
					}
					
					if (opcionConSinCondimento == 1) {
						condimentos = Boolean.TRUE;
					}
					
					combo.setProductosCombos(factoryCombo.obtenerProductosDelCombo(combo, null, null, condimentos, null, null, gaseosaGrande, null));
					Venta venta = new Venta(combo, datos, util.asignarCaja(auxCajas));
					service.descontarProductosDelStock(venta.getCombo().getProductosCombos());
					
					if (util.asignarCaja(auxCajas) instanceof CajaUno || util.asignarCaja(auxCajas) instanceof CajaCuatro) {
						Factura factura  = new Factura(combo);
						factura.getDetalleFactura(combo);
					}
					
				}else { // COMBO SUPER
					
					ComboPumperSuper combo = new ComboPumperSuper();
					Boolean opcionalCerveza = Boolean.FALSE;
					Boolean opcionalIngredientes = Boolean.FALSE;
					
					System.out.print("Para ingresar si el cliente desea gaseosa o cerveza ingrese 1 Gaseosa / 2 Cerveza: ");
					Integer opcionGaseosaCerveza = teclado.nextInt();
					System.out.print("Para ingresar si el cliente desea ingredientes o NO ingrese 1 c/ingredientes / 2 s/ingredientes: ");
					Integer opcionConSinIngredientes = teclado.nextInt();
					
					
					if (opcionGaseosaCerveza == 2) {
						opcionalCerveza = Boolean.TRUE;
					}
					
					if (opcionConSinIngredientes == 1) {
						opcionalIngredientes = Boolean.TRUE;
					}
					
					combo.setProductosCombos(factoryCombo.obtenerProductosDelCombo(combo, null, null, null, opcionalIngredientes, opcionalCerveza, null, null));
					Venta venta = new Venta(combo, datos, util.asignarCaja(auxCajas));
					service.descontarProductosDelStock(venta.getCombo().getProductosCombos());
					
					if (util.asignarCaja(auxCajas) instanceof CajaUno || util.asignarCaja(auxCajas) instanceof CajaCuatro) {
						Factura factura  = new Factura(combo);
						factura.getDetalleFactura(combo);
					}
					
				}
				
				if (auxCajas == 10) {
					auxCajas = 0;
				}
				
				if (auxCombos == 30) {
					util.grabarVentasEnArchivo(ventas);
					ventas.removeAll(ventas);
					auxCombos = 0;
				}
				
			}else if (opcionEntrada == 2) {
				List<ExistenciaEnStock> existenciaEnStockLista = service.consultarStockParcial();
				
				System.out.println("************************************************");
				System.out.println("**************** STOCK PARCIAL *****************");
	
				for (ExistenciaEnStock existenciaEnStock : existenciaEnStockLista) {
					System.out.println(existenciaEnStock.getNombre()+": " + existenciaEnStock.getCantidad());
				}
				
				System.out.println("************************************************");
				
			}else if (opcionEntrada == 3){
				System.out.print("Ingrese usuario: ");
				String user = teclado.next();
				System.out.print("Ingrese password: ");
				String pass = teclado.next();
				
				if (user.equals("") && pass.equals("")) {
					System.out.print("Digite 1 para obtener el reporte de ventas: ");
					Integer opcionReportea = teclado.nextInt();
					
					if (opcionReportea == 1) {
						
						for (Venta venta : service.getListaVentas()) {
							if (venta.getCombo() instanceof ComboPumperFeliz) {
								System.out.println(" Pumper Feliz: " + "$" + venta.getCombo().getPrecio() + "\n");
							}else if (venta.getCombo() instanceof ComboPumperMediano) {
								System.out.println(" Pumper Mediano: " + "$" + venta.getCombo().getPrecio() + "\n");
							}else if (venta.getCombo() instanceof ComboPumperGrande) {
								System.out.println(" Pumper Grande: " + "$" + venta.getCombo().getPrecio() + "\n");
							}else {
								System.out.println(" Pumper Super: " + "$" + venta.getCombo().getPrecio() + "\n");
							}
						}
						
					}else {
						System.out.println("Error al intentar recuperar los reportes.");
					}
				}else {
					System.out.println("ERROR DE AUTENTIFICACION");
				}
				
			}else {
				break;
			}
		}while (aux <= 4);
		teclado.close();
	}

}
