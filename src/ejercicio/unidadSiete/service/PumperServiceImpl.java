package ejercicio.unidadSiete.service;

import java.util.List;

import ejercicio.unidadSiete.bd.PumperDAO;
import ejercicio.unidadSiete.datosContacto.DatosContacto;
import ejercicio.unidadSiete.producto.ExistenciaEnStock;
import ejercicio.unidadSiete.producto.Producto;
import ejercicio.unidadSiete.util.Utils;
import ejercicio.unidadSiete.venta.Venta;

public class PumperServiceImpl {

	private static PumperServiceImpl service;
	Utils util = Utils.getSingletonInstance();

	public static PumperServiceImpl getSingletonInstance() {
		if (service == null) {
			service = new PumperServiceImpl();
		}
		return service;
	}
	
	PumperDAO dao = new PumperDAO();
	
	public List<ExistenciaEnStock> consultarStockParcial() {
		return dao.consultarStockParcial();
	}

	public Boolean insertarContacto (DatosContacto datos) {
		return dao.insertarContacto(datos);
	}
	
	public void descontarProductosDelStock(List<Producto> productos) {
		
		List<ExistenciaEnStock> lista = consultarStockParcial();
			
		for (Producto producto : productos) {
			if (producto.getTipoProducto().equals(util.AGUA_SIN_GAS)) {
				dao.descontarAguaSinGas();
			}else if (producto.getTipoProducto().equals(util.CEBOLLA)){
				dao.descontarCebolla();
			}else if (producto.getTipoProducto().equals(util.CERVEZA)){
				dao.descontarCerveza();
			}else if (producto.getTipoProducto().equals(util.GASEOSA_CHICA)){
				dao.descontarGaseosaChica();
			}else if (producto.getTipoProducto().equals(util.GASEOSA_GINI)){
				dao.descontarGaseosaGini();
			}else if (producto.getTipoProducto().equals(util.GASEOSA_GRANDE)){
				dao.descontarGaseosaGrande();
			}else if (producto.getTipoProducto().equals(util.GASEOSA_MEDIANA)){
				dao.descontarGaseosaMediana();
			}else if (producto.getTipoProducto().equals(util.HAMBURGESA)){
				dao.descontarHamburguesa();
			}else if (producto.getTipoProducto().equals(util.HUEVO)){
				dao.descontarHuevo();
			}else if (producto.getTipoProducto().equals(util.JUGUETE_HOMBRE)){
				dao.descontarJugueteVaron();
			}else if (producto.getTipoProducto().equals(util.JUGUETE_MUJER)){
				dao.descontarJugueteMujer();
			}else if (producto.getTipoProducto().equals(util.KETCHUP)){
				dao.descontarKetchup();
			}else if (producto.getTipoProducto().equals(util.MAYONESA)){
				dao.descontarMayonesa();
			}else if (producto.getTipoProducto().equals(util.PAN_CON_CENTENO)){
				dao.descontarPanConCenteno();
			}else if (producto.getTipoProducto().equals(util.PAN_SIN_CENTENO)){
				dao.descontarPanSinCenteno();
			}else if (producto.getTipoProducto().equals(util.PAPAS_GRANDES)){
				dao.descontarPapasGrandes();
			}else if (producto.getTipoProducto().equals(util.PAPAS_MEDIANAS)){
				dao.descontarPapasMedianas();
			}else if (producto.getTipoProducto().equals(util.PAPAS_SUPER)){
				dao.descontarPapasSuper();
			}else {
				dao.descontarQueso();
			}
		}
		
		for (ExistenciaEnStock existenciaEnStock : lista) {
			if (existenciaEnStock.getCantidad() <= 10) {
				System.out.println("RENOVAR STOCK");
				util.grabarPedidoRenovacionStockEnArchivo();
				dao.asignarStockInicialYUnidadDeCadaProducto();
			}
		}
	}
	
	public void registrarVenta (Venta venta) {
		dao.registrarVenta(venta);
	}
	
	public void asignarStockInicialYUnidadDeCadaProducto() {
		dao.asignarStockInicialYUnidadDeCadaProducto();
	}
	
	public void createTableContacto() {
		dao.createTableContacto();
	}
	
	public List<Venta> getListaVentas(){
		return  dao.consultarVentas();
	}
	
}
