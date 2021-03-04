package helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	private static Conexion DB = new Conexion();
	private Connection conn;
	private PreparedStatement pstmt;
	private String urlDB = "jdbc:postgresql://ec2-52-50-171-4.eu-west-1.compute.amazonaws.com:5432/dathvivntlm28p";
	private String userDB = "ussgyalqqoythj";
	private String passDB = "7b331340eef44da201988ff922ce0c985f79d8b14653b75cdff00a10aa00cdf6"; 
	
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
