package mconta.web.client.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;

public interface CrudService<Entity> extends RemoteService {
	
	public void save(Entity entity) throws Exception;
	public Entity get(long id) throws Exception;
	public List<Entity> getAll() throws Exception;
	public void remove(Entity entity) throws Exception;
	
}
