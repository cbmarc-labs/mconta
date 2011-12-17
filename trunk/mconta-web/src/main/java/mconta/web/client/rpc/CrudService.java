package mconta.web.client.rpc;

import java.util.List;
import java.util.Set;

import mconta.domain.model.Model;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("crudService")
public interface CrudService extends RemoteService {
	
	public void saveOrUpdate(Model model) throws Exception;
	public Model get(long id, String model) throws Exception;
	public List<Model> getAll(String model) throws Exception;
	public void deleteAll(Set<Model> model) throws Exception;
	
}
