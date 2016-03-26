package br.com.sidoc.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	public Connection getConnection(){
		//Class.forName("com.mysql.jdbc.Driver"); 
		try {	
			DriverManager.registerDriver(new com.mysql.jdbc.Driver()); //Essa linha foi a diferenca
			//return DriverManager.getConnection("jdbc:mysql://mysql.hostinger.com.br/u619621041_sidoc", "u619621041_sidoc","adc123");
			//return DriverManager.getConnection("jdbc:mysql://sql5.freesqldatabase.com/sql5112157", "sql5112157","iq1eFQQ1sA");
			return DriverManager.getConnection("jdbc:mysql://localhost/sidoc", "root","adc123");
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex);
		}catch (Exception e){
            throw new RuntimeException(e);
        }
	}
}
