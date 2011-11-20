/**
 * 
 */
package mconta.core.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * @author Marc
 *
 */
public abstract class GenericDAOImpl<Type> 
		extends HibernateDaoSupport implements GenericDAO<Type> {
		
	protected Class<Type> domainClass = getDomainClass();
    
    protected abstract Class<Type> getDomainClass();
    
    @Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    	
	@Override
	public void save(Type entity) throws Exception {
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
