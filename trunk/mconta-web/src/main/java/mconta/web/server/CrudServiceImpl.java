/**
 * 
 */
package mconta.web.server;

import java.util.List;

import mconta.core.dao.CrudDAO;
import mconta.web.client.rpc.CrudService;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public abstract class CrudServiceImpl<Type> 
	extends SpringRemoteServiceServlet implements CrudService<Type> {
		
	protected CrudDAO<Type> dao = getDao();
    
    protected abstract CrudDAO<Type> getDao();

	public void save(Type entity) throws Exception {
		checkLoggedIn();
		
		dao.save(entity);
		
	}

	public Type get(long id) throws Exception {
		checkLoggedIn();
		
		return (Type) dao.get(id);
		
	}

	public List<Type> getAll() throws Exception {
		checkLoggedIn();
		
		List<Type> all = dao.getAll();
		
		return all;
	}

	public void remove(Type entity) throws Exception {
		checkLoggedIn();
		
		dao.remove(entity);
		
	}

}
