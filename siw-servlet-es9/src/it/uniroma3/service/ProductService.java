package it.uniroma3.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import it.uniroma3.model.Prodotto;

//dobbiamo far diventare il nostro Prodotto una entità, per poterlo
//poi salvare nel database
public class ProductService {

	//entity manager comme variabile d'istanza
	private EntityManager em;
	
	public Prodotto inserisciProdotto(Prodotto prodotto) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazioneProdotto-unit");
		//ProductRepository productRepository = new ProductRepository(this.em);
		//productRepository.save(prodotto); //poi ha fatto il persist da qui dentro a causa di errori --> da risolvere
		this.em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(prodotto);
		tx.commit();
		em.close();
		emf.close();
		return prodotto;
	}
}
