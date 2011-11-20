/**
 * 
 */
package mconta.web.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import mconta.core.dao.CrudDAO;
import mconta.core.dao.RecordDAO;
import mconta.core.persistence.Record;
import mconta.web.client.rpc.RecordService;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class RecordServiceImpl extends CrudServiceImpl<Record> 
		implements RecordService {

	@Autowired
	private RecordDAO dao;

	@Override
	protected CrudDAO<Record> getDao() {
		return dao;
	}

	public void save(Record entity) throws Exception {
		dao.save(entity);
		
	}
	
	public List<Record> getAll() throws Exception {
		List<Record> all = dao.getAll();
		
		return all;
	}
	
}
