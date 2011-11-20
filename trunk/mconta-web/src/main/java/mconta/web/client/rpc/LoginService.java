package mconta.web.client.rpc;

import mconta.web.shared.UserDTO;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("loginService")
public interface LoginService extends RemoteService {
	
	public UserDTO login(String username, String password) throws Exception;
	public void logout() throws Exception;
	
}
