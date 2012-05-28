package mconta.web.client.app.rpc;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("image.rpc")
public interface ImageService extends RemoteService {
	
	public List<String> getAll() throws Exception;
	
}
