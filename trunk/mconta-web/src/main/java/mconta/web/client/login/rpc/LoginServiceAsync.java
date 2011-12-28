/**
 * 
 */
package mconta.web.client.login.rpc;

import mconta.web.shared.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface LoginServiceAsync {
	
	void isAuthenticated(AsyncCallback<UserDto> callback);
	void login(String email, String password, AsyncCallback<UserDto> callback);
	void logout(AsyncCallback<Void> callback);
	
}
