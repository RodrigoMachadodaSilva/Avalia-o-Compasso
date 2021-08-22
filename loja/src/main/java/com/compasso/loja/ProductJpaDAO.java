package com.compasso.loja;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ProductJpaDAO {
	private static ProductJpaDAO instance;
	protected EntityManager entityManager;

	public static ProductJpaDAO getInstance(){
		if (instance == null){
			instance = new ProductJpaDAO();
		}

		return instance;
	}

	private ProductJpaDAO() {
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

	public Product getById(final int id) {
		return entityManager.find(Product.class, id);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		return entityManager.createQuery("FROM " +
				Product.class.getName()).getResultList();
	}

	public void persist(Product product) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(product);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void merge(Product product) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(product);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Product product) {
		try {
			entityManager.getTransaction().begin();
			product = entityManager.find(Product.class, product.getId());
			entityManager.remove(product);
			entityManager.getTransaction().commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removeById(final int id) {
		try {
			Product product = getById(id);
			remove(product);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
