/**
 * 
 */
package mconta.web.server;

import java.util.List;
import java.util.Set;

import mconta.domain.dao.CrudDAO;
import mconta.domain.model.Model;
import mconta.web.client.app.rpc.CrudService;
import mconta.web.shared.ServerException;

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

	public void saveOrUpdate(Model model) throws ServerException {
		try {
			dao.saveOrUpdate(model);
			
		} catch(Exception e) {
			// normally bad style but we need to throw again to inform the client
			throw new ServerException(e);
			
		}
		
	}

	public Model get(long id, String model) throws ServerException {
		Model entity = null;
		
		try {
			entity = dao.get(id, model);
			
		} catch(Exception e) {
			throw new ServerException(e);
		}
			
		return entity;
		
	}

	public List<Model> getAll(String model) throws ServerException {
		List<Model> list = null;
		
		try {
			list = dao.getAll(model);
			
			for(Model m: list) {
				m.deHibernate();
			}
			
		} catch(Exception e) {
			throw new ServerException(e);
			
		}
		
		return list;
		
	}

	public void deleteAll(Set<Model> model) throws ServerException {
		try {
			dao.deleteAll(model);
			
		} catch(Exception e) {
			throw new ServerException(e);
			
		}
		
	}

	@Override
	public String test(String data) throws Exception {
		//this.saveOrUpdate(new User(data, data, false, null));
		
		return "All ok !!!";
		
	}
}
