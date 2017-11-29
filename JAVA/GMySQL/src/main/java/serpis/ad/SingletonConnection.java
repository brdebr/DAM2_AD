package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
	
	private static Connection connection;
	private static SingletonConnection instancia = new SingletonConnection();
    
    
    
	public SingletonConnection() {
		try {
			String user="root";
			String passwd="sistemas";
	        String connectionUrl = "jdbc:mysql://localhost/pruebas";
	        connection = DriverManager.getConnection(connectionUrl,user,passwd);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void setConnection(Connection connection) {
		SingletonConnection.connection = connection;
	}

	public static SingletonConnection getInstancia() {
		return instancia;
	}

	public static void setInstancia(SingletonConnection instancia) {
		SingletonConnection.instancia = instancia;
	}
    
    
    
	
}
