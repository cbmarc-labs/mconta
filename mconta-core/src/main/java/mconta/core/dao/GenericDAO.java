/**
 * 
 */
package mconta.core.dao;

import java.util.List;

/**
 * @author Marc
 *
 */
public interface GenericDAO<Type> extends DAO {
	
	void save(Type entity) throws Exception;
	void update(Type entity) throws Exception;
	void remove(Type entity) throws Exception;
    Type get(long id) throws Exception;
    List<Type> getAll() throws Exception;

}
