package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

public class Conexion {
	private static Conexion DB = new Conexion();
	private Connection conn;
	private PreparedStatement pstmt;
	private Properties propiedades = new Properties();
	private Statement stmt;
	private ResultSet rs;
	public Conexion() {
		// TODO Auto-generated constructor stub
		 try{
			   propiedades.load(this.getClass().
			    getResourceAsStream("/propiedades/propiedades.properties"));
	           Class.forName("org.postgresql.Driver");
	           conn = DriverManager.getConnection(propiedades.getProperty("host")+"/"
	        		   		+propiedades.getProperty("nameBD"), propiedades.getProperty("Usuario"),propiedades.getProperty("pass"));
	      
		 }catch(Exception ex){
	    	   System.out.println(ex);
	     }
	}

	public static Conexion getInstances() {
		return DB;
	}
	
	public void dbPrepareStatement(String query, Object... obj) {
		try {
			this.pstmt = this.conn.prepareStatement(query);
			int i=0;
			for(Object algo:obj) {
				if(algo instanceof java.lang.String)
					this.pstmt.setString(++i, (String) algo);
				else
					this.pstmt.setInt(++i, (int) algo);
			}
			this.pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				this.pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public ArrayList<String> dbStatement(String query ) { 
		try {  
			this.stmt = this.conn.createStatement(); 
			this.rs = this.stmt.executeQuery(query);
			ArrayList<String> datos_usuario=new ArrayList<String>();
			while(rs.next()) {
			for(int i=0;i<rs.getMetaData().getColumnCount();i++)
				datos_usuario.add(rs.getString(++i));
			}
			if(datos_usuario.get(0)!=null)
				return datos_usuario;
			else
				return null;
		} catch (SQLException e) { 
			e.printStackTrace(); 
		}finally { 
			try { 
				this.stmt.close(); 
				this.rs.close(); 
			} catch (SQLException e) { 
				e.printStackTrace(); 
			} 
		} 
		return null; 
	}
}
