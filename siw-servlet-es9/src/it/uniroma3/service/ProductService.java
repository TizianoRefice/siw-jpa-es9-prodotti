package it.uniroma3.service;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import java.util.List;


import it.uniroma3.model.Prodotto;

//dobbiamo far diventare il nostro Prodotto una entità, per poterlo
//poi salvare nel database
public class ProductService {

	//entity manager comme variabile d'istanza
	private EntityManager em;
	
	//l'entity manager andrebbe creato una volta sola e basta, quindi dovremmo spostarlo nel contesto dell'applicazione
	//così è a disposizione di tutti ed è implementato una volta sola
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

	public List<Prodotto> getProdotti() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazioneProdotto-unit");
		this.em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		TypedQuery<Prodotto> query = em.createNamedQuery("findAll", Prodotto.class);
		List<Prodotto> prodotti = query.getResultList();
		tx.commit();
		return prodotti;
	}

	public Prodotto getOneProduct(Long id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("esercitazioneProdotto-unit");
		this.em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Prodotto prodotto = em.find(Prodotto.class, id);
		tx.commit();
		return prodotto;
	}
}
