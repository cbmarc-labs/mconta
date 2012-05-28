/**
 * 
 */
package mconta.web.client.common.event;

import com.google.gwt.event.shared.EventHandler;

/**
 * MessageHandler interface
 * 
 * @author marc
 *
 */
public interface JQMListHandler extends EventHandler {
	
	public void onLeftClick(JQMListEvent event, int index);
	public void onRightClick(JQMListEvent event, int index);
	
}
