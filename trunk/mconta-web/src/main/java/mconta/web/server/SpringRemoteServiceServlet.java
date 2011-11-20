/**
 * 
 */
package mconta.web.server;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.WebApplicationContext;
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

}
