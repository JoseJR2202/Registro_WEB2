package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	private static Conexion DB = new Conexion();
	private Connection conn;
	private PreparedStatement pstmt;
	private String urlDB = "jdbc:postgresql://localhost:5432/Login";
	private String userDB = "postgres";
	private String passDB = "Samuel0403"; 
	
	public Conexion() {
		// TODO Auto-generated constructor stub
		 try{

	           Class.forName("org.postgresql.Driver");
	           conn = DriverManager.getConnection(this.urlDB, this.userDB, this.passDB);
	      
		 }catch(Exception ex){
	    	   System.out.println(ex);
	     }
	}

	public static Conexion getInstances() {
		return DB;
	}
	
	public void dbPrepareStatement(String query, Object[] obj) {
		try {
			this.pstmt = this.conn.prepareStatement(query);
			this.pstmt.setString(1, (String) obj[0]);
			this.pstmt.setString(2, (String) obj[1]);
			this.pstmt.setString(3, (String) obj[2]);
			this.pstmt.setInt(4, (Integer.parseInt((String) obj[3])));
			this.pstmt.setString(5, (String) obj[4]);
			this.pstmt.setString(6, (String) obj[5]);
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
}
