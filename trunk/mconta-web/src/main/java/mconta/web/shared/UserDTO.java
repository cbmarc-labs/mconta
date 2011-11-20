/**
 * 
 */
package mconta.web.shared;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class UserDTO implements Serializable {
	
	private String username;
	
	private Boolean LoggedIn;
	
	private Date LoggedInOn;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the loggedIn
	 */
	public Boolean getLoggedIn() {
		return LoggedIn;
	}

	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(Boolean loggedIn) {
		LoggedIn = loggedIn;
	}

	/**
	 * @return the loggedInOn
	 */
	public Date getLoggedInOn() {
		return LoggedInOn;
	}

	/**
	 * @param loggedInOn the loggedInOn to set
	 */
	public void setLoggedInOn(Date loggedInOn) {
		LoggedInOn = loggedInOn;
	}
	
}
