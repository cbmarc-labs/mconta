/**
 * 
 */
package mconta.domain.dao;

import java.util.List;
import java.util.Set;

/**
 * CrudDAO interface
 * 
 * @author Marc
 */
public interface CrudDAO<Type> {
	
	void saveOrUpdate(Type entity) throws Exception;
	void deleteAll(Set<Type> entity) throws Exception;
    Type get(long id, String domainName) throws Exception;
    List<Type> getAll(String domainName) throws Exception;

}
