/**
 * 
 */
package mconta.web.server;

import mconta.web.client.login.rpc.LoginService;
import mconta.web.shared.ServerException;
import mconta.web.shared.UserDto;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class LoginServiceImpl 
	extends SpringRemoteServiceServlet implements LoginService {

	public UserDto login(String username, String password) throws ServerException {
		UserDto user = new UserDto();
		
		user.setName(username);
		
		setUserSession(user);
		
		return user;
		
	}

	public void logout() throws ServerException {
		setUserSession(null);
		
	}
	

}
