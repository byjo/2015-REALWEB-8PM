package pm.eight.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericTypeResolver;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class AbstractRepository<T> {

	@Autowired
	private SessionFactory sessionFactory;
	private Class<T> genericType;

	@SuppressWarnings("unchecked")
	public AbstractRepository() {
		this.genericType = (Class<T>) GenericTypeResolver.resolveTypeArgument(getClass(), AbstractRepository.class);
	}

	Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void delete(T item) {
		getSession().delete(item);
	}

	public void save(T item) {
		getSession().flush();
		getSession().save(item);
	}

	public void update(T item) {
		getSession().saveOrUpdate(item);
	}

	@SuppressWarnings("unchecked")
	public T find(long l) {
		return (T) getSession().get(genericType, l);
	}
}
