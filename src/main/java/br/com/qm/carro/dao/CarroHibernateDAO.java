package br.com.qm.carro.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.qm.carro.pojo.Carro;

public class CarroHibernateDAO {

	
	private EntityManager em;

	public CarroHibernateDAO() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("carros");
		this.em = factory.createEntityManager();
	}
	
	
	public boolean insereCarro(Carro carro) {
		
		em.getTransaction().begin();
		em.persist(carro);
		em.getTransaction().commit();
		
		return true;
	}
	
	public boolean removeCarro(String placa) {
		
		Carro carro = em.find(Carro.class, placa);
		
		if (carro == null) {
			return false;
		}

		em.getTransaction().begin();
		em.remove(carro);
		em.getTransaction().commit();
		
		return true;
	}
	
	public Carro consultaCarro(String placa) {
		return em.find(Carro.class, placa);
	}
	
	public boolean alteraCarro(Carro carro) {
		
		Carro carroDb = em.find(Carro.class, carro.getPlaca());
		carroDb.setCor(carro.getCor());
		carroDb.setMarca(carro.getMarca());
		carroDb.setModelo(carro.getModelo());
		carroDb.setVelMax(carro.getVelMax());
		
		
		em.getTransaction().begin();
		em.merge(carroDb);
		em.getTransaction().commit();
		
		return true;
	}
	
	public List<Carro> listarCarros() {
		Query query = em.createQuery("select c from Carro as c");
		return query.getResultList();
	}
	
	public List<Carro> listarCarrosPorFabricante(String marca) {
		Query query = em.createQuery("select c from Carro as c where c.marca = :marca");
		query.setParameter("marca", marca);
		
		return query.getResultList();
	}
	
}