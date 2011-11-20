/**
 * 
 */
package mconta.web.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface CrudServiceAsync<Entity> {
	
	void remove(Entity entity, AsyncCallback<Void> callback);
	void getAll(AsyncCallback<List<Entity>> callback);
	void save(Entity entity, AsyncCallback<Void> callback);
	void get(long id, AsyncCallback<Entity> callback);

}
