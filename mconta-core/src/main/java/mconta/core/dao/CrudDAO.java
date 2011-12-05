/**
 * 
 */
package mconta.core.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author Marc
 *
 */
@Transactional
public interface CrudDAO<Type> {
	
	void save(Type entity) throws Exception;
	void remove(Type entity) throws Exception;
    Type get(long id, String domainName) throws Exception;
    List<Type> getAll(String domainName) throws Exception;

}
