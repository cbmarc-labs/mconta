/**
 * 
 */
package mconta.web.server;

import java.util.Date;

import mconta.web.client.rpc.LoginService;
import mconta.web.shared.UserDTO;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class LoginServiceImpl 
	extends SpringRemoteServiceServlet implements LoginService {

	public UserDTO login(String username, String password) throws Exception {
		UserDTO user = new UserDTO();
		
		user.setUsername(username);
		user.setLoggedIn(true);
		user.setLoggedInOn(new Date());
		
		setUserSession(user);
		
		return user;
		
	}

	public void logout() throws Exception {
		setUserSession(null);
		
	}
	

}
