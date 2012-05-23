/**
 * 
 */
package mconta.web.client.mobile.ui;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * JQMPage class
 * 
 * @author marc
 *
 */
public class JQMPage extends HTMLPanel {
		
	public JQMPage(String html) {
		super(html);
		
		getElement().setId(DOM.createUniqueId());
		getElement().setAttribute("data-role", "page");
	}

}
