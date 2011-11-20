package mconta.core.dao.impl;

import mconta.core.dao.AuditDAO;
import mconta.core.persistence.Audit;

import org.springframework.stereotype.Repository;

@Repository(value = "auditDAO")
public class AuditDAOImpl extends CrudDAOImpl<Audit> 
	implements AuditDAO {
	
	@Override
	protected Class<Audit> getDomainClass() {
		return Audit.class;
	}
	
}
