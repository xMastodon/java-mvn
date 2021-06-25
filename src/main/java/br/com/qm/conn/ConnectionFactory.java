package br.com.qm.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory
{
	public Connection getConnection()
	{
		try
		{
			return DriverManager.getConnection(
			        "jdbc:postgresql://127.0.0.1:5432/qeepmoving", "postgres", "Raw2011#");			
		}
		catch (SQLException e)
		{
			System.err.println("Erro ao estabelecer conexão com o banco de dados!");
			System.err.println(e.getMessage());
		}
		return null;		
	}
}