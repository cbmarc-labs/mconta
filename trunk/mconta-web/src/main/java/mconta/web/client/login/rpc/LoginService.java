/**
 * 
 */
package mconta.web.client.login.rpc;

import mconta.web.shared.dto.UserDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Marc
 *
 */
@RemoteServiceRelativePath("login.rpc")
public interface LoginService extends RemoteService {
	
	UserDto isAuthenticated();
	UserDto login(String email, String password) throws Exception;
	void logout() throws Exception;
	
}
