/**
 * 
 */
package mconta.web.server;

import java.util.List;
import java.util.Set;

import mconta.domain.dao.CrudDAO;
import mconta.domain.model.Model;
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
		List<Model> list = null;
		
		try {
			list = dao.getAll(model);
			
			for(Model m: list)
				m.deHibernate();
			
		} catch(Exception e) {
			throw new Exception(e);
			
		}
		
		return list;
		
	}

	public void deleteAll(Set<Model> model) throws Exception {
		try {
			dao.deleteAll(model);
			
		} catch(Exception e) {
			throw new Exception(e);
			
		}
		
	}
}
