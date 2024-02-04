package infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<E> {

	private static EntityManagerFactory emf;
	protected EntityManager em;
	private Class<E> classDAO;

	static {
		try {
			emf = Persistence.createEntityManagerFactory("exercises-jpa");
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
		}
	}

	public DAO() {
		this(null);
	}

	public DAO(Class<E> classDAO) {
		this.classDAO = classDAO;
		this.em = emf.createEntityManager();
	}

	public DAO<E> openTransaction() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> closeTransaction() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> insert(E entity) {
		em.persist(entity);
		return this;
	}

	public DAO<E> insertAtomic(E entity) {
		return this.openTransaction().insert(entity).closeTransaction();
	}
	
	public E getById(long id){
		return em.find(classDAO, id);
	}

	public List<E> getAll() {
		return this.getAll(10, 0);
	}

	public List<E> getAll(int limit, int offset) {
		if (classDAO == null) {
			throw new UnsupportedOperationException("Class is null.");
		}

		String jpql = "SELECT e FROM " + classDAO.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, classDAO);
		query.setMaxResults(limit);
		query.setFirstResult(offset);
		return query.getResultList();
	}

	public void close() {
		em.close();
	}
}
