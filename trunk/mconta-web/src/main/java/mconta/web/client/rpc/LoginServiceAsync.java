/**
 * 
 */
package mconta.web.client.rpc;

import mconta.web.shared.UserDTO;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author Marc
 *
 */
public interface LoginServiceAsync {
	
	void login(String username, String password, AsyncCallback<UserDTO> callback);
	void logout(AsyncCallback<Void> callback);

}
