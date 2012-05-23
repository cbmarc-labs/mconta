/**
 * 
 */
package mconta.web.client.mobile.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * MessageHandler interface
 * 
 * @author marc
 *
 */
public interface ChangePageHandler extends EventHandler {
	
	public void onChangePage(String product);
	
}
