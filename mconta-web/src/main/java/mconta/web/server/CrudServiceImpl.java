/**
 * 
 */
package mconta.web.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mconta.core.dao.GenericDAO;
import mconta.web.client.rpc.CrudService;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public abstract class CrudServiceImpl<Type> 
	extends SpringRemoteServiceServlet implements CrudService<Type> {
	
	@Autowired
	protected GenericDAO<Type> dao;

	public void save(Type entity) throws Exception {
		dao.save(entity);
		
	}

	public Type get(long id) throws Exception {
		return (Type) dao.get(id);
		
	}

	public List<Type> getAll() throws Exception {
		List<Type> all = dao.getAll();
		
		return all;
	}

	public void remove(Type entity) throws Exception {
		dao.remove(entity);
		
	}

}
