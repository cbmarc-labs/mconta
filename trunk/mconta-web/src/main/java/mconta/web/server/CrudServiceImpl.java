/**
 * 
 */
package mconta.web.server;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mconta.core.dao.CrudDAO;
import mconta.core.persistence.Model;
import mconta.core.persistence.User;
import mconta.core.persistence.UserGroup;
import mconta.web.client.rpc.CrudService;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class CrudServiceImpl
	extends SpringRemoteServiceServlet implements CrudService {	
		
	@Autowired
	protected CrudDAO<Model> dao;

	public void saveOrUpdate(Model model) throws Exception {		
		try {
			dao.saveOrUpdate(model);
			
		} catch(Exception e) {
			throw new Exception(e);
			
		}
		
	}

	public Model get(long id, String model) throws Exception {
		Model entity = null;
		
		try {
			entity = dao.get(id, model);
			
		} catch(Exception e) {
			throw new Exception(e);
		}
			
		return entity;
		
	}

	public List<Model> getAll(String model) throws Exception {
		List<Model> all = null;

		try {
			all = dao.getAll(model);
			
		} catch(Exception e) {
			throw new Exception(e);
			
		}
		
		return all;
		
	}

	public void deleteAll(Set<Model> model) throws Exception {
		try {
			dao.deleteAll(model);
			
		} catch(Exception e) {
			throw new Exception(e);
			
		}
		
	}
}
