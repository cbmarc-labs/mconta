/**
 * 
 */
package mconta.web.server;

import java.util.List;

import mconta.core.dao.RecordDAO;
import mconta.core.persistence.Record;
import mconta.web.client.rpc.RecordService;
import mconta.web.shared.RecordDTO;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class RecordServiceImpl extends SpringRemoteServiceServlet 
		implements RecordService {

	@Autowired
	private RecordDAO dao;

	public void save(RecordDTO entity) throws Exception {
		Record record = new Record();
		
		record.setPrice(100);
		record.setTitle("pepe");
		record.setYear(2005);
		
		dao.save(record);
		
	}

	public RecordDTO get(long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<RecordDTO> getAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public void remove(RecordDTO entity) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
