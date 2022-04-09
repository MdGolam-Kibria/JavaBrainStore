package com.CrackCode.ORM_CUSTOM_repository_CONFIG;

import com.CrackCode.util.Utils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Kibria
 *  based on test on only CORE SPRING FRAMEWORK not for this spring boot
 */
@Transactional
public abstract class BaseRepository<T extends Object> {
	@Autowired
	@Qualifier(value = "sessionFactory")
	private SessionFactory sessionFactory;

	private Class<T> modelClass;

	protected Criteria getCriteria() {
		return getSession().createCriteria(getModelClass());
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	private Class<T> getModelClass() {
		if (modelClass == null) {
			ParameterizedType thisType = (ParameterizedType) getClass().getGenericSuperclass();
			this.modelClass = (Class<T>) thisType.getActualTypeArguments()[0];
		}
		return modelClass;
	}

	private String getDomainClassName() {
		return getModelClass().getName();
	}

	public void create(T t) {
		try {
			getSession().save(t);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public Integer createAndGetID(T t) {
		Integer id = 0;
		try {
			id = (Integer) getSession().save(t);
			return id;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return id;
	}

	public T persist(T entity) {
		try {
			getSession().save(entity);
			getSession().flush();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		return (T) getSession().get(getModelClass(), id);
	}

	@SuppressWarnings("unchecked")
	public T load(Serializable id) {
		return (T) getSession().load(getModelClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return getSession().createQuery("from " + getDomainClassName()).list();
	}

	public T update(T entity) {
		try {
			Session session = getSession();
			session.update(entity);
			session.flush();
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return entity;
	}

	public void delete(T t) {
		getSession().delete(t);
	}

	public void deleteById(Serializable id) {
		delete(load(id));
	}

	public void deleteAll() {
		getSession().createQuery("delete " + getDomainClassName()).executeUpdate();
	}

	public long count() {
		return (Long) getSession().createQuery("select count(*) from " + getDomainClassName()).uniqueResult();
	}

	public boolean exists(Serializable id) {
		return (get(id) != null);
	}

	public <T> T findSingleResultByNativeQuery(String sql, Class<T> eClass) {
		if (Utils.isEmpty(sql)) {
			return null;
		}
		List<T> result = getSession().createSQLQuery(sql).addEntity(eClass).setFirstResult(0).setMaxResults(1).list();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public <T> T findSingleResultByNativeQuery(String sql, List<Object> param) {
		if (Utils.isEmpty(sql)) {
			return null;
		}
		Query query = getSession().createSQLQuery(sql).setFirstResult(0).setMaxResults(1);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				query.setParameter(i, param.get(i));
			}
		}
		List<T> result = query.list();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public <T> T findById(Class<T> eClass, Serializable id) {
		if (id == null) {
			return null;
		}
		return (T) getSession().get(eClass, id);
	}

	public <T> T findSingleResultByNativeQuery(String sql, Class<T> eClass, List<Object> param) {
		if (Utils.isEmpty(sql)) {
			return null;
		}
		Query q = getSession().createSQLQuery(sql).addEntity(eClass).setFirstResult(0).setMaxResults(1);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		List<T> result = q.list();
		if (result != null && result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	public <T> List<T> findByNativeQuery(String sql, Class<T> eClass, List<Object> param) {

		if (sql == null) {
			return new ArrayList<>();
		}
		Query q = getSession().createSQLQuery(sql).addEntity(eClass);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}

		return q.list();
	}

	public <T> List<T> findByNativeQuery(String sql, List<Object> param) {

		if (sql == null) {
			return new ArrayList<>();
		}
		Query q = getSession().createSQLQuery(sql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}

		return q.list();
	}

	public Integer findCountByNativeQuery(String sql, List<Object> param) {
		if (Utils.isEmpty(sql)) {
			return 0;
		}

		Query q = getSession().createSQLQuery(sql);
		if (param != null && !param.isEmpty()) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}

		List<Object> counts = q.setFirstResult(0).setMaxResults(1).list();
		if (counts != null && !counts.isEmpty()) {
			return Utils.getInteger(counts.get(0));
		}

		return 0;
	}

	public int deleteByQuery(String sql, List<Object> param) {
		if (Utils.isEmpty(sql)) {
			return 0;
		}

		try {
			Query q = getSession().createSQLQuery(sql);
			if (param != null && param.size() > 0) {
				for (int i = 0; i < param.size(); i++) {
					q.setParameter(i, param.get(i));
				}
			}
			return q.executeUpdate();
		} catch (Throwable t) {
			t.printStackTrace();
			return 0;
		}
	}

	/**
	 * We can use below 2 methods after implement spring security
	 */
	/*public CustomUser getUserDetail() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth.getPrincipal() instanceof String) {
			return null;
		}
		CustomUser user = (CustomUser) auth.getPrincipal();
		return user;

	}

	public CustomUser isLoggedIn() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		CustomUser user;
		try {
			user = (CustomUser) auth.getPrincipal();
		} catch (ClassCastException ce) {
			return null;
		}
		return user;
	}*/

	public int updateByNativeQuery(String sql, Map<String, Object> paramMap) {
		if (Utils.isEmpty(sql)) {
			return 0;
		}

		Query q = getSession().createSQLQuery(sql);
		if (paramMap != null && paramMap.size() > 0) {
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				if (paramMap.get(entry.getKey()) instanceof Collection) {
					ArrayList list = (ArrayList) entry.getValue();
					q.setParameterList(entry.getKey(), list);
				} else {
					q.setParameter(entry.getKey(), entry.getValue());
				}
			}
		}
		return q.executeUpdate();
	}

	public int updateByNativeQuery(String sql, List<Object> param) {
		if (Utils.isEmpty(sql)) {
			return 0;
		}
		Query q = getSession().createSQLQuery(sql);
		if (param != null && param.size() > 0) {
			for (int i = 0; i < param.size(); i++) {
				q.setParameter(i, param.get(i));
			}
		}
		return q.executeUpdate();
	}

}
