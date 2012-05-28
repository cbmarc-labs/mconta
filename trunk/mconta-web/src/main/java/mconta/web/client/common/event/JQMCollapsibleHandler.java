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
public interface JQMCollapsibleHandler extends EventHandler {
	
	public void onExpand(JQMCollapsibleEvent event);
	public void onCollapse(JQMCollapsibleEvent event);
	
}
