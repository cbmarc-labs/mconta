/**
 * 
 */
package mconta.web.server;

import mconta.web.client.login.rpc.LoginService;
import mconta.web.shared.ServerException;
import mconta.web.shared.dto.UserDto;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class LoginServiceImpl 
	extends SpringRemoteServiceServlet implements LoginService {

	public UserDto login(String username, String password) throws ServerException {
		UserDto user = new UserDto();
		
		user.setUsername(username);
		
		//setUserSession(user);
		
		return user;
		
	}

	public void logout() throws ServerException {
		//setUserSession(null);
		
	}

	public UserDto isAuthenticated() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
