package ejercicio.unidadSiete.service;

import ejercicio.unidadSiete.bd.PumperDAO;

public class PumperServiceImpl {

	private static PumperServiceImpl service;

	public static PumperServiceImpl getSingletonInstance() {
		if (service == null) {
			service = new PumperServiceImpl();
		}
		return service;
	}
	
	PumperDAO dao = new PumperDAO();
	
	public String obtenerStockParcial() {
		dao.consultarStockParcial();
		return null;
	}

}
