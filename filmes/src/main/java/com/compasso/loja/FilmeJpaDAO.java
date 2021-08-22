package com.compasso.loja;

import java.awt.print.PageFormat;
import java.awt.print.Pageable;
import java.awt.print.Printable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class FilmeJpaDAO {
	private static FilmeJpaDAO instance;
	protected EntityManager entityManager;

	public static FilmeJpaDAO getInstance(){
		if (instance == null){
			instance = new FilmeJpaDAO();
		}

		return instance;
	}

	private FilmeJpaDAO() {
		entityManager = getEntityManager();
	}

	private EntityManager getEntityManager() {
		EntityManagerFactory factory =
				Persistence.createEntityManagerFactory("Default");
		if (entityManager == null) {
			entityManager = factory.createEntityManager();
		}

		return entityManager;
	}

	public Filme getById(final int id) {
		return entityManager.find(Filme.class, id);
	}
	
	public List<Filme> findWithLimit(int limit, int offset) {
		Query query = entityManager.createQuery("FROM " + Filme.class.getName());
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Filme> findAll() {
		return entityManager.createQuery("FROM " +
				Filme.class.getName()).getResultList();
	}

	public void persist(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Filme filme) {
		try {
			entityManager.getTransaction().begin();
			filme = entityManager.find(Filme.class, filme.getId());
			entityManager.remove(filme);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Filme filme = getById(id);
			remove(filme);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
