package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {

	// conectar com banco de dados 
	private static Connection coon = null;
	
	public static Connection getConnection() { 
	
		if (coon == null) {
			try {
				Properties props = loadProperties();
				String url =props.getProperty("dburl");
				coon = DriverManager.getConnection(url,props);
			}
			catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		} 
			return coon;		
	}
	
	public static void closeConnection() {
		if (coon != null) {
			try {
				// fechando conexao com banco de dados
				coon.close();
			}
			catch(SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	} 
	
	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")){
			Properties props = new Properties();
			props.load(fs);
			return props;
		} 
		catch (IOException e){
			throw new DbException(e.getMessage());
		}
	}
}
