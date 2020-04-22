import java.sql.*;

public class Database {
	
	final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	final String DB_URL = "jdbc:mysql://localhost/student_ams";
	final String USER = "root";
	final String PASS = "ESWAReswar2";
	Connection conn = null;
	public Statement stmt = null;
	public Database() {
		try{

			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet executeQuery(String sql) throws ArithmeticException {
		
			try {
				stmt = conn.createStatement();
				boolean status = stmt.execute(sql);
				if(status) {
					return stmt.getResultSet();
				}
				else {
					int count = stmt.getUpdateCount();
	                System.out.println("Records updated: "+count);
	                return null;
				}
				
			} catch (SQLIntegrityConstraintViolationException e) {
				throw new ArithmeticException(e.getMessage());
			}catch(SQLException e) {
				e.printStackTrace();
			}
			return null;
	}
	

}
