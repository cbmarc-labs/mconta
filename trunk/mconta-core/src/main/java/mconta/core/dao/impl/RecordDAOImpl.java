package mconta.core.dao.impl;

import mconta.core.dao.RecordDAO;
import mconta.core.persistence.Record;

import org.springframework.stereotype.Repository;

@Repository(value = "recordDAO")
public class RecordDAOImpl extends CrudDAOImpl<Record> 
		implements RecordDAO {
	
	@Override
	protected Class<Record> getDomainClass() {
		return Record.class;
	}
	
}
