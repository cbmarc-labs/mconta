/**
 * 
 */
package mconta.web.client.login.rpc;

import mconta.web.shared.dto.UserDto;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * LoginService class
 * 
 * @author Marc
 */
@RemoteServiceRelativePath("login.rpc")
public interface LoginService extends RemoteService {
	
	/**
	 * isAuthenticated()
	 * 
	 * @return
	 */
	UserDto isAuthenticated();
	
	/**
	 * login()
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	UserDto login(String email, String password) throws Exception;
	
	/**
	 * logout()
	 * 
	 * @throws Exception
	 */
	void logout() throws Exception;
	
}
