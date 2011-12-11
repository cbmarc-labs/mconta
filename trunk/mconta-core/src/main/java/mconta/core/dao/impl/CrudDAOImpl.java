/**
 * 
 */
package mconta.core.dao.impl;

import java.util.List;
import java.util.Set;

import mconta.core.dao.CrudDAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marc
 *
 */
@Repository("crudDAO")
public class CrudDAOImpl<Type> 
		extends HibernateDaoSupport implements CrudDAO<Type> {
	
	@Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    	
	@Override
	@Transactional
	public void saveOrUpdate(Type entity) throws Exception {
		getHibernateTemplate().saveOrUpdate(entity);
		
	}

	@Override
	@Transactional
	public void deleteAll(Set<Type> entity) throws Exception {
		getHibernateTemplate().deleteAll(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Type get(long id, String domainName) throws Exception {
		return (Type)getHibernateTemplate().get(domainName, id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAll(String domainName) throws Exception {
		List<Type> all = null;

		all = getHibernateTemplate().find("from " + domainName);
		
		return all;
	}

}
