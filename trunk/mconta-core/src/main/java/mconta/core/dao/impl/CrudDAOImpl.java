/**
 * 
 */
package mconta.core.dao.impl;

import java.util.List;

import mconta.core.dao.CrudDAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marc
 *
 */
public abstract class CrudDAOImpl<Type> 
		extends HibernateDaoSupport implements CrudDAO<Type> {
		
	protected Class<Type> domainClass = getDomainClass();
    
    protected abstract Class<Type> getDomainClass();
    
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    	
	@Override
	@Transactional
	public void save(Type entity) throws Exception {
		getHibernateTemplate().persist(entity);
		getHibernateTemplate().save(entity);
		
	}

	@Override
	public void remove(Type entity) throws Exception {
		getHibernateTemplate().delete(entity);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Type get(long id) throws Exception {
		return (Type)getHibernateTemplate().load(getDomainClass(), id);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAll() throws Exception {
		List<Type> all = getHibernateTemplate().
				find("from " + domainClass.getName());
		
		return all;
	}

	@Override
	public void update(Type entity) throws Exception {
		getHibernateTemplate().update(entity);
		
	}


}
