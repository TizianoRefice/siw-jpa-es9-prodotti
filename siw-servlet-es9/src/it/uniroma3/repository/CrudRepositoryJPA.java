package it.uniroma3.repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


public class CrudRepositoryJPA<T> implements CrudRepository<T>{
	private EntityManager em;
	private Class<T> entitaClasse; //ci serve farci passare la classe dell'oggetto.

	public CrudRepositoryJPA(EntityManager em, Class<T> entitaClasse) {
		this.em = em;
		this.entitaClasse = entitaClasse;

	}

	//la soluzione fatta da me presupponeva che ci fosse sempre un attributo id, cosa non sempre vera. 
	//Facciamo una cosa più didattica: il metodo getId potrebbe non esistere
	//sfruttiamo il fatto che ogni entità  ha @id, quindi c'è una qualche informazione che ci dice che quella variabile è la chiave.
	//mi cerco l'attributo che è stato marcato come id. --> ALLA FINE SI USANO DELLE LIBRERIE PER FARE QUESTO
	@Override
	public T save(T entita) {
		Method getId = null;
		T persistentEntity = null;
		try{
			getId = this.entitaClasse.getMethod("getId");
		}catch (NoSuchMethodException|SecurityException e) {
			e.printStackTrace();
		}
		try{
			if(getId.invoke(entita)==null){
				em.persist(entita);
				persistentEntity = entita;
			}
			else{
				persistentEntity = em.merge(entita);
			}
		}catch (IllegalAccessException|IllegalArgumentException|InvocationTargetException e) {
			e.printStackTrace();
		}
		return entita;
	}

	@Override
	public T findOne(Long id) {
		return em.find(this.entitaClasse, id);
	}

	@Override
	public List<T> findAll() {
		String className = this.entitaClasse.getName();
		TypedQuery<T> query = em.createQuery("select e from " + className + " e",this.entitaClasse);
		return query.getResultList();
	}

	@Override
	public void delete(T entità) {
		em.remove(entità);
	}

	@Override
	public void deleteAll() {
		String className = this.entitaClasse.getName();
		Query query = em.createQuery("delete from " + className);
		query.executeUpdate();

	}

	protected EntityManager getEm() {
		return em; //protected cosi lo usano solo per le classi che la estendono
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	

}
