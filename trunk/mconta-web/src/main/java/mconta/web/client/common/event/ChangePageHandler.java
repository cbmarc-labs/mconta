/**
 * 
 */
package mconta.web.client.common.event;

import mconta.domain.model.Model;

import com.google.gwt.event.shared.EventHandler;

/**
 * MessageHandler interface
 * 
 * @author marc
 *
 */
public interface ChangePageHandler extends EventHandler {
	
	public void onList(ChangePageEvent event);
	public void onEdit(ChangePageEvent event, Model model);
	
}
