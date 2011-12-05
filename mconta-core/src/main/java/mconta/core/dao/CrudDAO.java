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
public interface CrudDAO<Type> extends DAO {
	
	@Transactional
	void save(Type entity) throws Exception;
	void update(Type entity) throws Exception;
	void remove(Type entity) throws Exception;
    Type get(long id) throws Exception;
    List<Type> getAll() throws Exception;

}