package br.com.qm.carro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.qm.carro.pojo.Carro;
import org.postgresql.core.ConnectionFactory;

public class CarroDAO
{	
	private Connection conn;
	
	public CarroDAO()
	{
		this.conn = new ConnectionFactory().getConnection();
	}
	
	public boolean insereCarro(Carro carro)
	{		
		String sql = "INSERT INTO carros.carro"
				+ "	(placa, cor, marca, modelo)"
				+ "	VALUES (?, ?, ?, ?)";		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, carro.getPlaca());
			stmt.setString(2, carro.getCor());
			stmt.setString(3, carro.getMarca());
			stmt.setString(4, carro.getModelo());
			
			stmt.execute();			
			stmt.close();
		} 
		catch (SQLException e)
		{
			System.err.println("Erro ao criar um carro!");
			System.err.println(e.getMessage());
			return false;
		}		
		return true;
	}	
	public boolean removeCarro(String placa)
	{
		
		String sql = "delete from carros.carro where placa = ?";
		PreparedStatement stmt;
		int qtdLinhas = 0;
		
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, placa);
			
			qtdLinhas = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e)
		{
			System.err.println("Erro ao remover um carro!");
			System.err.println(e.getMessage());
		}		
		return qtdLinhas > 0;
	}	
	public Carro consultaCarro(String placa)
	{		
		String sql = "select * from carros.carro where placa = ?";
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, placa);			
			ResultSet rs = stmt.executeQuery();			
			if (rs.next())
			{				
				Carro carro = new Carro();				
				carro.setPlaca(rs.getString("placa"));
				carro.setCor(rs.getString("cor"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));				
				stmt.close();
				return carro;
			}
		}
		catch (SQLException e)
		{
			System.err.println("Erro ao consultar um carro!");
			System.err.println(e.getMessage());
		}	
		return null;
	}	
	public List<Carro> listarCarros()
	{		
		List<Carro> carros = new ArrayList<Carro>();
		String sql = "select * from carros.carro";		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();			
			while (rs.next())
			{
				Carro carro = new Carro();				
				carro.setPlaca(rs.getString("placa"));
				carro.setCor(rs.getString("cor"));
				carro.setMarca(rs.getString("marca"));
				carro.setModelo(rs.getString("modelo"));				
				carros.add(carro);
			}			
			stmt.close();
		} catch (SQLException e)
		{
			System.err.println("Erro ao listar carros!");
			System.err.println(e.getMessage());
		}	
		return carros;
	}
}