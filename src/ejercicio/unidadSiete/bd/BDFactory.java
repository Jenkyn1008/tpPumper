package ejercicio.unidadSiete.bd;

import java.sql.Connection;
import java.sql.DriverManager;

public class BDFactory {
	
	private static BDFactory bdFactory = null;
	private static Connection instancia = null;

	private BDFactory() {

		try	{
			String dbURL = "jdbc:derby:tpPumper;create=true";
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
			instancia = DriverManager.getConnection(dbURL); 
			instancia.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	public synchronized static Connection getConexion() {
		if (bdFactory == null) {
			bdFactory = new BDFactory();
		}
		
		return instancia;
	}
}
