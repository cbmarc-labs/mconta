/**
 * 
 */
package mconta.web.client.login.rpc;

import mconta.web.shared.dto.UserDto;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * LoginServiceAsync interface
 * 
 * @author Marc
 */
public interface LoginServiceAsync {
	
	/**
	 * isAuthenticated()
	 * 
	 * @param callback
	 */
	void isAuthenticated(AsyncCallback<UserDto> callback);
	
	/**
	 * login()
	 * 
	 * @param email
	 * @param password
	 * @param callback
	 */
	void login(String email, String password, AsyncCallback<UserDto> callback);
	
	/**
	 * logout()
	 * 
	 * @param callback
	 */
	void logout(AsyncCallback<Void> callback);
	
}
