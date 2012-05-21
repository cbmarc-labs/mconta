/**
 * 
 */
package mconta.domain.dao.impl;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import mconta.domain.dao.CrudDAO;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Component;

/**
 * CrudDAOImpl class
 * 
 * @author Marc
 */
@Component("crudDAO")
public class CrudDAOImpl<Type> 
		extends HibernateDaoSupport implements CrudDAO<Type> {
	
	/**
	 * logger
	 */
	private static Logger logger =
		    Logger.getLogger(CrudDAOImpl.class.getName());
			
	/**
	 * init()
	 * 
	 * @param factory
	 */
	@Autowired
    public void init(SessionFactory factory) {
        setSessionFactory(factory);
    }
    
	/**
	 * saveOrUpdate()
	 * 
	 * @author marc
	 */
	public void saveOrUpdate(Type entity) throws Exception {
		getHibernateTemplate().saveOrUpdate(entity);
		
	}

	/**
	 * deleteAll()
	 * 
	 * @author marc
	 */
	public void deleteAll(Set<Type> entity) throws Exception {
		getHibernateTemplate().deleteAll(entity);
		
	}

	/**
	 * get()
	 * 
	 * @author marc
	 */
	@SuppressWarnings("unchecked")
	public Type get(long id, String domainName) throws Exception {
		return (Type)getHibernateTemplate().get(domainName, id);
		
	}

	/**
	 * getAll()
	 * 
	 * @author marc
	 */
	@SuppressWarnings("unchecked")
	public List<Type> getAll(final String domainName) throws Exception {
		List<Type> list = getHibernateTemplate().find("from " + domainName);		
		
		return list;
	}

}
