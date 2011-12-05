/**
 * 
 */
package mconta.web.server;

import java.util.List;

import mconta.core.dao.CrudDAO;
import mconta.core.persistence.Model;
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

	public void save(Model model) throws Exception {		
		dao.save(model);
		
	}

	public Model get(long id, String model) throws Exception {
		return dao.get(id, model);
		
	}

	public List<Model> getAll(String model) throws Exception {		
		List<Model> records = dao.getAll(model);
		
		return records;
	}

	public void remove(Model model) throws Exception {
		dao.remove(model);
		
	}
}
