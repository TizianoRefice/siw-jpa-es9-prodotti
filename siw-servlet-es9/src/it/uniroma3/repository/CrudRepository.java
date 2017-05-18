package it.uniroma3.repository;

import java.util.List;


public interface CrudRepository<T> {
	public T save(T entità);
	public T findOne(Long id);
	public List<T> findAll();
	public void delete(T entità);
	public void deleteAll(); 
}
//inoltre ci sono dei find che sono relativi alla applicazione che stiamo costruendo, per rendere piu'efficiente il tutto.
//ad esempio: trova tutti gli artisti morti, non carico tutti gli artisti. fai una classe che estende crudRepository ed
//implemtenta i medoti aggiuntivi che mi servono.