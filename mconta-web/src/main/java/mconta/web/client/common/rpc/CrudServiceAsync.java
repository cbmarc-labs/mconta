/**
 * 
 */
package mconta.web.client.common.rpc;

import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface CrudServiceAsync {
	
	void deleteAll(Set<Model> dto, AsyncCallback<Void> callback);
	void delete(Model dto, AsyncCallback<Void> callback);
	void getAll(String model, AsyncCallback<List<Model>> callback);
	void saveOrUpdate(Model dto, AsyncCallback<Void> callback);
	void get(long id, String model, AsyncCallback<Model> callback);
	
	void test(String data, AsyncCallback<String> callback);

}
