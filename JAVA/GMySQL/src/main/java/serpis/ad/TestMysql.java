package serpis.ad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import serpis.ad.gui.Principal;

public class TestMysql {

	public static void main(String[] args)  throws SQLException{
		// TODO Auto-generated method stub
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			String user="root";
//			String passwd="sistemas";
//	        String connectionUrl = "jdbc:mysql://localhost/pruebas";
//	        Connection connection = DriverManager.getConnection(connectionUrl,user,passwd);
//	        
//	        Statement stmt = connection.createStatement();
//	        
//	        ResultSet rs = stmt.executeQuery("SELECT * from categoria");
//	        while(rs.next()){
//	            String id = rs.getString("id");
//	            String nom = rs.getString("nombre");
//	            System.out.println("ID : "+id);
//	            System.out.println("Nombre : "+nom);
//	            System.out.println("------------");
//	        }
//	        
//	        connection.close();
//			
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// -----------------------
		
//		Articulo articulo = new Articulo("1","JAVA","25.0","2");
//		Articulo.insert(articulo);
		
//		Articulo[] lista = Articulo.select();
//		
//		for (int i = 0; i < lista.length; i++) {
//			Articulo.print(lista[i]);
//		}
		
//		Articulo.print(Articulo.select("12"));
		
		Principal principal = new Principal();
		
	}

}
