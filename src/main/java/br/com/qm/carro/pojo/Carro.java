package br.com.qm.carro.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Carro
{
	// @Id é para identificar a chave primária.
	@Id
	private String placa;
	private String cor;
	private String marca;
	private String modelo;	
	//@Column é para indicar o nome da coluna, se não usar,
	// o Hibernate faz uma correspondência entre o 
	// nome do atributo e a coluna.
	@Column(name = "vel_max")
	private float velMax;		
	public Carro(String placa, String cor, String marca, String modelo, float velMax)
	{
		this.placa = placa;
		this.cor = cor;
		this.marca = marca;
		this.modelo = modelo;
		this.velMax = velMax;
	}
	public String getPlaca()
	{
		return placa;
	}
	public void setPlaca(String placa)
	{
		this.placa = placa;
	}

	public String getCor()
	{
		return cor;
	}
	public void setCor(String cor)
	{
		this.cor = cor;
	}
	public String getMarca()
	{
		return marca;
	}
	public void setMarca(String marca)
	{
		this.marca = marca;
	}
	public String getModelo()
	{
		return modelo;
	}
	public void setModelo(String modelo)
	{
		this.modelo = modelo;
	}
	public float getVelMax()
	{
		return velMax;
	}
	public void setVelMax(float velMax)
	{
		this.velMax = velMax;
	}
	@Override
	public String toString()
	{
		return "Carro [placa=" + placa + ", cor=" + cor + ", marca=" + marca + ", modelo=" + modelo + ", velMax="
				+ velMax + "]";
	}	
}