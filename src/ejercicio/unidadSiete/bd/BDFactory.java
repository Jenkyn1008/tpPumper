package ejercicio.unidadSiete.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDFactory {
	
	private static Connection instance = null;

	private BDFactory() {

		try	{
			String dbURL = "jdbc:derby:tpPumper;create=true;user=me;password=mine";
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            //Get a connection
            instance = DriverManager.getConnection(dbURL); 
		    instance.setAutoCommit(false);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	public synchronized static Connection getConnection() {
		return instance;
	}
}
