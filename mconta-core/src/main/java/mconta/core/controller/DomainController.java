/**
 * 
 */
package mconta.core.controller;

import java.util.List;

/**
 * @author Marc
 *
 */
public interface DomainController<ENTITY> {
	
	ENTITY save(ENTITY entity);
	List<ENTITY> getAllObjects();
	
}
