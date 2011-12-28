/**
 * 
 */
package mconta.web.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mconta.web.shared.dto.UserDto;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Marc
 *
 */
@SuppressWarnings("serial")
public class SpringRemoteServiceServlet extends RemoteServiceServlet {
		
	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		
		WebApplicationContextUtils.
				getRequiredWebApplicationContext(getServletContext()).
				getAutowireCapableBeanFactory().
				autowireBean(this);
	}
	
	protected HttpServletRequest getRequest(){
		return (HttpServletRequest)getThreadLocalRequest();
	}
	
	protected HttpSession getSession(){
		return getRequest().getSession();
	}
	
	protected void checkLoggedIn() throws Exception {
		/*UserDto user = getUserSession();
		
		if (user.getLoggedIn() == false) {
	    	throw new Exception("Not logged in.");
	    	
	    }*/
	}
	
	protected void setUserSession(UserDto user) {
		if(user == null)
			getSession().removeAttribute("user");
		else
			getSession().setAttribute("user", user);
		
	}
	
	protected UserDto getUserSession() {
		return (UserDto) getSession().getAttribute("user");
	}
	
}
