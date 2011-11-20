package mconta.core.dao;

import org.springframework.stereotype.Repository;

import mconta.core.persistence.Record;

@Repository(value = "recordDAO")
public class RecordDAOImpl extends GenericDAOImpl<Record> 
		implements RecordDAO {
	
	@Override
	protected Class<Record> getDomainClass() {
		return Record.class;
	}
	
}
